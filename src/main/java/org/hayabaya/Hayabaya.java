/**
 * Hayabaya
 *
 * Measure the performance of arrays consisting of different primitive data types (int, float, double) and autoboxed
 * data types (Integer, Float).
 *
 * It is possible to test the 4 fundamental operations (Addition, Subtraction, Multplication and Division) on an
 * array of arbitrary length, and with an arbitrary number of cycles repeated on each array.
 */
package org.hayabaya;

import org.hayabaya.loopers.*;

import java.io.FileNotFoundException;

import static org.hayabaya.Utility.*;
import static org.hayabaya.RunSettings.*;


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
public class Hayabaya {

    public static void main(String[] args){


        long startTime = System.currentTimeMillis();
        Results result = null;
        Loopers looper = null;

        System.out.println("Hello to the Hayabaya project \n");

        //ToDo 1: Basically, you need to repeat this maybe 10 times to get a mean/SD
        //ToDo 1: and then, do this for each type
        looper = new LoopersInt(ARRAY_SIZE_MIN, CYCLES_MIN);
        result = looper.makeResults(Operation.ADD);
        writeResultsToCsv(result);



        long endTime = System.currentTimeMillis();
        long totalRunTime =  endTime-startTime;


        System.out.println("numberofRows: " + RunSettings.numberOfRowsSize + " numberofColumns: " + RunSettings.numberOfColumnsCycle);
        System.out.println("The total runtime was: " + totalRunTime);

    }


}