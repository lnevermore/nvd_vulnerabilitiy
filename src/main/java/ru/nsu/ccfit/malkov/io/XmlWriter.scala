package ru.nsu.ccfit.malkov.io

import javax.xml.parsers.DocumentBuilderFactory
import ru.nsu.ccfit.malkov.model.VirusPrecedent
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.dom.DOMSource
import java.io.{FileWriter, File}

/**
 * @author matveyka
 * class for show all entities to expert using XML
 */
class XmlWriter() {

  implicit def virusToXml(prec : VirusPrecedent) : XmlVirus = {
    new XmlVirus {
      val threat: String = prec.threat
      val looses: String = prec.looses
      val symptom: String = prec.symptom
      val countermeasures: String = prec.countermeasures
      val vulneralibity: String = prec.vulnerabilities
      val consequences: String = prec.consequences
      val name: String = prec.name
    }
  }


  def writeVirusesToFile(fileName : String, list : List[VirusPrecedent]) {
    val array = <precedents>
      {for (item <- list) yield {item.toXml}}
    </precedents>
    scala.xml.XML.save(fileName, array)
  }
}

/**
 * implictit class for implicit transformation.
 * @see this::virusToXml method
 */
abstract class XmlVirus {
  val name : String
  val symptom: String
  val threat : String
  val consequences : String
  val countermeasures : String
  val looses: String
  val vulneralibity : String

  override def toString = name

  def toXml = {
    <virus name={name}>
      <symptom>{symptom}</symptom>
      <threat>{threat}</threat>
      <consequences>{consequences}</consequences>
      <countermeasures>{countermeasures}</countermeasures>
      <looses>{looses}</looses>
      <vulnerabilities>{vulneralibity}</vulnerabilities>
    </virus>

  }
}
