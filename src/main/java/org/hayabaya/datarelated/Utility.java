package org.hayabaya.datarelated;

import org.hayabaya.RunSettings;
import org.hayabaya.loopers.Loopers;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by ktakagaki on 15/04/09.
 */
public class Utility {

    private static int resultCounter = 0;
    public static void setResultCounter(int i) {
        resultCounter = i;
    }

    public static void writeResultsToCsv(Results results) {
        try
        {
            //ToDo: results.type.toString() gives nullpointer exception. Seems that type is never initialized
            String filename = "./results/results."  + results.type.toString() + "." +
                    results.operation.toString() + "." + Integer.toString(resultCounter) + ".csv";


            BufferedWriter br = new BufferedWriter(new FileWriter(filename));
            StringBuilder sb = new StringBuilder();
            for (long[] element : results.data) {
                sb.append(Arrays.toString(element));
                sb.append("\n");
            }
//            resultCounter ++;

            br.write(sb.toString());
            br.close();
        }catch( FileNotFoundException e )
        {
            System.out.println("The /results/ folder does not exist, cannot write file");
            e.printStackTrace(System.out);
        }catch ( Exception e)
        {
            System.out.println("An unknown file exception was thrown..");
            e.printStackTrace(System.out);
        }

    }

    public static void printMatrix(long[][] grid) {
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