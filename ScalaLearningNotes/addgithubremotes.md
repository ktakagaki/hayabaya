# How to add a remote repository on GitHub as a managed dependency in SBT

## Guide from Scala Cookbook / Alvin Alexander
The following steps are from the guide on how to add a GitHub repository as a managed dependency to your project in SBT. The guide is a bit outdated and old, but the steps should largely have remained unchanged and still be the same.

The link to the website and the guide is [here](http://alvinalexander.com/scala/using-github-projects-scala-library-dependencies-sbt-sbteclipse)


### Step 1-3 Install SBT and create your project
These steps are just the standard installation of SBT, creating a new SBT based project and defining the build.sbt file etc.


### Step 2
Once the project has been created. Try creating a /src/main/scala/org/foobar/Test.scala file/class
The Class contains the following code

```{scala}
package foobar

import com.alvinalexander.sound._

object TestSbtGithub extends App {

  // you'll need your own short sound clip
  val testClip = "/Users/al/Sarah/plugins/DDRandomNoise/HAL9000/this-mission-too-important.wav"
  val player = SoundFilePlayer.getSoundFilePlayer(testClip)

  try {
    player.play
    // necessary b/c play() doesn't block, and i didn't want to add a
    // listener to this short test
    Thread.sleep(5000)
  } catch {
    case e:Exception => println(e.getMessage)
  }

}
```

### Step 3 Create the prject folder in TLD
Create the project/ folder in the top of the project folder if it doesn't exist yet. Add the **Build.scala** Build file to the project/Build.scala

```{scala}
import sbt._

object MyBuild extends Build {

  lazy val root = Project("root", file(".")) dependsOn(soundPlayerProject)
  lazy val soundPlayerProject = RootProject(uri("git://github.com/alvinj/SoundFilePlayer.git"))

}
```

### Step 4 Compile and run
If everything works as intended, it should now be possible to issue the SBT commands for compiling and running the project, and it'll make use of the code available from the GitHub repository added as a managed dependency in the project/Build.scala file

```{bash}
$sbt reload update compile
$sbt run
```

### Step 5

### Step 6

