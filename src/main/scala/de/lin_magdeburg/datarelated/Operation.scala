package de.lin_magdeburg.datarelated

/**
  * Created by cain on 1/9/16.
  */
object Operation extends Enumeration {
  type Operation = Value
  val ADD = Value("ADD")
  val SUBTRACT = Value("SUBTRACT")
  val MULTIPLY = Value("MULTIPLY")
  val DIVIDE = Value("DIVIDE")
  val LESSTHAN = Value("LESSTHAN")
  val GREATERTHAN = Value("GREATHERTHAN")
  val EQUALS = Value("EQUALS")
  val NOTEQUAL = Value("NOTEQUAL")

}
