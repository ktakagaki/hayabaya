/**
  * Created by ktakagaki on 15/12/09.
  */
object Test {

  import java.util.Random

  val max = 10000000
  val r = new Random()
  var start = 0L
  var end = 0L

  def overwriterI(): String = {
    var arrayI = Array.tabulate(max)((i: Int) => r.nextInt());
    var c = 0
    start = System.nanoTime()
    while (c < max) {
      arrayI(c) = r.nextInt()
      c += 1
    }
    end = System.nanoTime()
    s"#Int ov: *${(end - start) / 1000000} ms   (" + arrayI((r.nextDouble() * max).toInt) + ")"
  }

  def adderI(): String = {
    var arrayI = Array.tabulate(max)( (i: Int) => r.nextInt() );
    var c = 0
    start = System.nanoTime()
    while(c < max) {
      arrayI(c) = arrayI(c) + r.nextInt()
      c += 1
    }
    end = System.nanoTime()
    s"#Int add: ${(end - start) / 1000000} ms   (" + arrayI((r.nextDouble() * max).toInt)+ ")"
  }

  def dividerI(): String = {
    var arrayI = Array.tabulate(max)( (i: Int) => ((r.nextDouble() +1d)/2* Int.MaxValue).toInt );
    var c = 0
    start = System.nanoTime()
    while(c < max) {
      arrayI(c) = arrayI(c) / r.nextInt()
      c += 1
    }
    end = System.nanoTime()
    s"#Int div: ${(end - start) / 1000000} ms   (" + arrayI((r.nextDouble() * max).toInt)+ ")"
  }
  def overwriterD(): String ={
    var arrayD = Array.tabulate(max)(
      (i: Int) => r.nextDouble() + Math.pow(10d, r.nextDouble()*10)
    );
    var c = 0
    start = System.nanoTime()
    while(c < max){
      arrayD(c) = r.nextDouble()
      c += 1
    }
    end = System.nanoTime()
    s"#Double ov: *${(end - start) / 1000000} ms   (" + arrayD((r.nextDouble() * max).toInt)+ ")"
  }
  def adderD(): String = {
    var arrayD = Array.tabulate(max)(
      (i: Int) => r.nextDouble() + Math.pow(10d, r.nextDouble()*10)
    );
    var c = 0
    start = System.nanoTime()
    while(c < max) {
      arrayD(c) = arrayD(c) + r.nextDouble()
      c += 1
    }
    end = System.nanoTime()
    s"#Double add: ${(end - start) / 1000000} ms   (" + arrayD((r.nextDouble() * max).toInt)+ ")"
  }
  def dividerD(): String = {
    var arrayD = Array.tabulate(max)(
      (i: Int) => r.nextDouble() + Math.pow(10d, r.nextDouble()*10)
    );
    var c = 0
    start = System.nanoTime()
    while(c < max) {
      arrayD(c) = arrayD(c) / r.nextDouble()
      c += 1
    }
    end = System.nanoTime()
    s"#Double div: ${(end - start) / 1000000} ms   (" + arrayD((r.nextDouble() * max).toInt)+ ")"
  }

}
