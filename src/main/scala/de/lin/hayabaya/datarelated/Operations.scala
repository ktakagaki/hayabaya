package de.lin.hayabaya.datarelated

/**
  * The operations that are available in Hayabaya
  * <ul>
  * <li><b>ADD:</b> Addition</li>
  * <li><b>SUBTRACT:</b> Subtraction</li>
  * <li><b>MULTIPLY:</b> Multiplication</li>
  * <li><b>DIVIDE:</b> Division</li>
  * <li><b>LESSTHAN:</b> a less than b comparison </li>
  * <li><b>GREATERTHAN:</b> a greater than b comparison </li>
  * <li><b>EQUALS:</b> \= equality comparison </li>
  * <li><b>NOTEQUAL:</b> \!\= non equality comparison </li>
  * </ul>
  */
sealed trait Operation {
  def name: String
}

final case object ADD extends Operation {
  val name = "ADD"
}

final case object SUBTRACT extends Operation {
  val name = "SUBTRACT"
}

final case object DIVIDE extends Operation {
  val name = "DIVIDE"
}

final case object MULTIPLY extends Operation {
  val name = "MULTIPLY"
}

final case object LESSTHAN extends Operation {
  val name = "LESSTHAN"
}

final case object GREATERTHAN extends Operation {
  val name = "GREATERTHAN"
}

final case object EQUALS extends Operation {
  val name = "EQUALS"
}

final case object NOTEQUAL extends Operation {
  val name = "NOTEQUAL"
}

object Operation {
  val arithmeticOperations = List(ADD, SUBTRACT, DIVIDE, MULTIPLY)
  val comparisonOperations = List(LESSTHAN, GREATERTHAN, EQUALS, NOTEQUAL)
  val allOperations = arithmeticOperations ::: comparisonOperations
}


