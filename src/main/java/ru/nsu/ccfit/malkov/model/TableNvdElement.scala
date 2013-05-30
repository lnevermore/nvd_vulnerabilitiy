package ru.nsu.ccfit.malkov.model

/**
 @author : malkov
 */
case class TableNvdElement(var references : List[String], var name : String, var summary : String,
                           var products : List[String] ) {
  def this() {this(List(), "", "", List())}
}
