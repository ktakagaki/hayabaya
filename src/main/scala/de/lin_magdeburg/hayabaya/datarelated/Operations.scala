package de.lin_magdeburg.hayabaya.datarelated

/**
  * Created by admin on 2/22/2016.
  */
sealed trait Operation { def name: String}

final case object ADD extends Operation { val name = "ADD"}
final case object SUBTRACT extends Operation { val name = "SUBTRACT"}
final case object DIVIDE extends Operation { val name = "DIVIDE"}
final case object MULTIPLY extends Operation { val name = "MULTIPLY"}
final case object LESSTHAN extends Operation { val name = "LESSTHAN"}
final case object GREATERTHAN extends Operation { val name = "GREATERTHAN"}
final case object EQUALS extends Operation { val name = "EQUALS"}
final case object NOTEQUAL extends Operation { val name = "NOTEQUAL"}


