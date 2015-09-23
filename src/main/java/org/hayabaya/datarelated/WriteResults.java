package org.hayabaya.datarelated;

import org.hayabaya.RunSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteResults {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(WriteResults.class);

    /**
     * Writes the Results datastructure to a .csv file on disk. The results folder is prepended
     * with the "name" yielding name_results and the files are named as
     * name_datatype_operation_repetionX.csv
     *
     * @param results the Results datastructure to be written to disk
     */
    public static void writeResultsV2(Results results) {
        logger.trace("writeResultsV2 called with the following arguments: \n" +
                "Results name: {} \n" +
                "Results type: {} \n" +
                "Results Operation: {} \n" +
                "Repetition Number: {} \n" +
                "isBoxed: {} \n" +
                "Data: ", results.getName(), results.getType(), results.getOperation(), results
                .getTheRepetitionNumber(), results.isBoxed);


        RunSettings runSettings = RunSettings.getRunSettings();

        String wd = System.getProperty("user.dir");
        String nameCPU = runSettings.getNameOfProcessor();
        Path outPutFolderPath = Paths.get(wd + "/" + nameCPU);
        logger.debug("Path to results folder: {} \n", outPutFolderPath);

        // check if dir exists
        // if dir, then verify read/write access
        // else create dir
        try {
            boolean folderExists = Files.exists(outPutFolderPath);
            boolean folderRights = Files.isWritable(outPutFolderPath) && Files.isWritable(outPutFolderPath);
            logger.debug("foldreExists: {} \n folderRights: {} \n", folderExists, folderExists);

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


        // Create result string to be writtten to disk
        try {
            String newline = System.getProperty("line.separator"); // Windows or Linux?
            StringBuilder sb = new StringBuilder();

            // Create the header in the csv file
            sb.append("name, DataType, isBoxed, operation, Repetition, ArrayLength " +
                    "CycleNumber, RunTime");
            sb.append(newline);


            String fileText = sb.toString();

            Path resultFilePath = Paths.get(outPutFolderPath + "/" + results.getFileName());
            File newTextFile = new File(resultFilePath.toString());

            FileWriter fw = new FileWriter(newTextFile);
            fw.write(fileText);
            fw.close();
            logger.debug("Wrote file to disk");
        } catch (IOException e) {
            logger.error("File error {}", e);

        }



//
//        try {
//
//
//        } catch (Exception e) {
//
//        }
//
//            // Create needed local data
//
//
//
//            // Create the results folder
//            String fileDir = nameCPU + "_results";
//            File fileDirObject = new File(fileDir);
//            //ToDo: Test if folder exits and ensure it works on windows AND linux
//            if (!fileDirObject.exists()) fileDirObject.mkdir();
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




        ///// resultLine.toString()... never cast to a value!!!
        ///// The cast below may be redundant
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
}