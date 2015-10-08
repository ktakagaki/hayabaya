package org.hayabaya;

import ch.qos.logback.classic.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hayabaya.datarelated.Results;
import org.hayabaya.datarelated.ResultsCollection;
import org.hayabaya.datarelated.Tpe;
import org.hayabaya.loopers.LooperFactory;
import org.hayabaya.loopers.Loopers;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Profile how fast the JVM performs arithmetic operations. The program creates 8 arrays of length <i>n</i>
 * for each of the 4 basic data types <i>int, float, double</i> and <i>long</i> and their boxed counterparts. The
 * code then performs the 4 different operations (+,-,/,*) for <i>x</i> number of times on each element of the arrays
 * and measures the runtime. Results are saved to disk in csv files
 *
 * @author Ktakagaki
 * @author Slentzen Demian
 * @version 1.0
 */
public class MainClass {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(MainClass.class);


    /**
     * When run from the command line, Hayabaya takes 3 arguments.
     * <pre>
     *     $java -jar NameOfProcessor SizeOfTestData TotalRepetitions
     *     $java -jar AMDA4 small 3
     * </pre>
     * The arguments are as follows:
     *     <ul>
     *         <li><b>NameOfProcessor</b> To distinguish between results from different systems, the name of the CPU
     *         should be included
     *         <li><b>SizeOfTestData</b> There are 3 preset configurations for convenience, <b>small, medium</b> and
     *         <b>large</b>
     *         <li><b>TotalRepetitions</b> How many times to replicate the entire experiment
     *     </ul>
     *
     * Once Main has verified the values from the command line arguments given, it will pass them on to
     * the {@link RunSettings Runsettings} class which stores project settings.
     *
     * @throws IllegalArgumentException Throws error if 3 arguments are not given on the command line
     * @param args Arguments specifying the name of the CPU being tested, the size of the experiment to run and the
     */
    public static void main(String[] args) {
        logger.debug("Hayabaya main entered");

        RunSettings runSettingsInstance = RunSettings.getRunSettingsInstance();
        logger.debug("The values from args are: {}", Arrays.toString(args));


        /*
        This codeblock validates the input arguments given on the commandline
         */
        try {
            if (args.length != 3) {
                throw new IllegalArgumentException("You must supply 3 " +
                        "arguments to the program, 1st: name, 2nd: small/medium/large 3rd: " +
                        "repetitions [1-10] \n");
            }
            if (args.length == 3) {
                logger.debug("argument length == 3 and values: {}", Arrays.toString(args));

                String name = args[0];
                runSettingsInstance.setNameOfProcessor(name);
                String sampleSize = args[1];
                runSettingsInstance.setSampleSize(sampleSize);
                int reps = Integer.parseInt(args[2]);
                runSettingsInstance.setTotalExperimentRepetitions(reps);
            }
        } catch (IllegalArgumentException ie) {
            logger.error("args.length is not 3, it contains: {} ", Integer.toString(args.length));

        } catch (NumberFormatException fe) {
            logger.error("Argument \'" + args[2] + "\' must be a parsable integer.");

        }

        //<editor-fold desc="Data generation">
        //         else if (args.length == 3) {
//
//
//
//            try {
//
//
//            } catch (NumberFormatException e) {
//                System.err.println("Argument \'" + args[2] + "\' must be a parsable integer.");
//                System.exit(-1);
//            }
//        }


        /*
        Having validated the arguments it is now time to start the experiment. LooperFactory ensures that main does
        not have to concern itself with the specifics of each different datatype as generics are not allowed for the
        primitive data types in Java.
         */
        LooperFactory looperFactory = LooperFactory.getinstance();
        List<Loopers> arrayListOfLoopers = new ArrayList<>();

        for (Tpe datatype : Tpe.values()) { // Instantiate Looper instance for int, float...
            arrayListOfLoopers.add(looperFactory.createLooperInstance(datatype));
        }


        for (Loopers anInstance : arrayListOfLoopers) {
            anInstance.makeResults();
        }


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        builder.setPrettyPrinting().serializeNulls();

        ResultsCollection resultsCollection = ResultsCollection.getInstance();
        LinkedList<Results> allResults = resultsCollection.getResultsList();

//        Results oneResult = allResults.pop();
//        System.out.println(gson.toJson(oneResult));
//
        for (Results individualResults : allResults) {
            System.out.println(gson.toJson(individualResults));
        }

        logger.info("Writing the results to disk");

        logger.info("Exiting Hayabaya");
        System.exit(0);
        //</editor-fold>
    }

    private static void validateArgs(String[] args) throws RuntimeException {
        if (args.length != 3) throw new IllegalArgumentException("Give 3 arguments");
//
//                args[1] wrong option
//                args[2] not an integer
//                args[2] int < 0

    }

    private static void validateArgsValues(String[] args) throws IllegalArgumentException {
        String name = args[0];
        String sampleSize = args[1];
        String repetitions = args[2];

        Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) throw new IllegalArgumentException("Illegal characters in argument name");
        if (name.length() == 0 || name.length() > 20) throw new IllegalArgumentException("name must be 1-20 long");
        if (sampleSize.matches("small|medium|large"))


    }
}