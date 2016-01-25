# Java/Scala Microbenchmark - hayabaya

For the purpose of learning Java as a beginner, Hayabaya was developed as a rudimentary Microbenchmark project.
The code performs arithmetic operations (+,-,/,*) on arrays of type:

```java
Integer ints[]
Float floats[]
Long longs[]
Float floats[]
``` 
And their primitive counterparts **int[], long[], double[], float[]**.
The runtime for performing the arithmetic operations on the different types of arrays are measured.

Each of the operations are tested on all the datatypes for arrays of varying lenths as specified by a from, to, by expression, e.g, arrays of element lengths . 1,000 to 10,000 in steps of 1,000.
In addition to the lenths, each element of the array is operated on a number of times called the **CycleNumber** which is defined just like the array length (from, to, by).



Hayabaya saves the results of the microbenchmark in CSV (Comma Separated Value) files with the following columns:

<H2 align="center">  Example Hayabaya result output </H2>

| Column name | Name | Datatype | IsBoxed | Operation | ReplicationNumber | ArrayLength | CycleNumber | Runtime |
|---------------|------------------------|---------------------|------------------------|-----------|-------------------------|----------------|---------------------------------------|---------------|
| Description | Name of the system/CPU | Type of data tested | logical, Integer= True | (+,-,/,*) | What replication is run | Lenth of array | Times operation is done on an element | Runtime in ms |
| Example value | Inteli7 | Integer | True | ADD | 4 | 100,000 | 10,000 | 230 (ms) |
| Example value | AMDA4 | int | FALSE | DIVIDE | 5 | 100,000 | 20,000 | 175 (ms) |


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

```bash
$ git clone git@github.com:slentzen/hayabaya.git
$ cd hayabaya
$ sbt gen-idea
$ sbt reload update compile
$ sbt run myTestRun small 4 # Run a predefined set named "small" with name "myTestRun" and 4 replicates
```

Clone or fork the repository. Then issue the "**$sbt gen-idea**" command to intialize an IntelliJ IDEA project definition.

### Generate idea proj
# Benchmark results

# Conclusion

# References

# Contact

# Legal matters

# Referencing the project