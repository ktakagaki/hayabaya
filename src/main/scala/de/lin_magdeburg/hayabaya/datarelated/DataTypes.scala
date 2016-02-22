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
sealed trait DataTypes { def name: String}

final case object INT extends DataTypes { val name = "INT"}
final case object INTEGER_BOXED extends DataTypes { val name = "INTEGER_BOXED"}
final case object FLOAT extends DataTypes { val name = "FLOAT"}
final case object FLOAT_BOXED extends DataTypes { val name = "FLOAT_BOXED"}
final case object DOUBLE extends DataTypes { val name = "DOUBLE"}
final case object DOUBLE_BOXED extends DataTypes { val name = "DOUBLE_BOXED"}
final case object LONG extends DataTypes { val name = "LONG"}
final case object LONG_BOXED extends DataTypes { val name = "LONG_BOXED"}

object DataTypes{
  val primitiveTypes = List(INT, FLOAT, DOUBLE, LONG)
  val boxedTypes = List(INTEGER_BOXED, FLOAT_BOXED, DOUBLE_BOXED, LONG_BOXED)
  val allTypes = primitiveTypes ::: boxedTypes
}
