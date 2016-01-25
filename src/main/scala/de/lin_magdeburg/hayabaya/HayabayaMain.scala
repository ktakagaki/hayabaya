package de.lin_magdeburg.hayabaya

import de.lin_magdeburg.hayabaya.datarelated._


/**
  * Created by cain on 1/9/16.
  */
object HayabayaMain {

  def main(args: Array[String]): Unit = {
    Util.sessionLogger.debug("Starting Hayabaya Main Class")
    println("[In Scala] Welcome to the Hayabaya Microbenchmark project")

    val aTPE = Tpe.DOUBLE

    println("The value of aTPE is: " + aTPE)

    if(args == null) Util.sessionLogger.error("have to give command line arguments!")
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