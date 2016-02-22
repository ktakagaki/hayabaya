package de.lin_magdeburg.hayabaya.benchmarkers

import de.lin_magdeburg.hayabaya.datarelated.DataTypes

/**
  * Created by admin on 2/22/2016.
  */
abstract class AbstractBenchmark(val dataType: DataTypes) {
  def dataTypeMessage(): String = s"I'm a $dataType kind of guy"
  override def toString: String = s"$dataType"

}
