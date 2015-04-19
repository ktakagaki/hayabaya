package org.hayabaya;

import org.hayabaya.loopers.*;

/**Main class for testing java operations.
 * Created by ktakagaki on 15/03/16.
 */


public class Hayabaya {
    // NB: Note the hardcoded "1000" integer used for numberOfRowsSize and numberOfColumnsCycle, set the other variables only in
    // steps thousands
    //ToDo: Implement scaling factors so any stepsize can be used and calculation of numberOfX scales accordingly
    //ToDo 2: you might want to encapsulate all of the following in a class or a static thing
    static final public int ARRAY_SIZE_MIN =    1_000; // thousand
    static final public int ARRAY_SIZE_MAX =  100_000; // hundred thousand
    static final public int ARRAY_SIZE_STEPS =  1_000;

    static final public int CYCLES_MIN   = 1_000; // thousand
    static final public int CYCLES_MAX   = 5_000; // ten thousand
    static final public int CYCLES_STEPS = 1_000;

    static final public int numberOfRowsSize = (int) Math.ceil( ((ARRAY_SIZE_MAX - ARRAY_SIZE_MIN) ) / ARRAY_SIZE_STEPS) + 1;
    static final public int numberOfColumnsCycle = (int) Math.ceil( ((CYCLES_MAX - CYCLES_MIN) ) / CYCLES_STEPS) + 1;

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
        looper = new LoopersInt(ARRAY_SIZE_MIN, CYCLES_MIN);
        result = looper.makeResults(Operation.ADD);
        Utility.writeResultsToCsv(result);


//        System.out.println("Addition");
//        printMatrix(resultIntegerADD);

        System.out.println("numberofRows: " + numberOfRowsSize + " numberofColumns: " + numberOfColumnsCycle);

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
        long results[][] = new long[numberOfRowsSize][numberOfColumnsCycle];

        // row loop
        for (int rowCount = ARRAY_SIZE_MIN, rowIndex = 0; rowCount <= ARRAY_SIZE_MAX; rowCount += ARRAY_SIZE_STEPS,
                rowIndex++) {
            // column loop
            for (int columnCount = CYCLES_MIN, columnIndex = 0; columnCount <= CYCLES_MAX; columnCount +=
                    CYCLES_STEPS, columnIndex++) {
                results[rowIndex][columnIndex] = (long) rowCount;
            }
        }
        return results;
    }


}