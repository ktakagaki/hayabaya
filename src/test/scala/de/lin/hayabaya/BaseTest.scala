package de.lin.hayabaya

import org.scalatest._

/**
  * Created by cain on 09/05/16.
abstract class BaseTest extends FlatSpec with Matchers with OptionValues with Inside with Inspectors
*/
abstract class UnitSpec extends FlatSpec with Matchers with
  OptionValues with Inside with Inspectors

class BaseTest extends UnitSpec {

}
