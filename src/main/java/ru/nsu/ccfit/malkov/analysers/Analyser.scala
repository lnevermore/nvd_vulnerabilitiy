package ru.nsu.ccfit.malkov.analysers

import java.io.{PrintWriter, File}
import scala.io.Source

/**
 * Created by m.malkov on 25/05/14.
 */
class Analyser {
  val symptomStops = List("overflow", "crash", "host", "gain", "corrupt memory", "attack",
    "timer", "warning", "control", "access", "affect", "brake", "attacker")
  val treatStops = List("gain", "impact", "remote code execution", "report", "affect", "delete",
    "view data", "attack")
  val consequencesStops = List("overflow", "crashed", "remove code execution", "exploit", "content", "files",
    "corrupt files", "affected", "broke", "delete data", "view data", "administrative rights", "attacker")
  val countermeasuresStops = List("update", "release", "security update", "support", "download", "create",
    "install", "defence", "prevent", "recommendation", "new version",
    "helpful", "publish", "http://", "resolve", "patch", "up-to-date",
    "fixed", "upgrade")

  def analyseFromFile(path : String) { analyseFromFile(new File(path)) }
  def analyseById(id : Int) = throw new IllegalAccessError("nnnnope")

  def analyseFromFile(file : File) {
    val symptoms = Source.fromFile(file).mkString.split("[\\.][ ]|[\\n]").
      filter(sent => sent.split(" ").exists(symptomStops.contains))
    val treats = Source.fromFile(file).mkString.split("[\\.][ ]|[\\n]").
      filter(sent => sent.split(" ").exists(treatStops.contains))
    val consequences = Source.fromFile(file).mkString.split("[\\.][ ]|[\\n]").
      filter(sent => sent.split(" ").exists(consequencesStops.contains))
    val countermeasures =  Source.fromFile(file).mkString.split("[\\.][ ]|[\\n]").filter(sent => sent.split(" ").exists(countermeasuresStops.contains))

    val builder = new StringBuilder().append("{result:[{")
    builder.append("name : \"" + file.getName + "\",\n")
    builder.append("symptom : \""+ symptoms.mkString(" ; ").replace("\"", "\\\"").replace("\'", "\\\'") + "\",\n")
    builder.append("threat : \""+ treats.mkString(" ; ").replace("\"", "\\\"").replace("\'", "\\\'") + "\",\n")
    builder.append("consequences : \""+ consequences.mkString(" ; ").replace("\"", "\\\"").replace("\'", "\\\'") + "\",\n")
    builder.append("countermeasures : \""+ countermeasures.mkString(" ; ").replace("\"", "\\\"").replace("\'", "\\\'") + "\"\n")
    builder.append("}]}")
    Some(new PrintWriter(file.getName+"-parsed.txt")).foreach{f => try{f.write(builder.toString())}finally{f.close()}}
  }

  def analyseAll(dir : File) = dir.listFiles().foreach(analyseFromFile)


}
