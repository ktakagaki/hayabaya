package de.lin_magdeburg.hayabaya

import de.lin_magdeburg.hayabaya.benchmarkers.BenchmarkFactory
import de.lin_magdeburg.hayabaya.datarelated._
import de.lin_magdeburg.hayabaya.util._


/**
  * Hayabaya is a microbenchmark written in Scala/Java for benchmarking the performance
  * of basic arithmetic operations on the standard java data types.
  */
object HayabayaMain {

  def main(args: Array[String]): Unit = {
    println("\t Starting Hayabaya")

    val ConfSettings = new HayabayaConfig()
    ConfSettings.printSetting()


  }


/*  def main(args: Array[String]): Unit = {
    println("\n\t\t\t========== Welcome to Hayabaya ==========\n")

    val runConfig = new RunConfig(args)
    Util.log.debug(s"after parsing CLI, got runConfig: $runConfig")

    val benchmarkClassList = DataTypes.allTypes.map{BenchmarkFactory.getBenchMarker(_)}
    Util.log.debug("benchmarkClassList.length = " + benchmarkClassList.length)

    val msgList = benchmarkClassList.map(_.dataTypeMessage())
    msgList.foreach(println)
    println("\n\t\t\t========== End of Hayabaya ==========\n")

  }*/
}
