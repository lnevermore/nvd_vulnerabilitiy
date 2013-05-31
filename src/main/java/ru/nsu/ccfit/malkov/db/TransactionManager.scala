package ru.nsu.ccfit.malkov.db

import java.sql.{SQLException, DriverManager}

/**
 * @author malkov
 * @param user user db to set
 * @param passwd user's db pass
 */
class TransactionManager(user: String, passwd: String) {

  /**
   * exetuce single transaction with connection
   * @param callback. callback, which called when connection had taken.
   */
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

/**
 * object class for apply method
 */
object TransactionManager {
  val url = "jdbc:postgresql://localhost:5432/diploma?rewriteBatchedStatements=true"
}
