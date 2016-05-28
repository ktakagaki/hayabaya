def logb(base: Double, number: Double): Int = {
  import scala.math.log10
  val res = log10(number) / log10(base)
  res.toInt
}

logb(2, 512)
logb(2, 16)
logb(2, 1024)
