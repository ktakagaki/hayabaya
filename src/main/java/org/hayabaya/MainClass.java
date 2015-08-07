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

import org.hayabaya.datarelated.Tpe;
import org.hayabaya.loopers.LooperFactory;
import org.hayabaya.loopers.Loopers;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    // Define a static logger variable so that it references the
    // Logger instance named "MyApp".
    private static final Logger logger = LogManager.getLogger(MainClass.class);

    /**
     * Main takes 3 parameters, \" name sampleSize reps \". Name is the name
     * of the CPU/System being tested, sampleSize has to be either small,
     * medium or large, and reps are the total number of repetitions to
     * perform for the benchmarking.
     *
     * @param args name sampleSize repetitions
     */
    public static void main(String[] args) {
        logger.info("Hello, World!");
        RunSettings runSettings = RunSettings.getInstance();


        if (args.length < 3) {
            System.err.println("Please supply a \"name\" param, a \"size\" param (small, medium, large) and number of repetitions");
            System.exit(-1);
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
                logger.error("Illegal usage of repetitions arguments", e);
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