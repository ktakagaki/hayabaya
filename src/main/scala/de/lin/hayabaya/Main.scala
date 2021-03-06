package de.lin.hayabaya

import com.typesafe.scalalogging.LazyLogging

import de.lin.hayabaya.datarelated.MainConfig
import de.lin.hayabaya.util.Util

object Main extends LazyLogging {
  val usage: String =
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

  def main(args: Array[String]): Unit = {

    logger.info("Launching Hayabaya")

    logger.info(s"Launching Hayabaya with args: ${args.toString} \n" +
      s"and result of Util.displayHelp(args) is: ${Util.displayHelp(args)}")

    if (Util.displayHelp(args)) {
      print(usage)
      System.exit(0)
    } else {
      val mainConfig: MainConfig = Util.parseArgs(args)
      logger.info(s"mainConfig is now after parsing: ${mainConfig}")
    }

  }

  //  def parseArgs(args: Array[String]): MainConfig = {
  //    var config = MainConfig(false)
  //
  //    var i = 0 while (i < args.length) {
  //      if (args(i).startsWith("-") && i < args.length - 1) {
  //        args(i) match {
  //          case ("-h" | "--help") => usage()
  //          case ("-t" | "--test") => smallTestSample()
  //          case ("-c" | "--config") => ??? //ToDo: How to load a different conf file from path?
  //          case ("-n" | "--name") => config = config.copy(cpuname = Some(args(i + 1)))
  //          case ("-min" | "--minArraySize") => config = config.copy(minarraysize = Some(args(i + 1)))
  //          case ("-max" | "--maxArraySize") => config = config.copy(maxarraysize = Some(args(i + 1)))
  //          case ("-s" | "--stepSize") => config = config.copy(arraystepsize = Some(args(i + 1)))
  //          case _ => config = config.copy(error = true); println("Unknown CLI arg")
  //        } i = i + 2
  //      } else {
  //        i = i + 1
  //      }
  //    }
  //
  //    config
  //  }
}

