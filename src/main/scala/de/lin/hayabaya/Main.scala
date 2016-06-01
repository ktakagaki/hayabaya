package de.lin.hayabaya


import com.typesafe.config.ConfigFactory


/**
 * Main configuration of the project at runtime.
 * contains values for arraylengths, output paths, location of application.conf, profiling params,
 * CPU/system name and so forth.
 */
case class MainConfig(error: Boolean,
  help: Option[String] = None,
  runsmalltest: Boolean = false,
  cpuname: Option[String] = None,
  minarraysize: Option[Int] = None,
  maxarraysize: Option[Int] = None,
  arraystepsize: Option[Int] = None,
  pathtoconfigfile: Option[String] = None)

/**
 * Mainclass loading the CLI args, validating them, parsing them to other parts of code that
 * then executes the jobs. Once completed Main will assemble the results and write them to disk.
 */
object Main {

  /**
   * Usage flags for Hayabaya, their -fFN and --flagFullName versions, and an explanation of
   * what they flags are to be used for. If no flags are given, Hayabaya prints out the help
   * dialog. For convenience that is a smalltest flag users can give to just run a quick and
   * dirty small testsample.
   */
  private def usage() = {
    println("\n \t\t ===== Welcome to Hayabaya ===== ")
    println("Usage: Hayabaya -options [argument type]")
    println("============================================")
    println("-h,   --help               Prints this help message")
    println("-t,   --test               Runs a predefined small test sample")
    println("-c,   --config [FILE]      Configuration file (if none given, default is used)")
    println("-n,   --name [STRING]      Name of the CPU and/or Computer System")
    println("-min, --minArraySize [INT] The minimum array size to profile")
    println("-max, --maxArraySize [INT] The maximum array size to profile")
    println("-s,   --stepSize [INT]     The stepsize/stride to be used in the [min,max] array")
    println("============================================")
  }



  //ToDo: Write and implement a small runsmalltest function
  def smallTestSample() = {
    ???
  }

  /**
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
  }


  def main(args: Array[String]): Unit = {

    val config = parseArgs(args)

    val exitVal = {
      if (config.error) {
        1
      } else {
        if (execute(config)) 1 else 0
      }
    }

    System.exit(exitVal)
  }

  private[this] def execute(mc: MainConfig): Boolean = {
    ???
  }
}

