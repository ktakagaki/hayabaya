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