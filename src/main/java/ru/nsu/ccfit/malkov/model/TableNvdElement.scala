package ru.nsu.ccfit.malkov.model

/**
 * pre links parsiong class for saving entities
 @author : malkov
 */
case class TableNvdElement(var references : List[String], var name : String, var summary : String,
                           var products : List[String] ) {
  def this() {this(List(), "", "", List())}
}
