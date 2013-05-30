package ru.nsu.ccfit.malkov.io

/**
 @author : malkov
 */
object DBNames {

    val ONTOLOGY = "http://ontobox.org/risk-manager" 
    val CONSEQUENCE_CLASS = ONTOLOGY + "#Consequence" 
    val VULNERABILITY_CLASS = ONTOLOGY + "#Vulnerability" 
    val COUNTERMEASURE_CLASS = ONTOLOGY + "#Measure" 
    val LOSS_CLASS = ONTOLOGY + "#Loss" 
    val SYMPTOM_CLASS = ONTOLOGY + "#Symptom" 
    val THREAT_CLASS = ONTOLOGY + "#Threat" 
    val PRECEDENT_CLASS = ONTOLOGY + "#Precedent" 
    val NAME_TPROP = ONTOLOGY + "#name" 
    val DATE_TPROP = ONTOLOGY + "#date" 
    val SUBTHREAT_OPROP = ONTOLOGY + "#SubThreat" 
    val MAINTHREAT_OBJ = ONTOLOGY + "#MainThreat" 
    val SUBCONSEQUENCE_OPROP = ONTOLOGY + "#SubConsequence" 
    val MAINCONSEQUENCE_OBJ = ONTOLOGY + "#MainConsequense" 
    val SUBCOUNTERMEASURE_OPROP = ONTOLOGY + "#SubMeasure" 
    val MAINCOUNTERMEASURE_OBJ = ONTOLOGY + "#MainMeasure" 
    val SUBLOSS_OPROP = ONTOLOGY + "#SubLoss" 
    val MAINLOSS_OBJ = ONTOLOGY + "#MainLoss" 
    val SUBSYMPTOM_OPROP = ONTOLOGY + "#SubSymptom" 
    val MAINSYMPTOM_OBJ = ONTOLOGY + "#MainSymptom" 
    val SUBVULNERABILITY_OPROP = ONTOLOGY + "#SubVulnerability" 
    val MAINVULNERABILITY_OBJ = ONTOLOGY + "#MainVulnerability" 
    val PRECEDENT_SYMPTOM_OPROP = ONTOLOGY + "#precedent_symptom" 
    val PRECEDENT_THREAT_OPROP = ONTOLOGY + "#precedent_threat" 
    val PRECEDENT_CONSEQUENCE_OPROP = ONTOLOGY + "#precedent_consequence" 
    val PRECEDENT_COUNTERMEASURE_OPROP = ONTOLOGY + "#precedent_measure" 
    val PRECEDENT_LOSS_OPROP = ONTOLOGY + "#precedent_loss" 
    val PRECEDENT_VULNERABILITY_OPROP = ONTOLOGY + "#precedent_vulnerability"

}