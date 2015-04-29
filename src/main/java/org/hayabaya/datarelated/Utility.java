package org.hayabaya.datarelated;

import org.hayabaya.RunSettings;
import org.hayabaya.loopers.Loopers;

import java.io.*;
import java.util.Arrays;

/**
 * Created by ktakagaki on 15/04/09.
 */
public class Utility {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private static int resultCounter = 0;
    @Deprecated
    public static void setResultCounter(int i) {
        resultCounter = i;
    }

    public static void writeResultsToCsv(Results results) {

        assert results != null : "Results can't be null!";

        try
        {
            //check if directory exists, and create if not
            String fileDir = "./results";
            File fileDirObj = new File(fileDir);
            if(!fileDirObj.exists()) fileDirObj.mkdir();

            String filename = fileDir  + "/results." + results.type.toString() + "." +
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
            //This should not be triggered
            System.out.println("SHOULD NOT BE TRIGGERED: The /results/ folder does not exist, cannot write file");
            e.printStackTrace(System.out);
        }catch ( Exception e)
        {
            System.out.println("An unknown file exception was thrown..");
            e.printStackTrace(System.out);
        }

    }

//    public static void printMatrix(long[][] grid) {
//        for(int r=0; r<grid.length; r++) {
//            for(int c=0; c<grid[r].length; c++)
//                System.out.print(grid[r][c] + " ");
//            System.out.println();
//        }
//    }
//
//    public static long[][] showValues(Loopers looperObject, Operation operation) {
//        /**
//         * A quick and dirty function used to check that the MakeIntResults function works correctly
//         * To check that arraylength and repetition values are correct, just switch rowCount/columnCount in last line
//         * of the inner loop.
//         * //ToDo: Make method able to produce complete results passing 2D values
//         */
//        long results[][] = new long[RunSettings.numberOfRowsArrayLength][RunSettings.numberOfColumnsCycle];
//
//        // row loop
//        for (int rowCount = RunSettings.ARRAY_SIZE_MIN, rowIndex = 0; rowCount <= RunSettings.ARRAY_SIZE_MAX; rowCount += RunSettings.ARRAY_SIZE_STEPS,
//                rowIndex++) {
//            // column loop
//            for (int columnCount = RunSettings.CYCLES_MIN, columnIndex = 0; columnCount <= RunSettings.CYCLES_MAX; columnCount +=
//                    RunSettings.CYCLES_STEPS, columnIndex++) {
//                results[rowIndex][columnIndex] = (long) rowCount;
//            }
//        }
//        return results;
//    }
//
}