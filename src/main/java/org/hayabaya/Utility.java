package org.hayabaya;

import org.hayabaya.loopers.Loopers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by ktakagaki on 15/04/09.
 */
public class Utility {

    private static int resultCounter = 0;

    public static void writeResultsToCsv(Results results){
        try
        {
            //ToDo: results.type.toString() gives nullpointer exception. Seems that type is never initialized
            String filename = "./results/results."  + /* results.type.toString() + "." + */
                    results.operation.toString() + "." + Integer.toString(resultCounter) + ".csv";


            BufferedWriter br = new BufferedWriter(new FileWriter(filename));
            StringBuilder sb = new StringBuilder();
            for (long[] element : results.data) {
                sb.append(Arrays.toString(element));
                sb.append("\n");
            }
            resultCounter ++;

            br.write(sb.toString());
            br.close();
        }catch( Exception e )
        {
            System.out.println("An Exception was given from writeResultsToCsv() ");
            e.printStackTrace(System.out);
        }

    }

    @Deprecated
    public static void writeResultsToCsv(long[][] resultsToWrite, String filename) {
        try
        {
            BufferedWriter br = new BufferedWriter(new FileWriter(filename + ".csv"));
            StringBuilder sb = new StringBuilder();
            for (long[] element : resultsToWrite) {
                sb.append(Arrays.toString(element));
                sb.append("\n");
            }

            br.write(sb.toString());
            br.close();
        }catch(IOException e1)
        {
            System.out.println("An IOException was given from writeResultsToCsv() ");
        }

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