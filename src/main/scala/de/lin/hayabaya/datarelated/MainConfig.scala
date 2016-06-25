package de.lin.hayabaya.datarelated

import com.typesafe.scalalogging.LazyLogging

/**
 * Main configuration of the project at runtime.
 * contains values for arraylengths, CPU/system name and so forth.
 */
case class MainConfig (
  runtest: Boolean = false,
  systemName: String = "i73770k",
  minarraysize: Int = 2024,
  maxarraysize: Int = 4048,
  arraystepsize: Int = 8 ) {

  override def apply(runTest: Boolean, ): MainConfig = new MainConfig(runtest, systemName, minarraysize, maxarraysize, arraystepsize)
}
