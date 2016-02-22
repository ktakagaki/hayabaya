package de.lin_magdeburg.hayabaya

import de.lin_magdeburg.hayabaya.benchmarkers.BenchMarkFactory
import de.lin_magdeburg.hayabaya.datarelated.INT
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
  * Calculate the swuare of the given number
  *
  */
object HayabayaMain {
  def main(args: Array[String]): Unit = {

    println("\t\t========== Welcome to Hayabaya ==========")

    val runConfig = new RunConfig(args, Array(1,2,3))
    Util.sessionLogger.debug(s"got ${args.mkString(" ")} and ended with $runConfig after parsing")

    val testBenchmark = BenchMarkFactory.getBenchMarker(INT)
    println("Testing the returned type from benchmarkfactory")

    val testmsg = testBenchmark.
    println(testBenchmark.dataTypeMessage)




    /*    if(args == null) Util.sessionLogger.error("have to give command line arguments!")
        if(args.length < 2) Util.sessionLogger.error(s"must have more than 3 command line arguments, only ${args.length} arguments given!")

        val arg0 = args(0)
        val arg1 =
          try{
            args(1).toInt
          }catch{
            case e: Throwable => {
              println(s"trying to parse arg1 to int threw the following:$e")
              777 // <= this is the default value
            }
          }

        println(s"Hi Soren! Don't get too fancy with tuples, and parsing will be fine. Parsed: $arg0, $arg1")
        */


  }
}

// ========== Example of using Logging ================== //
/*Util.sessionLogger.trace("This is a trace, not important")
Util.sessionLogger.error("Error, something bad has happened!")
Util.sessionLogger.warn("Warning, are you sure?")
Util.sessionLogger.info("Initializing hayabaya")*/


// ========== Example of Throwing and Catching Exceptions ================== //
/*    try {
      Util.throwErrorForTesting
    } catch {
        case e: IllegalArgumentException => Util.sessionLogger.error("caught error", e)
        case _ => println("didn't recongize error")
    }*/
