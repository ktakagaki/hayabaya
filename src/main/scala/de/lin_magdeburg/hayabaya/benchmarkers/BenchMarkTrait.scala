package de.lin_magdeburg.hayabaya.benchmarkers

import de.lin_magdeburg.hayabaya.datarelated.Datatypes

/**
  * Created by admin on 2/22/2016.
  */
trait BenchMarkTrait {
  val dataType: Datatypes
  def defaultMessage(): String = "BenchTrait message"
  def datatypeMessage(): String = s"I'm a $dataType kind of guy"

}
