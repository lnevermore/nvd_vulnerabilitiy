package ru.nsu.ccfit.malkov.db

import ru.nsu.ccfit.malkov.model.TableCveElement
import java.sql.{SQLException, Statement, PreparedStatement, Connection}

/**
 * adapter for saving parser entities and links texts
 * @param user db to set
 * @param passwd user's db pass
 */
class PostgreDatabaseAdapter(user: String, passwd: String) {

  val driver = "org.postgresql.Driver"
  Class.forName(driver)

  /**
   * string resources with inserts
   */
  val insertElementPattern = "INSERT INTO elements VALUES (nextval('elements_id_seq'),?, ?);"
  val insertLinkPattern = "INSERT INTO linktexts VALUES (nextval('linktexts_id_seq'), ?, ?);"

  val manager = new TransactionManager(user, passwd)


  /**
   * add single element to db.
   * @param elem CVe element to save
   * @param linksTexts array with strings(link texts)
   */
  def addElements(elem: TableCveElement, linksTexts: List[String]) {
    manager.execute(new TransactionCallback {
      def doIntoTransaction(connection: Connection) {
        try {
        val elementStatement = connection.prepareStatement(insertElementPattern, Statement.RETURN_GENERATED_KEYS)
        connection.prepareStatement(insertElementPattern, Statement.RETURN_GENERATED_KEYS)

        elementStatement.setString(1, elem.name)
        elementStatement.setString(2, elem.description)
        elementStatement.executeUpdate()
        val idsSet = elementStatement.getGeneratedKeys
        println("links size :" + linksTexts.size)
        if (idsSet.next()) {
          println("start adding links by id " + idsSet.getInt(1))
          val linksStatement = connection prepareStatement insertLinkPattern

          linksTexts.foreach(text => {
            println(text)
            linksStatement.setString(1, text)
            linksStatement.setInt(2, idsSet.getInt(1))
            linksStatement.addBatch()
          })

          linksStatement.executeBatch()

          elementStatement.close()
          linksStatement.close()
        }
        } catch {
          case e :SQLException => {println(e.getLocalizedMessage)}
        }
      }
    })
  }


}
