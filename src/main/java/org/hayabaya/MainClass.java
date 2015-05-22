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

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Results;
import org.hayabaya.datarelated.Utility;
import org.hayabaya.loopers.*;

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

        System.out.println("Hello to the HayaBaya project \n");

        Results result = null;

        /* place Loopers into a list and iterate over the list performing operations, parsing types to methods */
        List<Loopers> aListOfLoopers = new ArrayList<>();


        //ToDo150522: try to change the following to use for comprehension with RunSettings.cycleNumbers and arrayLengths

        //<editor-fold desc="Initialize all Looper instances with Array_Size_Min and Cycles_Min from Runsettings">
        aListOfLoopers.add(new LoopersInt(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));
        aListOfLoopers.add(new LoopersLong(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));
        aListOfLoopers.add(new LoopersFloat(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));
        aListOfLoopers.add(new LoopersDouble(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));

        aListOfLoopers.add(new LoopersIntegerBoxed(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));
        aListOfLoopers.add(new LoopersLongBoxed(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));
        aListOfLoopers.add(new LoopersFloatBoxed(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));
        aListOfLoopers.add(new LoopersDoubleBoxed(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));
        //</editor-fold>


        /* The first and outermost loop only loops over the repetitions of the entire experiment */
        for ( int repetitions = 0; repetitions <= RunSettings.TOTAL_EXP_REPS; repetitions++ ){

            /* Loops over each getType of Looper object in the LooperList */
            for (Loopers aLooperInstance : aListOfLoopers) {

                /* Loop over the types of operations ADD, SUBTRACT etc. */
                for (Operation anOperationToUse : Operation.values()) {

                    result = aLooperInstance.makeResults(anOperationToUse);
                    Utility.writeResultsToCsv(result);


                    //<editor-fold desc="Print debug information when running Hayabaya">
                    if (RunSettings.debug){
                        String loopString = aLooperInstance.toString();
                        System.out.println(loopString);
                        String resultString = result.toString();
                        System.out.println(resultString);
                    }
                    //</editor-fold>
                }
            }
            Utility.setResultCounter(repetitions);
        }
    }
}