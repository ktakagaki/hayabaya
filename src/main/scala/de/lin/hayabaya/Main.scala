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

  /**
   * Always be case insensitive when evaluating strings for "true"
   *
   * @param s
   * @return true if string matches "true", "True", "TRUE" and so forth
   */
  private def isTrue(s: String): Boolean = {
    "true".equalsIgnoreCase(s)
  }


  def parseArgs(args: Array[String]): MainConfig = {
    var config = MainConfig(false)

    var i = 0
    while (i < args.length) {
      if (args(i).startsWith("-") && i < args.length - 1) {
        args(i) match {
          case ("-h" | "--help") => usage()
          case ("-t" | "--test") => config = config.copy()


          case ("-v" | "--verbose") => config = config.copy(verbose = isTrue(args(i + 1)))
          case ("-q" | "--quiet") => config = config.copy(quiet = isTrue(args(i + 1)))
          case ("-w" | "--warnings") => config = config.copy(warningsaserrors = isTrue(args(i + 1)))
          case ("--xmlOutput") => config = config.copy(xmlFile = Some(args(i + 1)))
          case ("--xmlEncoding") => config = config.copy(xmlEncoding = Some(args(i + 1)))
          case ("--inputEncoding") => config = config.copy(inputEncoding = Some(args(i + 1)))
          case ("-e" | "--externalJar") => config = config.copy(externalJar = Some(args(i + 1)))
          case ("-x" | "--excludedFiles") => config = config.copy(excludedFiles = args(i + 1).split(";"))
          case _ => config = config.copy(error = true)
        }
        i = i + 2
      } else {
        config = config.copy(directories = args(i) :: config.directories)
        i = i + 1
      }
    }

    if (!config.config.isDefined || config.directories.size == 0) {
      config = config.copy(error = true)
    }

    config
  }

  def main(args: Array[String]): Unit = {
    usage()

  }
}

