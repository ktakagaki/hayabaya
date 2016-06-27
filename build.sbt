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
    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0" withSources() withJavadoc()
    exclude("org.scala-lang", "modules.scala-xml") exclude("org.slf4j", "slf4j-api")
    exclude("org.scala-lang", "scala-reflect"),
    "ch.qos.logback" %  "logback-classic" % "1.1.7"
  )
}

mainClass in(Compile, packageBin) := Some("de.lin.hayabaya.Main")

initialCommands := """
                     |import de.lin.hayabaya._
                     |import de.lin.hayabaya.datarelated._
                     |import de.lin.hayabaya.util._
                     |import de.lin.hayabaya.playground._
                     |println(" \n\t=== creating val mainConf = new MainConfig() ===\n")
                     |val mainConf = new MainConfig()
                     |println("args: are = -sn, corei5, -min, 512, -max, 1024, --stepSize, 32) ===\n")
                     |val args = Array("-sn", "corei5", "-min", "512", "-max", "1024", "--stepSize", "32")
                   """.stripMargin('|')

// fork a new JVM for 'run' and 'test:run'
fork := true

