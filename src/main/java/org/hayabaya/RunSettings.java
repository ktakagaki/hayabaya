package org.hayabaya;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;


/**
 * All data related to the profiling experiment are saved in this singleton. This includes which of the data types
 * are to be profiled and with what operations they are to be profiled. Information on the minimum length of arrays
 * to test, their maximum length and the number of steps in between is also saved. Likewise the "CycleNumber"
 * indicating how many times each operation is to be performed on each element of an array is saved in this class.
 * The last type of information saved is the total number of times each experiment should be replicated.
 * Created by cain on 4/20/2015.
 */
public class RunSettings {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static RunSettings runSettings = new RunSettings();

    private String sampleSize;
    private String nameOfProcessor;
    private int totalExperimentRepetitions;
    private int[] arrayLengthFromToBy;
    private int[] cycleNumbersFromToBy;
    private int[] arrayLengths, cycleNumbers;


    // Singleton constructor
    private RunSettings() {}

    public static RunSettings getRunSettings() { return runSettings; }


    /**
     * Create an int[] array by specifying [From, To, ByStep]. Example: [1,6,2]=[1,3,5]. <b>Be aware that in "fencepost" cases like (1,10,3) the function will be inclusive and add as needed to the array. This mean that the last element may exceed the specified MAX value</b>
     * Create an array of int[] starting from minimumValue, going up to (inclusive) maximumValue, in increments of
     * incrementSize.
     *
     * @param minimumValue  The starting value for the array
     * @param maximumValue  The ending value for the array
     * @param incrementSize The increment size by which the elements of the array will increase by
     * @return A variable length int[] array, in fencepost cases the method will be inclusive
     */
    public static int[] generateIntegerLinearSpace(int minimumValue, int maximumValue, int incrementSize) {
        // By DeMorgans law (!a OR !b OR !c) == !(a OR b OR c) which is simpler to read
        if (!(minimumValue >= maximumValue ||
            incrementSize >= (maximumValue - minimumValue) ||
            minimumValue < 0 ||
            maximumValue < 0 ||
            incrementSize < 0 ||
            (maximumValue - minimumValue) / incrementSize > 1)){
            throw new IllegalArgumentException("Can't generate int[] from argument values");
        }

        /*
        Use a dynamic Arraylist for the intermediate calculation. Initialize it with arraylength + 5 to make sure that
        at no point will Java have to do any dynamic work and re-allocate a new arraylist (for performance).
         */
        int arrayLength = (int) Math.ceil(((maximumValue - minimumValue)) / incrementSize) + 1; // Estimate the length
        ArrayList<Integer> arl = new ArrayList<>(arrayLength + 5); // Adding a couple of elements for safety

        arl.add(minimumValue);
        int count = minimumValue;

        while (count < maximumValue) {
            count = count + incrementSize;
            arl.add(count);
        }

        int[] result = convertToPrimitiveArray(arl);

        return result;
    }

    public static int[] generateBaseNSpace(int base, int[] linearArray) {
        int[] result = linearArray.clone();
        for (int element : result) {
            element = (int) Math.pow((double) base, (double) element);
        }
        return result;
    }

    /**
     * <b>(copied from online)</b> Convert an ArrayList of Integers to a Java primitive int[]
     * array, while avoiding Java throwing a
     * NullPointerException if any of the elements are null.
     *
     * @param integers list of integers to convert
     * @return int[] returns an array of ints
     */
    public static int[] convertToPrimitiveArray(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }

    /**
     * Write the used runsettings to disk so it can be stored together with the results for later analysis. Utility
     * .writeResultsToCSV will in the future be updated to also include the meta data from runsettings, this is only
     * a temporary solution to ensure that the meta data is still saved in case the meta data should fail to be
     * included by writeResultsToCSV().
     */
    public void writeRunSettingsToDisk() {
        try {
            //check if directory exists, and create if not
            String fileDir = nameOfProcessor +"_results/";
            File fileDirObj = new File(fileDir);
            if (!fileDirObj.exists()) fileDirObj.mkdir();

            String filename = fileDir + "./A_" + "RunSettings.txt";

            BufferedWriter br = new BufferedWriter(new FileWriter(filename));
            StringBuilder sb = new StringBuilder();
            {
                sb.append("repetitions: " + totalExperimentRepetitions + System.getProperty("line.separator"));
                sb.append("ArrayLengths: " + Arrays.toString(arrayLengths) + System.getProperty("line.separator"));
                sb.append("CycleNumbers: " + Arrays.toString(cycleNumbers) + System.getProperty("line.separator"));
                sb.append(System.getProperty("line.separator"));
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

    public void setNameOfProcessor(String n) {
        //if(n == null) throw new IllegalArgumentException("This shouldn't happen")
        //if(n.equals("")) throw new IllegalArgumentException("This shouldn't happen either")
        this.nameOfProcessor = Objects.requireNonNull(n, "nameOfProcessor must not be null");
    }

    public String getNameOfProcessor() {
        return nameOfProcessor;
    }

    public void setSampleSize(String s) {
        this.sampleSize = Objects.requireNonNull(s, "sampleSize must not be null");
        if (s.equals("small")) {
            this.arrayLengthFromToBy = new int[]{10, 20, 1};
            this.cycleNumbersFromToBy = new int[]{10, 200, 20};
        } else if (s.equals("medium")) {
            this.arrayLengthFromToBy = new int[]{10000, 50000, 10000};
            this.cycleNumbersFromToBy = new int[]{1000, 5000, 1000};
        } else if (s.equals("large")) {
            this.arrayLengthFromToBy = new int[]{10000, 50000, 5000};
            this.cycleNumbersFromToBy = new int[]{1000, 10000, 1000};
        } else {
            throw new IllegalArgumentException("specify small/medium/large for 2nd argument!");
        }
        generateArrays(this.arrayLengthFromToBy, this.cycleNumbersFromToBy);
    }

    private void generateArrays(int[] arl, int[] cnf) {
        this.arrayLengths = generateIntegerLinearSpace(
                arl[0],
                arl[1],
                arl[2]);
        this.cycleNumbers = generateIntegerLinearSpace(
                cnf[0],
                cnf[1],
                cnf[2]);
    }

    public int getTotalExperimentRepetitions() {
        return totalExperimentRepetitions;
    }

    public void setTotalExperimentRepetitions(int r) {
        try {
//            if (r <= 0 || 100 <= r) {
//                throw new IllegalArgumentException("third argument must be greater than zero and less than 100!");
//            }
            this.totalExperimentRepetitions = Objects.requireNonNull(r, "Repetitions must not be null");
        } catch (IllegalArgumentException e){
            logger.error("Illegal usage of repetitions arguments", e);
        }
    }

    public int[] getArrayLengths() {
        return arrayLengths;
    }

    public int[] getCycleNumbers() {
        return cycleNumbers;
    }

}
