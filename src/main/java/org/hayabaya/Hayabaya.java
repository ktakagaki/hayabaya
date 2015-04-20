package org.hayabaya;

import org.hayabaya.loopers.*;

/**Main class for testing java operations.
 * Created by ktakagaki on 15/03/16.
 */


public class Hayabaya {

    public static void main(String[] args){
        /**
         *  APPROACH TO GENERATE DATA FOR REPORT:
         *  1: create 2D arrays for each datatype and operation type int:[add,sub,mult] + float:[add,sub,mult] etc.
         *      ROWS:  Arraylength, start: 1.000 length, end: 100.length, stepsize: +1.000 length, TOTAL = 100
         *      COLUMNS: Repetitions, start: 1.000 reps, end: 10.000 reps, stepsize: +1.000 reps, TOTAL = 10
         *  2: final is 100 rows X 10 colums (array length, repetitions)
         *  3: Write each datatype/operationtype to a csv file and process in R.
         */
        long startTime = System.currentTimeMillis();
        Results result = null;
        Loopers looper = null;

        System.out.println("Hello to the Hayabaya project \n");

        //ToDo 1: Basically, you need to repeat this maybe 10 times to get a mean/SD
        //ToDo 1: and then, do this for each type
        looper = new LoopersInt(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN);
        result = looper.makeResults(Operation.ADD);
        Utility.writeResultsToCsv(result);


//        System.out.println("Addition");
//        printMatrix(resultIntegerADD);

        System.out.println("numberofRows: " + RunSettings.numberOfRowsSize + " numberofColumns: " + RunSettings.numberOfColumnsCycle);

        long endTime = System.currentTimeMillis();
        long totalRunTime =  endTime-startTime;
        System.out.println("The total runtime was: " + totalRunTime);

    }


    static void printMatrix(long[][] grid) {
        for(int r=0; r<grid.length; r++) {
            for(int c=0; c<grid[r].length; c++)
                System.out.print(grid[r][c] + " ");
            System.out.println();
        }
    }

    public static long[][] showValues(Loopers looperObject, Operation operation) {
        /**
         * A quick and dirty function used to check that the MakeIntResults function works correctly
         * To check that arraylength and repetition values are correct, just switch rowCount/columnCount in last line
         * of the inner loop.
         * //ToDo: Make method able to produce complete results passing 2D values
         */
        long results[][] = new long[RunSettings.numberOfRowsSize][RunSettings.numberOfColumnsCycle];

        // row loop
        for (int rowCount = RunSettings.ARRAY_SIZE_MIN, rowIndex = 0; rowCount <= RunSettings.ARRAY_SIZE_MAX; rowCount += RunSettings.ARRAY_SIZE_STEPS,
                rowIndex++) {
            // column loop
            for (int columnCount = RunSettings.CYCLES_MIN, columnIndex = 0; columnCount <= RunSettings.CYCLES_MAX; columnCount +=
                    RunSettings.CYCLES_STEPS, columnIndex++) {
                results[rowIndex][columnIndex] = (long) rowCount;
            }
        }
        return results;
    }


}