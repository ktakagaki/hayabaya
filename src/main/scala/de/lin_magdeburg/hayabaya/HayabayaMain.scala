package de.lin_magdeburg.hayabaya

import de.lin_magdeburg.hayabaya.benchmarkers.BenchmarkFactory
import de.lin_magdeburg.hayabaya.datarelated._
import de.lin_magdeburg.hayabaya.util._


/**
  * Start the comment here
  * and use the left star followed by a white space on every line
  *
  * Even on empty paragraph-break lines.
  *
  * Note that the * on each line is aligned with the second * in / star star so that the
  * left margin is on the same column on the first line and on the subsequent ones
  *
  * The closing scaladoc tag goes on its own separate line
  * an Example
  *
  * Calculate the square of the given number
  *
  */
object HayabayaMain {

  def main(args: Array[String]): Unit = {
    println("\n\n\t\t\t========== Welcome to Hayabaya ==========\n\n")

    val runConfig = new RunConfig(args)
    Util.sessionLogger.debug(s"after parsing CLI, got runConfig: $runConfig")

    val benchmarkClassList = DataTypes.allTypes.map{BenchmarkFactory.getBenchMarker(_)}
    Util.sessionLogger.debug("benchmarkClassList.length = " + benchmarkClassList.length)

    val msgList = benchmarkClassList.map(_.dataTypeMessage())
    msgList.foreach(println)
    println("\n\n\t\t\t========== End of Hayabaya ==========\n\n")

  }
}
