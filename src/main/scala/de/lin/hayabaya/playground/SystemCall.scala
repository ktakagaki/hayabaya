package de.lin.hayabaya.playground

import sys.process._
import scala.language.postfixOps

object SystemCall {

  def printLines(): String = ("ls -al" !!).toString

  def printHash(): String = {
    val res = ("git rev-parse HEAD" !!).toString
    res
  }


  def main(args: Array[String]): Unit = {

    println("Hello")

    val hash = SystemCall.printHash()

    println("The git hash is: " + hash)
  }

}

