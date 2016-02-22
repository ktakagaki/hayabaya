name := "hayabaya"

version := "1.0"

organization := "de.lin-magdeburg"

scalaVersion := "2.11.7"

//ToDo: Add https://github.com/tkawachi/sbt-doctest
//ToDo: Use SBT-DocTest and ScalaTest to do in-line testing

// add a test dependency on ScalaCheck
libraryDependencies ++= {
  Seq(
    "org.scalatest" % "scalatest_2.10" % "2.0" % "test" withSources() withJavadoc(),
    "ch.qos.logback" % "logback-core" % "1.1.3" withSources() withJavadoc(),
    "ch.qos.logback" % "logback-classic" % "1.1.3" withSources() withJavadoc(),
    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0" withSources() withJavadoc(),
    "com.typesafe" % "config" % "1.3.0" withSources() withJavadoc()
  )
}

// reduce the maximum number of errors shown by the Scala compiler
maxErrors := 20

// increase the time between polling for file changes when using continuous execution
pollInterval := 1000

// append several options to the list of options passed to the Java compiler
javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

// append -deprecation to the options passed to the Scala compiler
scalacOptions         := Seq(
  "-encoding", "utf8",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-target:jvm-1.8",
  "-language:_",
  "-Xlog-reflective-calls"
)

// define the statements initially evaluated when entering 'console', 'console-quick', or 'console-project'
initialCommands := """
  import System.{currentTimeMillis => now}
  def time[T](f: => T): T = {
    val start = now
    try { f } finally { println("Elapsed: " + (now - start)/1000.0 + " s") }
  }
                   """

// set the initial commands when entering 'console' or 'console-quick', but not 'console-project'
initialCommands in console := "import de.lin_magdeburg.hayabaya._; \n" +
  "import de.lin_magdeburg.hayabaya.datarelated._;" +
  "import de.lin_magdeburg.hayabaya.benchmarking;"

// set the main class for packaging the main jar
// 'run' will still auto-detect and prompt
// change Compile to Test to set it for the test jar
mainClass in (Compile, packageBin) := Some("de.lin_magdeburg.hayabaya.HayabayaMain")

// set the main class for the main 'run' task
// change Compile to Test to set it for 'test:run'
mainClass in (Compile, run) := Some("de.lin_magdeburg.hayabaya.HayabayaMain")

// fork a new JVM for 'run' and 'test:run'
fork := true

