package ru.nsu.ccfit.malkov.db

import java.sql.Connection

/*
 * Created with IntelliJ IDEA.
 * User: malkov
 * Date: 4/23/13
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */
trait TransactionCallback {
  def doIntoTransaction(connection : Connection)
}
