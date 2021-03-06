package de.lin.hayabaya.util

import com.typesafe.scalalogging.LazyLogging
import de.lin.hayabaya.datarelated.MainConfig

/**
 * Created by admin on 15/01/2016.
 */
object Util extends LazyLogging {

  /**
   * Check if conditions for displaying help are satisfied, e.g. no args, invalid args
   *
   * @param args command line arguments
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

  /**
   * Calculate the log_b(x) for any base "b".
   */
  def logb(base: Double, number: Double): Int = {
    import scala.math.log10
    val res = log10(number) / log10(base)
    res.toInt
  }

  /**
   * parse the arguments from cli. run a small test (the defaults) or
   * parse all of the arguments
   * @param args command line arguments
   * @return MainConfig instance
   */
  def parseArgs(args: Array[String]): MainConfig = {
    var result = MainConfig()
    args match {
      case Array("-rt" | "--runTest") => MainConfig()
      case _ => println("Unknown case"); MainConfig()
    }
  }

  def getArgPosition(implicit args: Array[String], flags: String*): Int = {
    val pos = args.indexOf(flags)
    println(s"Position of flag is $pos ")
    pos
  }

}

