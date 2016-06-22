package de.lin.hayabaya

import com.typesafe.scalalogging.LazyLogging
import de.lin.hayabaya.util.Util

/**
 * Main configuration of the project at runtime.
 * contains values for arraylengths, output paths, location of application.conf, profiling params,
 * CPU/system name and so forth.
 */
case class MainConfig(
  help: Option[String] = Some("false"),
  runtest: Option[Boolean] = Some(false),
  systemName: Option[String] = Some("i73770k"),
  minarraysize: Option[Int] = Some(2024),
  maxarraysize: Option[Int] = Some(4048),
  arraystepsize: Option[Int] = Some(8)
)

class Main() extends LazyLogging {

  import de.lin.hayabaya.Main._

}

object Main {
  //  val log = logger

  def main(args: Array[String]): Unit = {

    //    log.info("Launching Hayabaya")

    val displayHelp = Util.displayHelp(args)
    println("result of displayHelp is: " + displayHelp)
    if (displayHelp) Util.usage();
  }

  /*  /**
    * Parses and validates the CLI arguments, initializing the MainConfig file
    *
    * @param args command line arguments
    * @return MainConfig instance
    */
  def parseArgs(args: Array[String]): MainConfig = {
    var config = MainConfig(false)

    var i = 0 while (i < args.length) {
      if (args(i).startsWith("-") && i < args.length - 1) {
        args(i) match {
          case ("-h" | "--help") => usage()
          case ("-t" | "--test") => smallTestSample()
          case ("-c" | "--config") => ??? //ToDo: How to load a different conf file from path?
          case ("-n" | "--name") => config = config.copy(cpuname = Some(args(i + 1)))
          case ("-min" | "--minArraySize") => config = config.copy(minarraysize = Some(args(i + 1)))
          case ("-max" | "--maxArraySize") => config = config.copy(maxarraysize = Some(args(i + 1)))
          case ("-s" | "--stepSize") => config = config.copy(arraystepsize = Some(args(i + 1)))
          case _ => config = config.copy(error = true); println("Unknown CLI arg")
        } i = i + 2
      } else {
        i = i + 1
      }
    }

    config
  }*/
}

