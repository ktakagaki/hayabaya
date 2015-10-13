package org.hayabaya.datarelated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * General Utility functions used to execute the project.
 */
public class Utility {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Utility.class);



    /**
     * The old version of the function that was used to write the Results datastructure to disk as csv files. The
     * function is now deprecated, but kept as a backup in case the new function should fail.
     * @deprecated
     * @param results The results datastructure to be written to disk as a csv file
     */
    @Deprecated
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


    /**
     * Ensures that correct number of arguments are passet to Hayabaya from the commandline
     * @param args
     * @throws IllegalArgumentException
     */
    public static void validateArgsLength(String[] args) throws IllegalArgumentException {
        if (args.length != 3) throw new IllegalArgumentException("Give 3 arguments");

    }

    /**
     * Ensures that the type and value of args passed to main are of the correct type
     * @param args String[]
     * @throws IllegalArgumentException
     */
    public static void validateArgsValues(String[] args) throws IllegalArgumentException {
        String name = args[0];
        String sampleSize = args[1].toLowerCase();
        String repetitions = args[2];

        Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]");
        Matcher matcher = pattern.matcher(name);

        if (matcher.find() || name.length() <= 0 || name.length() > 20) throw new IllegalArgumentException("Bad name " +
                "arguments");

        if (!sampleSize.matches("small|medium|large")) throw new IllegalArgumentException("sample size must be either" +
                " \"small\" , \"medium\" or \"large\" ");

        if (!isInteger(repetitions)) throw new NumberFormatException("non integer rep argument");
        if (isInteger(repetitions)) {
            int reps = Integer.parseInt(args[2]);
            if (reps <= 0) throw new NumberFormatException("repetitions must be > 0");
            if (reps > 100) throw new NumberFormatException("too many repetitions");
        }
    }

    /**
     * Validate that an element from a String[] array is an integer
     * @param str String[] array
     * @return true if element is integer, false if it is not
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c <= '/' || c >= ':') {
                return false;
            }
        }
        return true;
    }
}