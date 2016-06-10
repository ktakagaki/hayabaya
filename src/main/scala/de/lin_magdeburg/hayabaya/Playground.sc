import de.lin_magdeburg.hayabaya.datarelated.INT

val a = (1 to 10 by 2).toArray
//val b = {(a % 2) == 0}
10 % 2
a.forall(_ % 2 != 0)

val b = (2 to 12 by 2).toArray
b.forall(_ % 2 == 0)

a.isInstanceOf[Array[Int]]

val Array(valA, valB, valC) = Array(1, 2, 3)
println("Value of A, B, and C is " + valA + " and " + valC)

val dt = INT
println(dt)
