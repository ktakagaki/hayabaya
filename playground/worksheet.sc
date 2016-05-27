case class Employee(name: String, office: String, role: String)

val fred = Employee("Fred", "Anchorage", "Salesman")

val joe = fred.copy(name="Joe")

println("fred is :" + fred + "\n and joe: " +joe)


import sys.process._
import scala.language.postfixOps

val shahash = "git rev-parse HEAD" !!


