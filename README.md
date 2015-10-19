# hayabaya (Release Candidate)
Java based project for raw profiling of processing times of primitive Java data types and the Boxed counterparts.

## How to run Hayabaya
##From the commandline 
Using "$java -jar HayabayaV1RC.jar" you must supply the program with 3 arguments

1. **name** The name of your CPU
2. **sample size** What size of data you want to profile, choose among 3 predefined sets: **small, medium, large**
3. **Number of repetitions** How many times the profiling should be replicated, must be an integer [1-100]

### Examples of running from commandline
$java -jar hayabayaV1RC.jar AMDA4 small 3

$java -jar hayabayaV1RC.jar inteli7 medium 6

The first example will write the results into a subfolder of the current folder from which the jar file was executed. 
The subfolder will be named after the **name** argument given to the program and contains csv files for the profiling
results, and a **SystemPropertiesJVM.txt** file with information about the JVM properties, such and operating system
dependent path separator, line separator, newline character etc, all of which differ between *nix and Windows systems.
The **audits** folder contains a text file with logging information from the logging system.

## Running using the gradle build system
If you have gradle installed, from the command line (or in gradle pane in your IDE) you can build the project, assemble
a jar file and clean the project.

"$gradle build" will compile and assemble the project
"$gradle" will will a small sample of the profiling project
"$gradle fatJar" will assemble the project and all of its dependencies into a jar file located in the subfolder
./build/libs/