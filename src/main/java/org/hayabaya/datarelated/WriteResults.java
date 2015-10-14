package org.hayabaya.datarelated;

import org.hayabaya.RunSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WriteResults {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(WriteResults.class);

    /**
     * Get all of the JVM properties
     *
     * @param m A  hashmap of either System.getEnv og System.getProperties
     * @return List A List of strings for the System/Environment properties
     */
    private static List<String> dumpVars(Map<String, ?> m) {
        List<String> keys = new ArrayList<String>(m.keySet());
        Collections.sort(keys);
        List<String> linesOfValues = new ArrayList<>();

        for (String k : keys) {
            String line = k + " : " + m.get(k);
            linesOfValues.add(line);
        }
        return linesOfValues;
    }

    /**
     * Calls dumpvars And writes them to disk
     */
    public static void writeJVMValuesToDisk() {

        String outPutFileName = "SystemPropertiesJVM.txt";
        String pathSeperator = System.getProperty("file.separator");
        Path outPath = Paths.get(ensureResultFolder() +pathSeperator + outPutFileName);
        List<String> listToWrite = dumpVars(new HashMap(System.getProperties()));

        logger.debug("Attempting to write JVM values to disk");
        try {
            Files.write(outPath, listToWrite, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error("Writing JVM values files with {}", e);
            e.printStackTrace();
        }
    }

    /**
     * Ensure that the folder for the results exits and if it does that it has the right permissions. If the folder
     * does not exist, create the folder
     * @return A system independent path to the resultsfolder
     */
    static Path ensureResultFolder() {
        RunSettings runSettings = RunSettings.getRunSettingsInstance();

        String wd = System.getProperty("user.dir");
        String nameCPU = runSettings.getNameOfProcessor();
        String pathSeperator = System.getProperty("file.separator");
        Path outPutFolderPath = Paths.get(wd + pathSeperator + nameCPU);
        logger.debug("Path to results folder: {} \n", outPutFolderPath);

        try {
            boolean folderExists = Files.exists(outPutFolderPath);
            boolean folderRights = Files.isWritable(outPutFolderPath) && Files.isWritable(outPutFolderPath);
            logger.debug("folder Exists: {} \n folderRights: {} \n", folderExists, folderExists);

            if(!folderExists) {
                logger.debug("output folder {} did not exist - creating it..", outPutFolderPath);
                Files.createDirectory(outPutFolderPath);
            }
            if (folderExists && !folderRights) {
                logger.warn("cant write to results folder {}");
            }
        } catch (IOException e) {
            logger.error("got exception when creating results folder: {}", e);
        }
        return outPutFolderPath;
    }


    /**
     * Writes the Results datastructure to a .csv file on disk. The results folder is prepended
     * with the "name" yielding name_results and the files are named as
     * name_datatype_operation_repetionX.csv
     *
     * @param results the Results datastructure to be written to disk
     */
    public static void writeResultsV2(Results results) {

        String pathSeperator = System.getProperty("file.separator");

        Path outPutFolderPath = ensureResultFolder();

        String fileName = results.getFileName();

        Path outPath = Paths.get(outPutFolderPath + pathSeperator + fileName);

        List<String> flatResults = flattenResults(results);

        logger.debug("Attempting to write {} to file", fileName);

        try {
            Files.write(outPath,flatResults, StandardCharsets.UTF_8);
        }catch (IOException e) {
            logger.error("Writing file {} failed with {}", fileName, e);
            e.printStackTrace();
        }


    }

    private static List<String> flattenResults(Results results) {
        RunSettings runSettings = RunSettings.getRunSettingsInstance();
        String newline = System.getProperty("line.separator");

        List<String> rValue = new ArrayList<>();
        rValue.add(results.getCSVHeader());
        rValue.add(newline);

        int[] arrayLengths = runSettings.getArrayLengths();
        int[] cycleNumbers = runSettings.getCycleNumbers();

        String lineBody = results.getLineBody();

        for (int i = 0; i < results.data.length; i++) { // traverse the rows

            for (int j = 0; j < results.data[i].length; j++) { // traverse each column

                int al = arrayLengths[i];
                int cn = cycleNumbers[j];
                Long runTime = results.data[i][j];

                String toAdd = lineBody + al + "," + cn + "," + runTime;
                rValue.add(toAdd);
                rValue.add(newline);
            }
        }
        return rValue;
    }


//        try {
//            StringBuilder sb = new StringBuilder();
//
//            sb.append(results.getCSVHeader());
//            sb.append(newline);
//
//
//            String fileText = sb.toString();
//
//            Path resultFilePath = Paths.get(outPutFolderPath + pathSeperator + results.getFileName());
//            File newTextFile = new File(resultFilePath.toString());
//
//            FileWriter fw = new FileWriter(newTextFile);
//            fw.write(fileText);
//            fw.close();
//            logger.debug("Wrote file to disk");
//        } catch (IOException e) {
//            logger.error("File error {}", e);
//        }
//

//            // Create the file and open the connection
//            String filename = fileDir + "/" + nameCPU + "_res_" + dataType + "_" +
//                    operation + "_rep_" + repetitionNumber + ".csv";
//
//            // Open up the connection and create a string builder
//            BufferedWriter br = new BufferedWriter(new FileWriter(filename));
//            StringBuilder header = new StringBuilder();
//
//            // Create the header/first line
//            header.append("name,\t\t DataType,\t isBoxed,\t operation,\t Repetition,\t ArrayLength\t " +
//                    "CycleNumber,\t RunTime,\t");
//            br.write(header.toString());
//            br.newLine();
//
//            // Begin writing the actual data
//            int arrayLengthNumbers = runSettings.getArrayLengths().length;
//            int cycleLengthNumbers = runSettings.getCycleNumbers().length;
//            int rowNumbers = arrayLengthNumbers * cycleLengthNumbers;
//
//
//            //Start producing each line
//            int[] arrayLengths = runSettings.getArrayLengths();
//            int[] cycleNumbers = runSettings.getCycleNumbers();
//            int arrayLen = arrayLengths.length;
//            int cycleLen = cycleNumbers.length;
//
//            int rowIndex = 0, columnIndex = 0;
//
//            //StringBuilder rowLine = new StringBuilder();
//            //rowLine.append(resultLine);
//            // Missing values are ArrayLength CycleNumber and Runtime
//            String resultLine = nameCPU + ",\t   " + dataType + ",\t   " + isBoxed + ",\t\t" + operation +
//                    ",\t\t" + repetitionNumber + ",\t\t\t";
//            // Create the strinBuilder for adding the missing values and output on each line
//
//            // RowLoop
//            for (int i = 0; i < arrayLen; i++) {
//
//                //Column Loop
//                for (int j = 0; j < cycleLen; j++) {
//
//                    int arrayLength = arrayLengths[i];
//                    int cycleNumber = cycleNumbers[j];
//                    long runTime = results.data[i][j];
//
//                    String toAppend = (String) resultLine + " " + arrayLength + ",\t\t\t\t" + cycleNumber + "," +
//                            "\t\t\t\t" + runTime + ",\t\t\t";
//                    br.write(toAppend);
//                    br.newLine();
//                }
//            }
//
//            br.newLine();
//            br.close();
//
///*            for (int i = 0; i <= rowNumbers; i++) {
//            }*/
//
//
//            // Close the connection to the file and finish off this result
//            br.close();
//
//        } catch (FileNotFoundException e) {
////            Utility.logger.error("[linux]The folder results does not exists, and cannot create the folder", e);
//            e.printStackTrace(System.out);
//        } catch (IOException e2) {
////            Utility.logger.error("Error in the BufferedWriter part of writeresults2CSV", e2);
//            e2.printStackTrace(System.out);
//        }
}