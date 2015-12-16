/**
  * Created by ktakagaki on 15/12/09.
  */
object Test {

  import java.util.Random

  val max = 100000
  val reps = 1000000000

  // <editor-fold defaultstate="collapsed" desc=" loop functions ">

  val r = new Random()
  var start = 0L //for timing
  var end = 0L //for timing

  /**Standard operations common to all other arithmetic operations
    */
  def overwriterI(): (Long, Array[Int]) = {
    var arrayI = Array.tabulate(max)((i: Int) => r.nextInt()); //Random array, not too long
    var c = 0
    var rep = 0
    start = System.nanoTime()
    while (rep < reps) {
      while (c < max) {
        val curr = r.nextInt() //Double random number excludes algebraic simplifications
        val next = r.nextInt()
        arrayI(c) = if( next < curr ) next else curr //This conditional allocation prevents overflow, etc.
        c += 1
        }
      rep += 1
    }
    end = System.nanoTime()
    ( (end - start),  arrayI ) //The return of the variables make arrayI necessary to calculate
  }

  /**add + standard operations
    */
  def adderI(): (Long, Array[Int]) = {
    var arrayI = Array.tabulate(max)((i: Int) => r.nextInt()); //Random array, not too long
    var c = 0
    var rep = 0
    start = System.nanoTime()
    while (rep < reps) {
      while (c < max) {
        val curr = arrayI(c) + r.nextInt() //Double random number excludes algebraic simplifications
        val next = r.nextInt()
        arrayI(c) = if (next < curr) next else curr //This conditional allocation prevents overflow, etc.
        c += 1
      }
      rep += 1
    }
    end = System.nanoTime()
    ( (end - start),  arrayI ) //The return of the variables make arrayI necessary to calculate
  }

  /**divide + standard operations
    */
  def dividerI(): (Long, Array[Int]) = {
    var arrayI = Array.tabulate(max)((i: Int) => r.nextInt()); //Random array, not too long
    var c = 0
    var rep = 0
    start = System.nanoTime()
    while (rep < reps) {
      while (c < max) {
        val curr = r.nextInt() / arrayI(c) //Double random number excludes algebraic simplifications
        val next = r.nextInt()
        arrayI(c) = if( next < curr ) curr else next //This conditional allocation prevents overflow, etc.
        c += 1
      }
      rep += 1
    }
    end = System.nanoTime()
    ( (end - start),  arrayI ) //The return of the variables make arrayI necessary to calculate
  }

  /**Double within reasonable range*/
  def randomDouble() = (r.nextDouble()-0.5) * Math.pow(10d, r.nextDouble()*7)

  /**Standard operations common to all other arithmetic operations
    */
  def overwriterD(): (Long, Array[Double]) = {
    var arrayD = Array.tabulate(max)(  (i: Int) => randomDouble()   )
    var c = 0
    var rep = 0
    start = System.nanoTime()
    while (rep < reps) {
      while (c < max) {
        val curr = randomDouble() //Double random number excludes algebraic simplifications
        val next = randomDouble()
        arrayD(c) = if( next < curr ) next else curr //This conditional allocation prevents overflow, etc.
        c += 1
      }
      rep += 1
    }

    end = System.nanoTime()
    ( (end - start),  arrayD ) //The return of the variables make arrayI necessary to calculate
  }
  /**add + standard operations
    */
  def adderD(): (Long, Array[Double]) = {
    var arrayD = Array.tabulate(max)(  (i: Int) => randomDouble()   )
    var c = 0
    var rep = 0
    start = System.nanoTime()
    while (rep < reps) {
      while (c < max) {
        val curr = arrayD(c) + randomDouble() //Double random number excludes algebraic simplifications
        val next = randomDouble()
        arrayD(c) = if( next < curr ) next else curr //This conditional allocation prevents overflow, etc.
        c += 1
      }
      rep += 1
    }

    end = System.nanoTime()
    ( (end - start),  arrayD ) //The return of the variables make arrayI necessary to calculate
  }
  /**divide + standard operations
    */
  def dividerD(): (Long, Array[Double]) = {
    var arrayD = Array.tabulate(max)(  (i: Int) => randomDouble()   )
    var c = 0
    var rep = 0
    start = System.nanoTime()
    while (rep < reps) {
      while (c < max) {
        val curr = arrayD(c) / randomDouble() //Double random number excludes algebraic simplifications
        val next = randomDouble()
        arrayD(c) = if( next < curr ) next else curr //This conditional allocation prevents overflow, etc.
        c += 1
      }
      rep += 1
    }

    end = System.nanoTime()
    ( (end - start),  arrayD ) //The return of the variables make arrayI necessary to calculate
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" run functions ">


  def runloopsOverwriterI(): Array[(Long, Int)] = {
    (for(c <- 1 to 10) yield {
         val temp = overwriterI()
          (temp._1, temp._2.fold(0)( (a: Int, b: Int) => a + (if(r.nextInt > 0) 1 else -1 ) * b ) )
      }).toArray
  }

  def runloopsAdderI(): Array[(Long, Int)] = {
    (for(c <- 1 to 10) yield {
      val temp = adderI()
      (temp._1, temp._2.fold(0)( (a: Int, b: Int) => a + (if(r.nextInt > 0) 1 else -1 ) * b ) )
    }).toArray
  }

  def runloopsDividerI(): Array[(Long, Int)] = {
    (for(c <- 1 to 10) yield {
      val temp = dividerI()
      (temp._1, temp._2.fold(0)( (a: Int, b: Int) => a + (if(r.nextInt > 0) 1 else -1 ) * b ) )
    }).toArray
  }

  def runloopsOverwriterD(): Array[(Long, Double)] = {
    (for(c <- 1 to 10) yield {
      val temp = overwriterD()
      (temp._1, temp._2.fold(0d)( (a: Double, b: Double) => a + (if(r.nextInt > 0) 1d else -1d ) * b ) )
    }).toArray
  }

  def runloopsAdderD(): Array[(Long, Double)] = {
    (for(c <- 1 to 10) yield {
      val temp = adderD()
      (temp._1, temp._2.fold(0d)( (a: Double, b: Double) => a + (if(r.nextInt > 0) 1d else -1d ) * b ) )
    }).toArray
  }

  def runloopsDividerD(): Array[(Long, Double)] = {
    (for(c <- 1 to 10) yield {
      val temp = dividerD()
      (temp._1, temp._2.fold(0d)( (a: Double, b: Double) => a + (if(r.nextInt > 0) 1d else -1d ) * b ) )
    }).toArray
  }

  // </editor-fold>

}
