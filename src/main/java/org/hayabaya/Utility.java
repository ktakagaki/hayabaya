package org.hayabaya;

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
            String filename = "./results/results." + /* results.type.toString() + "." + */
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

}