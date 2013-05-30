package ru.nsu.ccfit.malkov.db

import java.sql.{SQLException, DriverManager}

/*
 * Created with IntelliJ IDEA.
 * User: malkov
 * Date: 4/23/13
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */
class TransactionManager(user: String, passwd: String) {


  def execute(callback: TransactionCallback) {
    val connection = DriverManager.getConnection(TransactionManager.url, user, passwd)
    try {
      connection.setAutoCommit(false)

      callback.doIntoTransaction(connection)
      connection.commit()
      connection.setAutoCommit(true)
    } catch {
      case e: SQLException => {
        try {
          connection.rollback()
        } catch {
          case e1: SQLException => {
            println(e1.getLocalizedMessage)

          }
        }
      }
    }
  }
}

object TransactionManager {
  val url = "jdbc:postgresql://localhost:5432/diploma?rewriteBatchedStatements=true"
}
