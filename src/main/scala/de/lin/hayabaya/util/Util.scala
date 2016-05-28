package de.lin.hayabaya.util

import com.typesafe.scalalogging.LazyLogging



/**
 * Created by admin on 15/01/2016.
 */
object Util extends LazyLogging {

  val log = logger

  /**
    * Calculate the log_b(x) for any base "b".
    */
  def logb(base: Double, number: Double): Int = {
    import scala.math.log10
    val res = log10(number) / log10(base)
    res.toInt
  }

}
