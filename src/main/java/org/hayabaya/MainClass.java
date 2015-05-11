/**
 * MainClass
 * <p>
 * Measure the performance of arrays consisting of different primitive data types (int, float, double) and autoboxed
 * data types (Integer, Float).
 * <p>
 * It is possible to test the 4 fundamental operations (Addition, Subtraction, Multplication and Division) on an
 * array of arbitrary length, and with an arbitrary number of cycles repeated on each array.
 */
package org.hayabaya;

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Results;
import org.hayabaya.datarelated.Utility;
import org.hayabaya.loopers.*;

import java.util.ArrayList;
import java.util.List;


/**Main class for testing java operations.
 *
 *           APPROACH TO GENERATE DATA FOR REPORT:
 * 1: create 2D arrays for each datatype and operation getType int:[add,sub,mult] + float:[add,sub,mult] etc.
 * ROWS:  Arraylength, start: 1.000 length, end: 100.length, stepsize: +1.000 length, TOTAL = 100
 * COLUMNS: Repetitions, start: 1.000 reps, end: 10.000 reps, stepsize: +1.000 reps, TOTAL = 10
 * 2: final is 100 rows X 10 colums (array length, repetitions)
 * 3: Write each datatype/operationtype to a csv file and process in R.
 *
 * Created by ktakagaki on 15/03/16.
 *
 * @author Ktakagaki
 * @author Slentzen Demian
 */
public class MainClass {
    public static void main(String[] args) {

        System.out.println("Hello to the HayaBaya project \n");

        Results result = null;
        List<Loopers> looperList = new ArrayList<>(); // place Loopers into a list and iterate over the list
        // performing operations, parsing types to methods


        looperList.add(new LoopersInt(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));
        looperList.add(new LoopersLong(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));
        looperList.add(new LoopersFloat(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));
        looperList.add(new LoopersDouble(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));

        looperList.add(new LoopersIntegerBoxed(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));
        looperList.add(new LoopersLongBoxed(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));
        looperList.add(new LoopersFloatBoxed(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));
        looperList.add(new LoopersDoubleBoxed(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));

        //<editor-fold desc="Temporarely not used for debug purposes">
/*        boolean debugPrintStuff = true;
        *//** The first and outermost loop only loops over the repetitions of the entire experiment *//*
        for ( int repetitions = 0; repetitions < RunSettings.TOTAL_EXP_REPS; repetitions++ ){

            *//** Loop over each getType of Looper object in the LooperList *//*
            for (Loopers thisLooper : looperList) {

                *//** Loop over the types of operations ADD, SUBTRACT etc. *//*
                for (Operation operation : Operation.values()) {

                    result = thisLooper.makeResults(operation);
                    Utility.writeResultsToCsv(result);

                    if (debugPrintStuff){
                        *//** DEBUG INSERTS START *//*
                        String loopString = thisLooper.toString();
                        System.out.println(loopString);

                        String resultString = result.toString();
                        System.out.println(resultString);

                        *//** DEBUG INSERTS START *//*
                    }


                }
            }
            Utility.setResultCounter(repetitions);
        }*/
        //</editor-fold>


//        /** Loops over the repetitions of the entire experiment */
//        for (int repetitions = 0; repetitions < RunSettings.TOTAL_EXP_REPS; repetitions++) {
//
//            /** Loops over objects (Loopers) in ArrayList */
//            for (Loopers thisLooper : looperList) {
//
//                /** Loops over operations ADD, SUBTRACT etc. */
//                for (Operation thisOperation : Operation.values()) {
//
//                    result = thisLooper.makeResults(thisOperation);
//                    Utility.writeResultsToCsv(result);
//                    //ToDo Rewrite:
//                    //  Utility.writeResultsToCsv(result, repetitions);
//                    //delete
//                    //  Utility.setResultCounter()
//
//                    if (RunSettings.debug) {
//                        /** DEBUG INSERTS START */
//                        System.out.println(thisLooper.toString());
//                        System.out.println(result.toString());
//                        /** DEBUG INSERTS START */
//                    }
//
//
//                }
//            }
//            Utility.setResultCounter(repetitions);
//        }
    }
}