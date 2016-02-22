package de.lin_magdeburg.hayabaya.benchmarkers

import de.lin_magdeburg.hayabaya.datarelated.Datatypes

/**
  * Created by admin on 2/22/2016.
  */
abstract class AbstractBenchmark(val dataType: Datatypes) {
  def dataTypeMessage(): String = s"I'm a $dataType kind of guy"
  override def toString: String = s"$dataType"

}
