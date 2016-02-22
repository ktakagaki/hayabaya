package de.lin_magdeburg.hayabaya

import de.lin_magdeburg.hayabaya.util.Util

/**
  *Encapsulation of the runconfiguration for Hayabaya. When instantiating the class, it
  *  parsing functions will be called ensuring that arguments are not illegal. In case
  *  no arguments are passed (runArgs.length == 0), the class will provide default
  *  values instead of parsing the args.
  *
  *
  * @param runArgs Array[String] The CLI arguments provided from MainClass
  */
class RunConfig(runArgs: Array[String]) {

  if (runArgs.length == 0){
    val arraySizes: Array[Int] = Array(1, 2, 3)
    val repetitions: Int = 3
    val CPUName: String = "testCPU"
  }
  if (runArgs.length == 3) {
    val arraySizes: Array[Int] = parseArrayCLIArgs(runArgs)
    val repetitions: Int = parseCLIRepetitions(runArgs)
    val CPUName: String = parseCPUNameCLIArgs(runArgs)
  }
  else {
    throw new IllegalArgumentException("Illegal CLI arguments, length == 3 or 0")
  }


  def parseArrayCLIArgs(runArgs: Array[String]): Array[Int] = Array(1, 2, 3)
  def parseCLIRepetitions(runArgs: Array[String]): Int = 3
  def parseCPUNameCLIArgs(runArgs: Array[String]): String = "test"

  def validatArraysFactorTwo(anArray: Array[Int]): Boolean = {
    assert(anArray.isInstanceOf[Array[Int]])
    anArray.forall(_ % 2 == 0)
  }

}

