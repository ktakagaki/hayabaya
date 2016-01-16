# Java/Scala Microbenchmark - hayabaya

Hayabaya is a microbenchmark project written initially in Java while I was learning the Java programming language. The idea was to benchmark how long it takes for the JVM to perform arithmetic operations such as addition, multiplication, division etc. on arrays of integer, long, float and double for both the primitive and boxed versions available in Java.

Now as I am learning Scala as well, I have decided to start writing a new version of the project in Scala sourcecode to complement the code written in Java.



# **ToDo**


* Switch from Gradle build system to SBT build system
* Use the "gen idea" plugin for SBT to generate the .idea folder and make a clean build
* Re-design the Java domain model as a library provider for the Scala code being the "consumer"/"client" in the benchmark project.
* Write Scala code using Java code to perform the benchmark
* Implement code using the Scala version of the OpenJDK Java Microbenchmark Harness **(JMH)**

# Design of Hayabaya
## Use cases
## Domain model
## API
## How to use

# Benchmark results

# Conclusion

# References

# Contact

# Legal matters

# Referencing the project