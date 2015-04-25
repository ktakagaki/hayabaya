/**
 * MainClass
 *
 * Measure the performance of arrays consisting of different primitive data types (int, float, double) and autoboxed
 * data types (Integer, Float).
 *
 * It is possible to test the 4 fundamental operations (Addition, Subtraction, Multplication and Division) on an
 * array of arbitrary length, and with an arbitrary number of cycles repeated on each array.
 */
package org.hayabaya;

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Results;
import org.hayabaya.datarelated.Utility;
import org.hayabaya.loopers.*;
import org.hayabaya.datarelated.*;

import java.util.ArrayList;
import java.util.List;


/**Main class for testing java operations.
 *
 *           APPROACH TO GENERATE DATA FOR REPORT:
 * 1: create 2D arrays for each datatype and operation type int:[add,sub,mult] + float:[add,sub,mult] etc.
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

    public static void main(String[] args){

        System.out.println("Hello to the MainClass project \n");
        long startTime = System.currentTimeMillis();

        Results result = null;
        List<Loopers> looperList = new ArrayList<>();

        int asize = RunSettings.ARRAY_SIZE_MIN;
        int acycle = RunSettings.CYCLES_MIN;


        Loopers loopersInt = new LoopersInt(asize, acycle);
        Loopers loopersLong = new LoopersLong(asize, acycle);
        Loopers loopersFloat = new LoopersFloat(asize, acycle);
        Loopers loopersDouble = new LoopersDouble(asize, acycle);
        Loopers loopersIntegerBoxed = new LoopersIntegerBoxed(asize, acycle);
        Loopers loopersLongBoxed = new LoopersLongBoxed(asize, acycle);
        Loopers loopersFloatBoxed = new LoopersFloatBoxed(asize, acycle);
        Loopers loopersDoubleBoxed = new LoopersDoubleBoxed(asize, acycle);


        looperList.add(loopersInt);
        looperList.add(loopersLong);
        looperList.add(loopersFloat);
        looperList.add(loopersDouble);
        looperList.add(loopersIntegerBoxed);
        looperList.add(loopersLongBoxed);
        looperList.add(loopersFloatBoxed);
        looperList.add(loopersDoubleBoxed);


        for (int repetitions = 0; repetitions < 5; repetitions++ ){
            for (Loopers lop : looperList) {
                for (Operation op : Operation.values()) {
                    result = lop.makeResults(op);
                    Utility.writeResultsToCsv(result);
                }
            }
            Utility.setResultCounter(repetitions);
        }


        long endTime = System.currentTimeMillis();
        long totalRunTime =  endTime-startTime;

        System.out.println("numberofRows: " + RunSettings.numberOfRowsSize + " numberofColumns: " + RunSettings.numberOfColumnsCycle);
        System.out.println("The total runtime was: " + totalRunTime);
        Loopers forPrint = looperList.get(0);
        Results forPrintResults = forPrint.makeResults(Operation.ADD);
        System.out.println("Example of result for addition: \t \n");
        Utility.printMatrix(forPrintResults.data);

    }


}