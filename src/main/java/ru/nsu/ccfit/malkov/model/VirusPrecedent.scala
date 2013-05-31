package ru.nsu.ccfit.malkov.model

/**
 * bean for saving ready entities to expert checking and after it
 * @author matveyka
 */
case class VirusPrecedent(name : String, symptom : String, threat : String,
                          consequences : String, countermeasures : String,
                          looses : String, vulnerabilities : String)
