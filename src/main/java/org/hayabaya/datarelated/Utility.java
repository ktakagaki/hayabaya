package org.hayabaya.datarelated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Arrays;

/**
 * General Utility functions used to execute the project.
 */
public class Utility {
    private static String TAG;
    private static Class<?> cls;
    private static Logger logger;
    static {
        try {
            Class<?> cls = Class.forName("org.hayabaya.datarelated.Utility");
            TAG = cls.toString();
            Logger logger = LoggerFactory.getLogger(cls);
            logger.info("Utility logger sucessfully initialized");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("The classname was not found and TAG not initialized", e);
        }
    }


    public static void writeResultsToCsv(Results results) {
        try
        {
            //check if directory exists, and create if not
            String fileDir = "./results";
            File fileDirObj = new File(fileDir);
            if(!fileDirObj.exists()) fileDirObj.mkdir();

            String filename = fileDir + "./res_" + results.type.toString() + "_" +
                    results.operation.toString() + "_rep_" + results.theRepetitionNumber + ".csv";

            BufferedWriter br = new BufferedWriter(new FileWriter(filename));
            StringBuilder sb = new StringBuilder();
            for (long[] element : results.data) {
                sb.append(Arrays.toString(element));
                sb.append("\n");
            }

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

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
}