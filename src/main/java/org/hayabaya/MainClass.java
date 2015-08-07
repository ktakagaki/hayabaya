/**
 * MainClass
 * <p/>
 * Measure the performance of arrays consisting of different primitive data types (int, float, double) and autoboxed
 * data types (Integer, Float).
 * <p/>
 * It is possible to performOperation the 4 fundamental operations (Addition, Subtraction, Multplication and Division) on an
 * array of arbitrary length, and with an arbitrary number of cycles repeated on each array.
 */
package org.hayabaya;

import ch.qos.logback.classic.Logger;
import org.hayabaya.datarelated.Tpe;
import org.hayabaya.loopers.LooperFactory;
import org.hayabaya.loopers.Loopers;
import org.slf4j.LoggerFactory;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Main class for testing java operations.
 * <p>
 * APPROACH TO GENERATE DATA FOR REPORT:
 * 1: create 2D arrays for each datatype and operation getType int:[add,sub,mult] + float:[add,sub,mult] etc.
 * ROWS:  Arraylength, start: 1.000 length, end: 100.length, stepsize: +1.000 length, TOTAL = 100
 * COLUMNS: Repetitions, start: 1.000 reps, end: 10.000 reps, stepsize: +1.000 reps, TOTAL = 10
 * 2: final is 100 rows X 10 colums (array length, repetitions)
 * 3: Write each datatype/operationtype to a csv file and process in R.</p>
 *
 * @author Ktakagaki
 * @author Slentzen Demian
 * @version 1.0
 */
public class MainClass {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(MainClass.class);
    private static String TAG;

    /**
     * Main takes 3 parameters, \" name sampleSize reps \". Name is the name
     * of the CPU/System being tested, sampleSize has to be either small,
     * medium or large, and reps are the total number of repetitions to
     * perform for the benchmarking.
     *
     * @param args name sampleSize repetitions
     */
    public static void main(String[] args) throws IllegalArgumentException,
            NumberFormatException, ClassNotFoundException {
        //<editor-fold desc="Logging">
        try {
            Class<?> cls = Class.forName("org.hayabaya.MainClass");
            TAG = cls.toString();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("The classname was not found and TAG not initialized", e);
        }


        Date date = new Date();
        logger.info(TAG + " the date is: " + date);
        //</editor-fold>


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