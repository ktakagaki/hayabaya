package de.lin.hayabaya


import org.scalatest.{FlatSpec, FunSuite}
import org.scalatest.BeforeAndAfter
import de.lin.hayabaya._
import de.lin.hayabaya.datarelated.MainConfig

/**
  * Created by cain on 23/06/16.
  */
class Main$Test extends FunSuite with BeforeAndAfter {

  var mainConfig: MainConfig = _
  val usageMessage: String = Main.usage;

  before {
    mainConfig = MainConfig()
  }

  test("When calling hayabaya without any args, it should display usage") {

    assert(Main.usage === usageMessage, "The usage message is not correct or not printed")
  }

  test("When calling with illegal args, or wrong number of args usage is displayed") {

    assert(Main.usage === usageMessage, "The usage message is not correct or not printed")
  }

}
