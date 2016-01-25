package de.lin_magdeburg.hayabaya.datarelated

/**
  * The data types available in the Hayabaya project
  * <ul>
  * <li><b>INT</b> Integer</li>
  * <li><b>INTEGER_BOXED</b> Boxed Integer</li>
  * <li><b>FLOAT</b> Float</li>
  * <li><b>FLOAT_BOXED</b> Boxed Float</li>
  * <li><b>DOUBLE</b> Double</li>
  * <li><b>DOUBLE_BOXED</b> Boxed Double</li>
  * <li><b>LONG</b> Long</li>
  * <li><b>LONG_BOXED</b> Boxed Long</li>
  * </ul>
  */
object Tpe extends Enumeration {
  type Tpe = Value
  val INT = Value("INT")
  val INTEGER_BOXED = Value("INTEGER_BOXED")
  val FLOAT = Value("FLOAT")
  val FLOAT_BOXED = Value("FLOAT_BOXED")
  val DOUBLE = Value("DOUBLE")
  val DOUBLE_BOXED = Value("DOUBLE_BOXED")
  val LONG = Value("LONG")
  val LONG_BOXED = Value("LONG_BOXED")

}
