package org.hayabaya.datarelated;

import org.hayabaya.MainClass;
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

    //ToDo 1: implement
    //public static void writeResultsToCsv(Results results, int repetitionTag) {

    @Deprecated
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
            MainClass.logger.debug("Wrote results to csv for: " + filename);
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
}