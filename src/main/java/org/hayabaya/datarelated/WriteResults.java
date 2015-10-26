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
     * Calls the #dumpvars function And writes all of the System.getProperties resutls from the Java Virtual Machine to
     * disk
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

    /**
     * Flattens a 2D array of RunTime results (Long) in milliseconds with increasing CycleNumbers and ArrayLengths
     * out of the 2 dimenstions. The flattening will result in a 1D list of Key=ArrayLength,CycleNumber and
     * Value=Runtime suitable for insertion into a flat csv file format
     * @param results 2D Long[][] array of Runtime results
     * @return List\<String\> [ArrayLength, CycleNumber, RunTime]
     */
    private static List<String> flattenResults(Results results) {
        RunSettings runSettings = RunSettings.getRunSettingsInstance();
        List<String> rValue = new ArrayList<>();
        rValue.add(results.getCSVHeader());

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
            }
        }
        return rValue;
    }

}