package de.lin_magdeburg.hayabaya.benchmarkers

import de.lin_magdeburg.hayabaya.datarelated.{INT, Datatypes}

/**
  * Created by admin on 2/22/2016.
  */
object BenchMarkFactory {

  def getBenchMarker(dataType: Datatypes) = dataType match {
    case INT => new IntegerBenchmark
    case _ => println("Undetermined datatype for Benchmark")
  }

}
