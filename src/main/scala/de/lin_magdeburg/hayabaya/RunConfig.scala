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

    val arraySizes: Array[Int] = parseArrayCLIArgs(runArgs)
    val repetitions: Int = parseCLIRepetitions(runArgs)
    val CPUName: String = parseCPUNameCLIArgs(runArgs)



  def parseArrayCLIArgs(runArgs: Array[String]): Array[Int] = {
    if (runArgs.length == 0) {
      Array(1, 2, 3)
    }
    else {
      (runArgs(1).toInt to runArgs(2).toInt by runArgs(3).toInt).toArray
    }
  }

  def parseCLIRepetitions(runArgs: Array[String]): Int = {
    if (runArgs.length == 0) {
      3
    }
    else {
      runArgs(4).toInt
    }
  }

  def parseCPUNameCLIArgs(runArgs: Array[String]): String = {
    if (runArgs.length == 0) {
      "test"
    }
    else {
      runArgs(0).toString
    }
  }

  def validatArraysFactorTwo(anArray: Array[Int]): Boolean = {
    assert(anArray.isInstanceOf[Array[Int]])
    anArray.forall(_ % 2 == 0)
  }

  override def toString: String = s"arraySizes: $arraySizes, repetitions: $repetitions" +
    s", CPUName: $CPUName"

}

