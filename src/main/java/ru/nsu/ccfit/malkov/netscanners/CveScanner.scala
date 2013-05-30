package ru.nsu.ccfit.malkov.netscanners

import ru.nsu.ccfit.malkov.model.TableCveElement
import xml.pull._
import io.Source
import xml.pull.EvElemStart
import ru.nsu.ccfit.malkov.model.TableCveElement
import xml.pull.EvText
import ru.nsu.ccfit.malkov.parser.CvePrecedentParser
import org.jsoup.{HttpStatusException, Jsoup}
import org.jsoup.nodes.{Node, TextNode, Element, Document}
import ru.nsu.ccfit.malkov.db.PostgreDatabaseAdapter
import java.io.IOException
import org.jsoup.select.Elements
import java.util


/**
 * @author matveyka
 */
class CveScanner(filename: String) extends Scanner {

  var isDescStart = false
  var addingList: TableCveElement = new TableCveElement()

  val adapter = new PostgreDatabaseAdapter("root", "root")

  def matchItem(event: XMLEvent) {
    event match {
      case EvElemStart(_, "item", name, _) => {
        addingList = new TableCveElement()
        addingList.name = name.get("name").getOrElse(null).toString()
      }
      case EvElemStart(_, "desc", _, _) => {
        isDescStart = true
      }
      case EvElemStart(_, "ref", links, _) => {
        val option = links.get("url")
        if (!option.isEmpty) {
          addingList.references ::= option.get.toString
        }
      }
      case EvText(text) => {
        if (isDescStart) addingList.description = text
      }
      case EvElemEnd(_, "item") => {
        processItem(addingList)
        addingList = null
      }
      case EvElemEnd(_, "desc") => {
        isDescStart = false
      }
      case _ => {}
    }
  }

  override def scanFile() {
    val p = new XMLEventReader(Source.fromFile(filename))
    p.foreach(matchItem(_))

  }

  def run() {
    scanFile()
  }

  def removeRecursively(elements: Elements, removingElements: Elements) {
    val iterator: util.ListIterator[Element] = removingElements.listIterator()
    while (iterator.hasNext) {
      val elem = iterator.next()
      val allElements: Elements = elem.getAllElements
      allElements.remove(elem)
      removeRecursively(elements, allElements)
    }
    elements.removeAll(removingElements)
  }

  def removeTags(element: Element, forbidden: List[String], builder: StringBuilder) {
    if (!forbidden.contains(element.tagName())) {

      val nodes: util.List[Node] = element.childNodes()

      val iterator: util.ListIterator[Node] = nodes.listIterator()

      while (iterator.hasNext) {

        val next: Node = iterator.next()

        if (next.isInstanceOf[TextNode] && !(next.asInstanceOf[TextNode].text().trim.length == 0)) {

          builder.append(next.asInstanceOf[TextNode].text()).append(' ')

        } else if (next.isInstanceOf[Element]) {

          if (next.asInstanceOf[Element].tagName() eq "br") {

            builder.append('\n')

          } else {

            removeTags(next.asInstanceOf[Element], forbidden, builder)

          }
        }
      }

    }

  }

  def findTextIntoBody(element: Element): String = {
    val list = List[String]("ul", "ol", "script", "style", "noscript", "footer", "header", "input")
    val builder = new StringBuilder

    removeTags(element, list, builder)
    builder.toString()
  }

  def processItem(elem: TableCveElement) {

    println("element started to get links")

    var textList = List[String]()
    elem.references.foreach(ref => {
      try {

        val body = Jsoup.connect(ref).timeout(0).get().body()
        val refResult: String = findTextIntoBody(body)

        println(ref + " says : " + refResult)

        textList ::= refResult

      } catch {

        case e: IOException => {

          println(e.getLocalizedMessage)
        }
      }
    })
    adapter.addElements(elem, textList)

    println("element added to db")
  }
}

object CveScanner {
  def apply(filename: String): CveScanner = {
    new CveScanner(filename)
  }
}
