package de.lin.hayabaya

/**
 * Main configuration of the project at runtime, contains values for arraylengths, output dir
 * paths, where to read any application.conf configuration file, number of repetitions, name of
 * CPU/system and so forth.
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
    println("Usage: Hayabaya [options] <source directory>")
    println(" -h,   --help               Prints out this help message")
    println(" -t,   --test               Runs a default small test sample for testing purposes")
    println(" -c,   --config FILE        Configuration file (if NONE, defaults to internal default")
    println(" -n,   --name STRING        Name of the CPU and or System Hayabaya is profiling")
    println(" -min, --minArraySize INT   The starting array size to profile")
    println(" -max, --maxArraySize INT   The end size of the array to profile")
    println(" -s,   --stepSize INT       The step size / stride to be used in the [min,max] array")
  }


  def main(args: Array[String]): Unit = {
    usage()
  }
}

