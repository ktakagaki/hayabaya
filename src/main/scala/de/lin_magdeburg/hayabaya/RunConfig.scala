package de.lin_magdeburg.hayabaya

import de.lin_magdeburg.hayabaya.util.Util

/**
  *
  * For class primary constructors which in Scala coincide with the definition of the class itself,
  * a @constructor tag is used to target a comment to be put on the primary
  * constructor documentation rather than the class overview.
  *
  * @param lengthsToTest Array of the lengths of arrays that will be tested, values (from, to, by)
  * @param repetitions   the total number of times the entire run will be repeated
  * @param cpuName       The name of the CPU/System being benchmarked
  * @param debug         boolean variable indicating debug level
  *
  *
  *
  */
class RunConfig(runArgs: Array[String], val lengthsToTest: Array[Int], repetitions: Int = 3, cpuName: String = "testCPU",
                debug: Boolean = false) {

  def this(runArgs: Array[String]) {
    this(runArgs: Array[String])
  }

  Util.sessionLogger.debug("test")

  val exitStatus = if (runArgs.length == 0) { 0  }
  else  {
    println("entered the else-part of RunConfig.mainconstructor")
    parseCLIArguments
  }

  def parseCLIArguments = 1 // 1 for bad init, 0 for sucess

  def validatArraysFactorTwo(anArray: Array[Int]): Boolean = {
    assert(anArray.isInstanceOf[Array[Int]])
    anArray.forall(_ % 2 == 0)
  }


}