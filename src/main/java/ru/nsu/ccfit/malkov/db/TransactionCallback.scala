package ru.nsu.ccfit.malkov.db

import java.sql.Connection


/**
 * interface for callback
 * @see TransactionManager::doIntoTransaction
 */
trait TransactionCallback {
  def doIntoTransaction(connection : Connection)
}
