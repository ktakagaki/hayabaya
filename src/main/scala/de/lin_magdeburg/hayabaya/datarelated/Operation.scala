package de.lin_magdeburg.hayabaya.datarelated

/**
  * The operations that are available in Hayabaya
  * <ul>
  * <li><b>ADD:</b> Addition</li>
  * <li><b>SUBTRACT:</b> Subtraction</li>
  * <li><b>MULTIPLY:</b> Multiplication</li>
  * <li><b>DIVIDE:</b> Division</li>
  * <li><b>LESSTHAN:</b> \< a less than b comparison </li>
  * <li><b>GREATERTHAN:</b> \> a greater than b comparison </li>
  * <li><b>EQUALS:</b> \= equality comparison </li>
  * <li><b>NOTEQUAL:</b> \!\= non equality comparison </li>
  * </ul>
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
