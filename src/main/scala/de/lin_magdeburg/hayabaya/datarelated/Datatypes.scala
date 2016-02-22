package de.lin_magdeburg.hayabaya.datarelated

sealed trait Datatypes { def name: String}

final case object INT extends Datatypes { val name = "INT"}
final case object INTEGER_BOXED extends Datatypes { val name = "INTEGER_BOXED"}
final case object FLOAT extends Datatypes { val name = "FLOAT"}
final case object FLOAT_BOXED extends Datatypes { val name = "FLOAT_BOXED"}
final case object DOUBLE extends Datatypes { val name = "DOUBLE"}
final case object DOUBLE_BOXED extends Datatypes { val name = "DOUBLE_BOXED"}
final case object LONG extends Datatypes { val name = "LONG"}
final case object LONG_BOXED extends Datatypes { val name = "LONG_BOXED"}
