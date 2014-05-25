package ru.nsu.ccfit.malkov.netscanners

import xml.pull._
import io.Source
import ru.nsu.ccfit.malkov.model.{TableCveElement, TableNvdElement}
import xml.pull.EvElemEnd
import xml.pull.EvElemStart

/**
 * class for parsiog xml from nvd and get all text links
 * So, this class implements "searching algorithm"
 @author : malkov
 */
class NvdScanner(filename : String) extends CveScanner(filename){
  var addedElement = new TableCveElement()

  var isProduct = false
  var isSummary = false


  /**
   * method for stax xml parsing
   * @param event
   */
  override def matchItem(event: XMLEvent) {
    event match {
      case EvElemStart(_, "entry", id, _) => {
        addedElement = new TableCveElement()
        addedElement.name = id.get("name").getOrElse(null).toString()
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
      case EvElemStart(_, "ref", data, _) => {
        val href = data.get("url")
        if (!href.isEmpty) { addedElement.references ::= href.get.toString()}
      }
      case EvElemStart(_, "descript", _, _) => { isSummary = true }
      case EvElemEnd(_, "descript") => { isSummary = false }
      case (_) => {}
    }
  }

  /**
   * @see CveScanner::scanFile()
   */
  override def scanFile() {
    val p = new XMLEventReader(Source.fromFile(filename))
    p.foreach(matchItem(_))
  }

  /**
  * @see CveScanner::run()
  */
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
