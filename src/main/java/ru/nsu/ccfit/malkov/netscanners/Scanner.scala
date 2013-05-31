package ru.nsu.ccfit.malkov.netscanners

/**
 * typical scanner interface
 * @author matveyka
 * @see NvdScanner, CveScanner
 */
trait Scanner extends Runnable {
  def scanFile()
}

