package de.lin_magdeburg.hayabaya

import scala.util.Try


/**
  * Created by cain on 1/9/16.
  */
object HayabayaMain /*extends App*/ {

  def main(args: Array[String]): Unit = {
    println("Hello, world!")
    Util.sessionLogger.info("Initializing hayabaya")
    Util.sessionLogger.trace("This is a trace, not important")
    Util.sessionLogger.error("Error, something bad has happened!")
    Util.sessionLogger.warn("Warning, are you sure?")

    try {
      Util.throwError
    } catch {
        case e: IllegalArgumentException => Util.sessionLogger.error("caught error", e)
        case _ => println("didn't recongize error")
    }
  }




}
