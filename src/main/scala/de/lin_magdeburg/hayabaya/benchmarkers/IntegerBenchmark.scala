package de.lin_magdeburg.hayabaya.benchmarkers

import de.lin_magdeburg.hayabaya.datarelated.{INT, Datatypes}

/**
  * Created by admin on 2/22/2016.
  */
class IntegerBenchmark() extends BenchMarkTrait {
  final val dataType = INT
  override def datatypeMessage(): String = s"I'm a $dataType kind of guy"

}
