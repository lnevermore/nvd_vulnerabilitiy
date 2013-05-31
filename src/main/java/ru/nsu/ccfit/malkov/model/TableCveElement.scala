package ru.nsu.ccfit.malkov.model

/**
 * pre links parsiog container for saving entities
 * @author malkov
 *
 */
case class TableCveElement(var references : List[String], var description : String, var name : String) {
  def this() { this(List(), "", "") }
}
