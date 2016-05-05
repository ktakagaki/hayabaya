package de.lin_magdeburg.hayabaya

import com.typesafe.config._

/**
  * Loads and validates settings for hayabaya in the resources directory
  *
  */
class HayabayaConfig(config: Config) {
  import HayabayaConfig._

  config.checkValid(ConfigFactory.defaultReference(), "runsettings")

  // Use standard default runsetting configurations from application.conf if
  // user has not provided any and accepts using defaults
  def this() {
    this(ConfigFactory.load())
  }


  val name = validateName(config.getString("runsettings.CPUName"))
  val repetitions = config.getInt("runsettings.repetitions")
  val arraySizes = config.getIntList("runsettings.arraysizes")


  def printSetting(): Unit = {
    println("name =  " + name)
    println("repetitions = " + repetitions)
    println("arraysizes = " + arraySizes)
  }

//  val validated = validateName(name)
}

object HayabayaConfig {
  def validateName(name: String): Option[String] = {

    if (name == "Inteli7") {
      Some(name)
    }
    else {
      Some("InvalidNameGiven")
    }
  }
}
