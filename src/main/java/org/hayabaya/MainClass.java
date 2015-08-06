/**
 * MainClass
 * <p>
 * Measure the performance of arrays consisting of different primitive data types (int, float, double) and autoboxed
 * data types (Integer, Float).
 * <p>
 * It is possible to performOperation the 4 fundamental operations (Addition, Subtraction, Multplication and Division) on an
 * array of arbitrary length, and with an arbitrary number of cycles repeated on each array.
 */
package org.hayabaya;

import org.hayabaya.datarelated.Tpe;
import org.hayabaya.loopers.LooperFactory;
import org.hayabaya.loopers.Loopers;

import java.util.ArrayList;
import java.util.List;


/**
 * Main class for testing java operations.
 * <p>
 * APPROACH TO GENERATE DATA FOR REPORT:
 * 1: create 2D arrays for each datatype and operation getType int:[add,sub,mult] + float:[add,sub,mult] etc.
 * ROWS:  Arraylength, start: 1.000 length, end: 100.length, stepsize: +1.000 length, TOTAL = 100
 * COLUMNS: Repetitions, start: 1.000 reps, end: 10.000 reps, stepsize: +1.000 reps, TOTAL = 10
 * 2: final is 100 rows X 10 colums (array length, repetitions)
 * 3: Write each datatype/operationtype to a csv file and process in R.
 *
 * @author Ktakagaki
 * @author Slentzen Demian
 * @version 1.0
 */
public class MainClass {
    public static void main(String[] args) {
        RunSettings runSettings = RunSettings.getInstance();


        if (args.length < 3) {
            System.out.println("Please supply a \"name\" param, a \"size\" param (small, medium, large) and number of repetitions");
            System.exit(1);
        } else if (args.length == 3) {
            String name= args[0];
            runSettings.setName(name);
            String sampleSize = args[1];
            runSettings.setSampleSize(sampleSize);

            try {
                int reps = Integer.parseInt(args[2]);
                runSettings.setTotalExperimentRepetitions(reps);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[2] + "must be an integer > 0");
                System.exit(1);
            }
        }


        LooperFactory looperFactory = LooperFactory.getinstance();

        /* place Loopers into a list and iterate over the list performing operations, parsing types to methods */
        List<Loopers> aListOfLoopers = new ArrayList<>();

        for (Tpe datatype: Tpe.values()){
            aListOfLoopers.add(looperFactory.createLooperInstance(datatype));
        }

        /* No need for doing casting as makeResults should only use non-instance specific actions */
        for (Loopers anInstance : aListOfLoopers){
            anInstance.makeResults();
        }

        //Write the runsettings used to disk so it can be recalled later on during analysis
        runSettings.writeRunSettingsToDisk();
    }
}