package org.hayabaya.datarelated;

import org.hayabaya.RunSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;

/**
 * General Utility functions used to execute the project.
 */
public class Utility {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
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
//            logger.error("The classname was not found and TAG not initialized", e);
        }
    }

    /**
     * Writes the Results datastructure to a .csv file on disk. The results folder is prepended
     * with the "name" yielding name_results and the files are named as
     * name_datatype_operation_repetionX.csv
     *
     * @param results the Results datastructure to be written to disk
     */
    public static void writeResultsV2(Results results) {

       // Utility.logger.info("Writing results to disk with object ", results);
        RunSettings runSettings = RunSettings.getInstance();
        try {
            // Create the results folder
            String name = runSettings.getName();
            String fileDir = "./" + name + "_results";
            File fileDirObject = new File(fileDir);
            if (!fileDirObject.exists()) fileDirObject.mkdir();

            // Create the file and open the connection
            String filename = fileDir + "./" + name + "_res_" + results.type.toString() + "_" +
                    results.operation.toString() + "_rep_" + results.theRepetitionNumber + ".csv";

            // Open up the connection and create a string builder
            BufferedWriter br = new BufferedWriter(new FileWriter(filename));
            StringBuilder header = new StringBuilder();

            // Create the header/first line
            header.append("name,\t" + "DataType,\t" + "isBoxed,\t" + "Repetition, \tArrayLength," +
            "\tCycleNumber, \tRuntime");
            br.write(header.toString());
            br.newLine();

            // Begin writing the actual data
            int arrayLengthNumbers = runSettings.getArrayLengths().length;
            int cycleLengthNumbers = runSettings.getCycleNumbers().length;
            int rowNumbers = arrayLengthNumbers * cycleLengthNumbers;



            // Close the connection to the file and finish off this result
            br.close();

        } catch (FileNotFoundException e) {
//            Utility.logger.error("The folder results does not exists, and cannot create the folder", e);
            e.printStackTrace(System.out);
        } catch (IOException e2) {
//            Utility.logger.error("Error in the BufferedWriter part of writeresults2CSV", e2);
            e2.printStackTrace(System.out);
        }

    }

    public static void writeResultsToCsv(Results results) {
        try {
            //check if directory exists, and create if not
            String fileDir = "./results";
            File fileDirObj = new File(fileDir);
            if (!fileDirObj.exists()) fileDirObj.mkdir();

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
        } catch (FileNotFoundException e) {
            //This should not be triggered
            System.out.println("SHOULD NOT BE TRIGGERED: The /results/ folder does not exist, cannot write file");
            e.printStackTrace(System.out);
        } catch (Exception e) {
            System.out.println("An unknown file exception was thrown..");
            e.printStackTrace(System.out);
        }

    }
}