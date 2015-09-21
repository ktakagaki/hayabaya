package org.hayabaya;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import org.hayabaya.datarelated.Tpe;
import org.hayabaya.loopers.LooperFactory;
import org.hayabaya.loopers.Loopers;


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
    private static String TAG;


    /**
     * When run from the command line, Hayabaya takes 3 arguments.
     * <code><pre>
     *     $java -jar NameOfProcessor SizeOfTestData TotalRepetitions
     *     $java -jar AMDA4 small 3
     * </pre></code>
     * The arguments are as follows:
     * <p>
     *     <ul>
     *         <li><b>NameOfProcessor</b> To distinguish between results from different systems, the name of the CPU
     *         should be included
     *         <li><b>SizeOfTestData</b> There are 3 preset configurations for convenience, <b>small, medium</b> and
     *         <b>large</b>
     *         <li><b>TotalRepetitions</b> How many times to replicate the entire experiment
     *     </ul>
     * </p>
     *
     * Once Main has verified the values from the command line arguments given, it will pass them on to
     * the {@link RunSettings Runsettings} class which stores project settings.
     *
     * @throws IllegalArgumentException Throws error if 3 arguments are not given on the command line
     * @throws java.lang.ClassNotFoundException Throws error if class is not found
     * @param args Arguments specifying the name of the CPU being tested, the size of the experiment to run and the
     *             total number of times to repeat the experiment
     */
    public static void main(String[] args) throws IllegalArgumentException, ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("org.hayabaya.MainClass");
            TAG = cls.toString();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("The classname was not found and TAG not initialized", e);
        }


        Date date = new Date();
        logger.info(TAG + " the date is: " + date);


        RunSettings runSettings = RunSettings.getInstance();


        if (args.length < 3) {
            throw new IllegalArgumentException("You must supply 3 " +
                    "arguments to the program, 1st: name, 2nd: small/medium/large 3rd: " +
                    "repetitions [1-10] \n");
        } else if (args.length == 3) {
            String name = args[0];
            runSettings.setName(name);
            String sampleSize = args[1];
            runSettings.setSampleSize(sampleSize);

            try {
                int reps = Integer.parseInt(args[2]);
                //The following will also check if the parse ended up with a valid int parameter
                runSettings.setTotalExperimentRepetitions(reps);
            } catch (NumberFormatException e) {
                System.err.println("Argument \'" + args[2] + "\' must be a parsable integer.");
                System.exit(-1);
            }
        }


        LooperFactory looperFactory = LooperFactory.getinstance();

        /* place Loopers into a list and iterate over the list performing operations, parsing types to methods */
        List<Loopers> aListOfLoopers = new ArrayList<>();

        for (Tpe datatype : Tpe.values()) {
            aListOfLoopers.add(looperFactory.createLooperInstance(datatype));
        }

        /* No need for doing casting as makeResults should only use non-instance specific actions */
        for (Loopers anInstance : aListOfLoopers) {
            anInstance.makeResults();
        }

        //Write the runsettings used to disk so it can be recalled later on during analysis
        runSettings.writeRunSettingsToDisk();
        System.exit(0);
    }
}