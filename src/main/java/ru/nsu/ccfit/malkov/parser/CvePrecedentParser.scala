package ru.nsu.ccfit.malkov.parser

import ru.nsu.ccfit.malkov.model.TableCveElement
import java.net.URL
import java.io.{InputStreamReader, BufferedReader}

/**
 * @author matveyka
 */
class CvePrecedentParser(list : List[TableCveElement]) extends PrecedentParser{

  def parse() {
    //list.foreach(parseElement(_))
    list.drop(100).foreach(_.references.foreach(i => println(i)))
    list.drop(100) foreach (parseElement(_))
    list.drop(100).foreach(_.references.foreach(i => println(i)))
  }

  def loadText(link :String) = {
    try{
    val bufferedReader = new BufferedReader(new InputStreamReader(new URL(link).openStream()))
    var line : String = ""
    val stringBuilder = new StringBuilder
    var needInsert = false
    while ((line = bufferedReader.readLine()) != null) {
      if (!needInsert) {
        if (line.contains("</head>")) {
          needInsert = true
        }
      } else {
        stringBuilder.append(line)
      }
    }
    val noHTMLString = stringBuilder.toString().replaceAll("\\<.*?\\>", "")

    // Remove Carriage return from java String
    noHTMLString.replaceAll("\r", "<br/>")

    // Remove New line from java string and replace html break
    noHTMLString.replaceAll("\n", " ")
    noHTMLString.replaceAll("\'", "&#39;")
    noHTMLString.replaceAll("\"", "&quot;")
    noHTMLString
    } catch {
      case e:Exception => {
        println(e.getLocalizedMessage)
        ""
      }
    }
  }

  def parseElement(elem : TableCveElement) {
    val list = elem.references
    var resultList = List[String]()
    list.foreach(resultList ::= loadText(_))
    elem.references = resultList
  }
}

object CvePrecedentParser {
  def apply(list : List[TableCveElement]) : CvePrecedentParser = {
    new CvePrecedentParser(list)
  }
}
