package de.lin.hayabaya.util

import com.typesafe.scalalogging.LazyLogging

/**
 * Created by admin on 15/01/2016.
 */
object Util {

  /**
   * Check if conditions for displaying help are satisfied, e.g. no args, invalid args
   * @return Boolean True if the conditions for displaying help are met
   */
  def displayHelp[T](args: Array[T]): Boolean = {

    val conditions: Boolean = if (args.length == 0) {
      true
    } else if (args.length != 1 && args.length != 4) {
      true
    } else {
      false
    }

    conditions
  }

  def usage(): Unit = {
    println("\n \t\t ===== Welcome to Hayabaya ===== ")
    println("Usage: Hayabaya -options [argument type]")
    println("============================================")
    println("-h,   --help            Prints this help message")
    println("-rt,  --runTest        Runs a predefined small test example")
    println("-sn,  --systemName     The name of the CPU/System running Hayabaya on")
    println("-min, --minArraySize   The minimum array size to profile")
    println("-max, --maxArraySize   The maximum array size to profile")
    println("-s,   --stepSize       The stepsize increment the arraysize with (default=" + "32)")
    println("============================================")
  }

  /**
   * Calculate the log_b(x) for any base "b".
   */
  def logb(base: Double, number: Double): Int = {
    import scala.math.log10
    val res = log10(number) / log10(base)
    res.toInt
  }

  /**
   * Always be case insensitive when evaluating strings for "true"
   *
   * @param s string being true,True,TRUE,TruE etc.
   * @return true if string matches "true", "True", "TRUE" and so forth
   */
  private def isTrue(s: String): Boolean = {
    "true".equalsIgnoreCase(s)
  }

}
