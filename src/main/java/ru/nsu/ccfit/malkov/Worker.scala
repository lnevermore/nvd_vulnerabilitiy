package ru.nsu.ccfit.malkov

import io.{OntoboxInserter, XmlWriter}
import model.VirusPrecedent
import netscanners.{NvdScanner, CveScanner}

/**
 * @author matveyka
 */
object Worker {
  def main(args: Array[String]) {
    /*val writer = new XmlWriter
    val list = List(new VirusPrecedent("valera", "stopr", "dva","semki","probka","trista kitaicev","fooo1"),
      new VirusPrecedent("valera", "stopr", "dva","semki","probka","trista kitaicev","fooo2"),
      new VirusPrecedent("valera", "stopr", "dva","semki","probka","trista kitaicev","fooo3"),
      new VirusPrecedent("valera", "stopr", "dva","semki","probka","trista kitaicev","foo04"),
      new VirusPrecedent("valera", "stopr", "dva","semki","probka","trista kitaicev","fooo5"))*/

    //writer.writeVirusesToFile("blaaah.xml", list)
    /*val inserter = new OntoboxInserter(list)
    inserter.insert()*/
    //NvdScanner("nvdcve-2012.xml").run()
    NvdScanner("nvdcve-2012.xml").run()
  }

}
