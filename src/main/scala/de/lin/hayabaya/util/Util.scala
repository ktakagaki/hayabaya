package de.lin.hayabaya.util

import com.typesafe.scalalogging.LazyLogging

/**
 * Created by admin on 15/01/2016.
 */
object Util {

  /**
   * Check if conditions for displaying help are satisfied, e.g. no args, invalid args
   *
   * @return Boolean True if the conditions for displaying help are met
   */
  def displayHelp[T](args: Array[T]): Boolean = args match {
    case Array() => true
    case Array("-h") => true
    case Array("--help") => true
    case Array("-rt") => false
    case Array("--runTest") => false
    case _ if (args.length != 4) => true
    case _ => false

  }

  def printUsage(): Unit = {
    val usageText =
      """
        |===== Welcome to Hayabaya =====
        |Usage:
        |-arg, --fullargName    description
        |===============================
        |-h,   --help           Prints this help message
        |-rt,  --runTest        Runs a predefined small test example
        |-sn,  --systemName     The name of the CPU/System running Hayabaya on
        |-min, --minArraySize   The minimum array size to profile
        |-max, --maxArraySize   The maximum array size to profile
        |-s,   --stepSize       The stride to increment arraysize with (default = 32)
      """.stripMargin
    println(usageText)
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
