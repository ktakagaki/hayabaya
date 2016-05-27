import sys.process._
import scala.language.postfixOps

object SystemCall extends App {

  val results = "ls -al" !!

  println("The results from calling ls -al !!: \n \n ")
  println(results.toString)

  val revparse = "git rev-parse HEAD" !!

  println("\t\t\t ========== Printing the git commit SHA1 hash from HEAD ========== \n")
  println(revparse.toString)

}



