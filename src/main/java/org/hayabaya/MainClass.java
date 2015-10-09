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

    /**
     * Name, Description and flag for the -h (help) Command Line option
     */
    private static final String OPTION_H_NAME = "h";
    private static final String OPTION_H_DESCRIPTION = "Prints helpfull information on program usage and options";
    private static final boolean OPTION_H_HAS_ARGS = false;

    /**
     * Name, Description and flag for the -p (use preset values) Command Line option
     */
    private static final String OPTION_P_NAME = "h";
    private static final String OPTION_P_DESCRIPTION = "Use preset values for generating the data supplying only 3 " +
            "arguments, name of the cpu, sampleSize and number of total repetitions. Defaults to true. See -h for " +
            "further details on usage and other options";
    private static final boolean OPTION_P_HAS_ARGS = false;

    /**
     * Name, Description and flag for the -c (costum settings) Command Line option
     */
    private static final String OPTION_C_NAME = "c";
    private static final String OPTION_C_DESCRIPTION = "Specify costum values for the profiling project " +
            "(default=false) using the default -c=true option instead" +
            ". See -h for " +
            "details on usage";
    private static final boolean OPTION_C_HAS_ARGS = true;

    /**
     * Name, Description and flag for the -la (linear arrays) Command Line option
     */
    private static final String OPTION_LA_NAME = "la";
    private static final String OPTION_LA_DESCRIPTION = "Make the elements of the array of arraylengths to be tested" +
            " " +
            "be generated in a linear fashion";
    private static final boolean OPTION_H_HAS_ARGS = false;

    private static final String OPTION_H_NAME = "h";
    private static final String OPTION_H_DESCRIPTION = "Prints helpfull information on program usage and options";
    private static final boolean OPTION_H_HAS_ARGS = false;

    private static final String OPTION_H_NAME = "h";
    private static final String OPTION_H_DESCRIPTION = "Prints helpfull information on program usage and options";
    private static final boolean OPTION_H_HAS_ARGS = false;
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
            validateArgsValues(args);
            validateArgs(args);

            String name = args[0];
            runSettingsInstance.setNameOfProcessor(name);
            String sampleSize = args[1];
            runSettingsInstance.setSampleSize(sampleSize);
            int reps = Integer.parseInt(args[2]);
            runSettingsInstance.setTotalExperimentRepetitions(reps);

        } catch (IllegalArgumentException e) {
            logger.error("Illegal arguments for args: {} ", Integer.toString(args.length));

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

    public static void validateArgs(String[] args) throws IllegalArgumentException {
        if (args.length != 3) throw new IllegalArgumentException("Give 3 arguments");

    }

    public static void validateArgsValues(String[] args) throws IllegalArgumentException {
        String name = args[0];
        String sampleSize = args[1].toLowerCase();
        String repetitions = args[2];

        Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]");
        Matcher matcher = pattern.matcher(name);

        if (matcher.find() || name.length() <= 0 || name.length() > 20) throw new IllegalArgumentException("Bad name " +
                "arguments");

        if (!sampleSize.matches("small|medium|large")) throw new IllegalArgumentException("sample size must be either" +
                " \"small\" , \"medium\" or \"large\" ");

        if (!isInteger(repetitions)) throw new NumberFormatException("non integer rep argument");
        if (isInteger(repetitions)) {
            int reps = Integer.parseInt(args[2]);
            if (reps <= 0) throw new NumberFormatException("repetitions must be > 0");
            if (reps > 100) throw new NumberFormatException("too many repetitions");
        }


    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c <= '/' || c >= ':') {
                return false;
            }
        }
        return true;
    }
}