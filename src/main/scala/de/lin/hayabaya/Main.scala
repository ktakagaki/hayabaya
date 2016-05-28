package de.lin.hayabaya

/**
 * Main configuration of the project at runtime.
 * contains values for arraylengths, output paths, location of application.conf, profiling params,
 * CPU/system name and so forth.
 */
case class MainConfig(error: Boolean,
  help: Option[String],
  runsmalltest: Boolean,
  cpuname: Option[String],
  minarraysize: Option[Int],
  maxarraysize: Option[Int],
  arraystepsize: Option[Int],
  pathtoconfigfile: Option[String])

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
    var config = MainConfig(false) // initialize error to false
    var i = 0

    while (i < args.length) {
      if (args(i).startsWith("-") && i < (args.length - 1)) {
        args(i) match {
          case ("-h" | "--help") => usage()

        }
      }
    }
  }

  def main(args: Array[String]): Unit = {
    usage()
  }
}

