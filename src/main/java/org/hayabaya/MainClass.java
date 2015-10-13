package org.hayabaya;

import ch.qos.logback.classic.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.cli.Options;
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
        RunSettings runSettingsInstance = RunSettings.getRunSettingsInstance();
        logger.debug("Args are: {}", Arrays.toString(args));


        /*
        Validate arguments and then parse them to RunSettings so system is ready to go
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
            logger.error("Illegal arguments for args: {} \n resulting in {}",
                    Integer.toString(args.length), e.getStackTrace());
            e.printStackTrace();
        }



        List<Loopers> arrayListOfLoopers = new ArrayList<>();
        // Add a Loopers instance to the arrayList for each value in Tpe
        for (Tpe datatype : Tpe.values()) {
            try {
                arrayListOfLoopers.add(LooperFactory.getLooper(datatype));
            } catch (IllegalArgumentException e) {
             logger.error("LooperFactory.getlooper got an illegal argument: {}", e);
            }
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

//    /** Prints the usage information. **/
//    private static void printUsage() {
//        final HelpFormatter formatter = new HelpFormatter();
//        formatter.printHelp(String.format("java %s [options] -c <config.xml> file...",
//                Main.class.getName()), buildOptions());
//    }

    /**
     * Builds the options objects outlined in the CLIOptions class
     * @return available options
     */
    private static Options buildOptions() {
        final Options options = new Options();
        options.addOption(CLIOptions.OPTION_H_NAME, CLIOptions.OPTION_H_HAS_ARGS, CLIOptions.OPTION_H_DESCRIPTION);
        options.addOption(CLIOptions.OPTION_P_NAME, CLIOptions.OPTION_P_HAS_ARGS, CLIOptions.OPTION_P_DESCRIPTION);
        options.addOption(CLIOptions.OPTION_C_NAME, CLIOptions.OPTION_C_HAS_ARGS, CLIOptions.OPTION_C_DESCRIPTION);
        options.addOption(CLIOptions.OPTION_LA_NAME, CLIOptions.OPTION_LA_HAS_ARGS, CLIOptions.OPTION_LA_DESCRIPTION);
        options.addOption(CLIOptions.OPTION_LC_NAME, CLIOptions.OPTION_LC_HAS_ARGS, CLIOptions.OPTION_LC_DESCRIPTION);
        options.addOption(CLIOptions.OPTION_EA_NAME, CLIOptions.OPTION_EA_HAS_ARGS, CLIOptions.OPTION_EA_DESCRIPTION);
        options.addOption(CLIOptions.OPTION_EC_NAME, CLIOptions.OPTION_EC_HAS_ARGS, CLIOptions.OPTION_EC_DESCRIPTION);
        options.addOption(CLIOptions.OPTION_JSON_NAME, CLIOptions.OPTION_JSON_HAS_ARGS, CLIOptions.OPTION_JSON_DESCRIPTION);
        options.addOption(CLIOptions.OPTION_CSV_NAME, CLIOptions.OPTION_CSV_HAS_ARGS, CLIOptions.OPTION_CSV_DESCRIPTION);
        options.addOption(CLIOptions.OPTION_V_NAME, CLIOptions.OPTION_V_HAS_ARGS, CLIOptions.OPTION_V_DESCRIPTION);
        options.addOption(CLIOptions.OPTION_D_NAME, CLIOptions.OPTION_D_HAS_ARGS, CLIOptions.OPTION_D_DESCRIPTION);
//        options.addOption(OPTION_F_NAME, true, String.format(
//                "Sets the output format. (%s|%s). Defaults to %s",
//                PLAIN_FORMAT_NAME, XML_FORMAT_NAME, PLAIN_FORMAT_NAME));
//        options.addOption(OPTION_V_NAME, false, "Print product version and exit");
        return options;
    }

    /**
     * Definition of all of the available command line options in Hayabaya
     */
    private static class CLIOptions {
//        /**
//         * Name, Description and flag for the -participate (help) Command Line option
//         */
//        private static final String OPTION_PARTICIPATE_NAME = "participate";
//        private static final String OPTION_PARTICIPATE_DESCRIPTION = "Help the hayabaya project out in our attempt to" +
//                " create a website with a large database benchmarking many different devices in a uniform fashion. " +
//                "This assist our users in choosing the right device for specific applications of high performance " +
//                "computing at research institutions around the world. The option will run a series of benchmarks and " +
//                "then it will upload the results to our server. All data will be anonymous. default=false";
//        private static final boolean OPTION_PARTICIPATE_HAS_ARGS = false;

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
         * Name, Description and flag for the -c (custom settings) Command Line option
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
                "be generated in a linear fashion. Default=true";
        private static final boolean OPTION_LA_HAS_ARGS = false;

        /**
         * Name, Description and flag for the -ea (exponential arrays) Command Line option
         */
        private static final String OPTION_EA_NAME = "ea";
        private static final String OPTION_EA_DESCRIPTION = "Make the elements of the array of arraylengths to be tested" +
                " " +
                "be generated in an exponential fashion. Default=false";
        private static final boolean OPTION_EA_HAS_ARGS = false;


        /**
         * Name, Description and flag for the -lc (linear cycle numbers) Command Line option
         */
        private static final String OPTION_LC_NAME = "lc";
        private static final String OPTION_LC_DESCRIPTION = "Make the elements of the array of cycle numbers to " +
                "be tested be generated in a linear fashion. Default=true";
        private static final boolean OPTION_LC_HAS_ARGS = false;

        /**
         * Name, Description and flag for the -ec (exponential cycle numbers) Command Line option
         */
        private static final String OPTION_EC_NAME = "ec";
        private static final String OPTION_EC_DESCRIPTION = "Make the elements of the array of cycle numbers to be tested" +
                " be generated in an exponential fashion. Default=false";
        private static final boolean OPTION_EC_HAS_ARGS = false;

        /**
         * Name, Description and flag for the -json (use json format for results) Command Line option
         */
        private static final String OPTION_JSON_NAME = "json";
        private static final String OPTION_JSON_DESCRIPTION = "Write results in json format instead of csv format. " +
                "default=false";
        private static final boolean OPTION_JSON_HAS_ARGS = false;

        /**
         * Name, Description and flag for the -csv (use csv format for results) Command Line option
         */
        private static final String OPTION_CSV_NAME = "csv";
        private static final String OPTION_CSV_DESCRIPTION = "Write results in csv format. default=true";
        private static final boolean OPTION_CSV_HAS_ARGS = false;

        /**
         * Name, Description and flag for the -v (verbose) Command Line option
         */
        private static final String OPTION_V_NAME = "v";
        private static final String OPTION_V_DESCRIPTION = "Verbose mode, prints the results to stdout while running " +
                "default=false";
        private static final boolean OPTION_V_HAS_ARGS = false;

        /**
         * Name, Description and flag for the -d (debug mode) Command Line option
         */
        private static final String OPTION_D_NAME = "d";
        private static final String OPTION_D_DESCRIPTION = "Debug mode. default=false";
        private static final boolean OPTION_D_HAS_ARGS = false;
    }
}