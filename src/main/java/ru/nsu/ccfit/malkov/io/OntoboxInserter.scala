package ru.nsu.ccfit.malkov.io

import ru.nsu.ccfit.malkov.model.VirusPrecedent

/**
 @author : malkov
          inserts ready entities to ontobox db
 */
class OntoboxInserter(list : List[VirusPrecedent]) {
  def insertOne(one : VirusPrecedent) {

  }

  /*def insert() {
    val box = new StorageBox(new File("/home/malkov/Dropbox/diploma/final"))
    try {
      val worker = box.work
      try {
        MVX.importFile(new File("the_last_one_new.mvx"), worker)
        val name = worker.resolve(DBNames.NAME_TPROP, Entity.TPROPERTY)
        val date = worker.resolve(DBNames.DATE_TPROP, Entity.TPROPERTY)
        val precSymptom = worker.resolve(DBNames.PRECEDENT_SYMPTOM_OPROP, Entity.OPROPERTY)
        val precThreat = worker.resolve(DBNames.PRECEDENT_THREAT_OPROP, Entity.OPROPERTY)
        val precConsequence = worker.resolve(DBNames.PRECEDENT_CONSEQUENCE_OPROP, Entity.OPROPERTY)
        val precCountermeasure = worker.resolve(DBNames.PRECEDENT_COUNTERMEASURE_OPROP, Entity.OPROPERTY)
        val precLoss = worker.resolve(DBNames.PRECEDENT_LOSS_OPROP, Entity.OPROPERTY)
        val precVulnerability = worker.resolve(DBNames.PRECEDENT_VULNERABILITY_OPROP, Entity.OPROPERTY)

        val writer = worker.write()
        list.foreach(item => {
          val objectAddition = WHelper.`object`(worker, DBNames.PRECEDENT_CLASS + "#" + item.name, DBNames.PRECEDENT_CLASS)
          objectAddition.setString(DBNames.PRECEDENT_SYMPTOM_OPROP, item.symptom)
          objectAddition.setString(DBNames.PRECEDENT_THREAT_OPROP, item.threat)
          objectAddition.setString(DBNames.PRECEDENT_CONSEQUENCE_OPROP, item.consequences)
          objectAddition.setString(DBNames.PRECEDENT_COUNTERMEASURE_OPROP, item.countermeasures)
          objectAddition.setString(DBNames.PRECEDENT_VULNERABILITY_OPROP, item.vulnerabilities)
          objectAddition.setString(DBNames.PRECEDENT_LOSS_OPROP, item.looses)
          worker.commit()
        })
      } catch {
        case e : Exception => {
          println("bybyby: " +e.getLocalizedMessage)
        }
      } finally {
        worker.close()
        box.close()
      }
    }
  }*/
}
