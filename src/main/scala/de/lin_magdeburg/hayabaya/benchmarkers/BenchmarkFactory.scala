package de.lin_magdeburg.hayabaya.benchmarkers

import de.lin_magdeburg.hayabaya.datarelated._
import de.lin_magdeburg.hayabaya.benchmarkers._

/**
  * Created by admin on 2/22/2016.
  */
object BenchmarkFactory {

  def getBenchMarker(dataType: Datatypes) = dataType match {
    case INT => new IntegerBenchmark(INT)
    case INTEGER_BOXED => new IntegerBoxedBenchmark(INTEGER_BOXED)
    case FLOAT => new FloatBenchmark(FLOAT)
    case FLOAT_BOXED => FloatBoxedBenchmark(FLOAT_BOXED)
    case DOUBLE => DoubleBenchmark(DOUBLE)
    case DOUBLE_BOXED => DoubleBoxedBenchmark(DOUBLE_BOXED)
    case LONG => LongBenchmark(LONG)
    case LONG_BOXED(LONG_BOXED)
    case _ => throw new IllegalArgumentException("Unknown datatype")
  }

}
