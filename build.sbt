name := "hayabaya"

version := "1.0"

organization := "de.lin"

scalaVersion := "2.11.8"

// To ignore all the java code
unmanagedSourceDirectories in Compile := (scalaSource in Compile).value :: Nil

scalacOptions ++= Seq("-unchecked", "-deprecation", "-Xcheckinit",
  "-encoding", "utf8", "-feature")

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

libraryDependencies ++= {
  Seq(
    "org.scalatest" %% "scalatest" % "2.2.6" % "test" withSources() withJavadoc(),
    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0" withSources() withJavadoc(),
    "ch.qos.logback" %  "logback-classic" % "1.1.7"
  )
}


// Define initialcommands in console
initialCommands in console := """
                                 import de.lin.hayabaya._
                                 println("hayabayaConsoleStarted")
     """

mainClass in Compile := Some("de.lin.hayabaya.Main")
//mainClass in(Compile, packageBin) := Some("de.lin.hayabaya.Main")


// fork a new JVM for 'run' and 'test:run'
fork := true

