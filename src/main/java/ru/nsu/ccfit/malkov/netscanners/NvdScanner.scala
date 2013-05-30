package ru.nsu.ccfit.malkov.netscanners

import xml.pull._
import io.Source
import ru.nsu.ccfit.malkov.model.{TableCveElement, TableNvdElement}
import xml.pull.EvElemEnd
import xml.pull.EvElemStart

/**
 @author : malkov
 */
class NvdScanner(filename : String) extends CveScanner(filename){
  var addedElement = new TableCveElement()

  var isProduct = false
  var isSummary = false

  override def matchItem(event: XMLEvent) {
    event match {
      case EvElemStart(_, "entry", id, _) => {
        addedElement = new TableCveElement()
        addedElement.name = id.get("id").getOrElse(null).toString()
      }
      case EvElemEnd(_, "entry") => {
        processItem(addedElement)
        addedElement = null
      }
      case EvElemStart(_, "product", _, _) => { isProduct = true }
      case EvText(text) => {
        if(isProduct) {}
        if (isSummary) { addedElement.description = text}
      }
      case EvElemEnd(_, "product") => {isProduct = false}
      case EvElemStart(_, "reference", data, _) => {
        val href = data.get("href")
        if (!href.isEmpty) { addedElement.references ::= href.get.toString()}
      }
      case EvElemStart(_, "summary", _, _) => { isSummary = true }
      case EvElemEnd(_, "summary") => { isSummary = false }
      case (_) => {}
    }
  }

  override def scanFile() {
    val p = new XMLEventReader(Source.fromFile(filename))
    p.foreach(matchItem(_))
  }

  override def run() {
    scanFile()
  }
}

object NvdScanner {
  val CUT_REDUCIANT_PREFIX = 7
  def apply(filename : String) : NvdScanner = {
    new NvdScanner(filename)
  }
}
