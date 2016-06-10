package de.lin_magdeburg.hayabaya.benchmarkers

import de.lin_magdeburg.hayabaya.datarelated._

/**
 * Created by admin on 2/22/2016.
 */
object BenchmarkFactory {

  def getBenchMarker(dataType: DataTypes) = dataType match {
    case INT => new IntegerBenchmark(INT)
    case INTEGER_BOXED => new IntegerBoxedBenchmark(INTEGER_BOXED)
    case FLOAT => new FloatBenchmark(FLOAT)
    case FLOAT_BOXED => new FloatBoxedBenchmark(FLOAT_BOXED)
    case DOUBLE => new DoubleBenchmark(DOUBLE)
    case DOUBLE_BOXED => new DoubleBoxedBenchmark(DOUBLE_BOXED)
    case LONG => new LongBenchmark(LONG)
    case LONG_BOXED => new LongBoxedBenchmark(LONG_BOXED)
    case _ => throw new IllegalArgumentException("Unknown datatype")
  }

}
