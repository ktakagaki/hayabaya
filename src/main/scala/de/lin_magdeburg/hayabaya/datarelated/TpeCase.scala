package de.lin_magdeburg.hayabaya.datarelated

/**
  * Created by admin on 1/22/2016.
  */
object TpeCase {

  lazy val integerBits = 32
  lazy val integerMinValue = -2147483648
  lazy val integerMaxValue = 2147483647
  val typesOfTypes = Seq(INTEGER, INTEGER_BOXED,
    FLOAT, FLOAT_BOXED,
    DOUBLE, DOUBLE_BOXED,
    LONG, LONG_BOXED)

  sealed trait EnumVal

  case object INTEGER extends EnumVal

  case object INTEGER_BOXED extends EnumVal

  case object FLOAT extends EnumVal

  case object FLOAT_BOXED extends EnumVal

  case object DOUBLE extends EnumVal

  case object DOUBLE_BOXED extends EnumVal

  case object LONG extends EnumVal

  case object LONG_BOXED extends EnumVal

}
