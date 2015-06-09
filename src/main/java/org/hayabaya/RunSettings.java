package org.hayabaya;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**This static class encapsulates the settings for experimental runs
 * which are common for all types (Int/Float/Double, etc.)
 *
 * Created by cain on 4/20/2015.
 */
public class RunSettings {
    private static RunSettings instance = new RunSettings();

    private int[] arrayLengthFromToBy = {1, 10, 1};
    private int[] cycleNumbersFromToBy = {10, 50, 10};
    private int totalExperimentRepetitions = 2;

    private int[] arrayLengths = generateIntegerLinearSpace(
            arrayLengthFromToBy[0],
            arrayLengthFromToBy[1],
            arrayLengthFromToBy[2]);
    private int[] cycleNumbers = generateIntegerLinearSpace(
            cycleNumbersFromToBy[0],
            cycleNumbersFromToBy[1],
            cycleNumbersFromToBy[2]);

    private int[] arrayLengthsX2 = generateBaseNSpace(2, arrayLengths);
    private int[] cycleNumbersX2 = generateBaseNSpace(2, cycleNumbers);


    private RunSettings() {
        System.out.println("RunSettings() Singleton being instantiated...");
        System.out.println("Total repetitions: "      +totalExperimentRepetitions);
        System.out.println("arrayLengthFromToBy: "    +Arrays.toString(arrayLengthFromToBy));
        System.out.println("Resulting ArrayLengths: " +Arrays.toString(arrayLengths));
        System.out.println("cycleNumbersFromToBy: "   +Arrays.toString(cycleNumbersFromToBy));
        System.out.println("Resulting cycleNumbers: " +Arrays.toString(cycleNumbers));
    }

    public static RunSettings getInstance() {
        return instance;
    }

    public static int[] generateBaseNSpace(int base, int[] linearArray) {
        int[] result = linearArray;
        for (int element : result) {
            element = (int) Math.pow((double) base, (double) element);
        }
        return result;
    }

    /**
     * Create an array of int[] starting from minimumValue, going up to (inclusive) maximumValue, in increments of
     * incrementSize.
     *
     * @param minimumValue  The starting value for the array
     * @param maximumValue  The ending value for the array
     * @param incrementSize The increment size by which the elements of the array will increase by
     * @return A variable length int[] array, in fencepost cases the method will be inclusive
     */
    public static int[] generateIntegerLinearSpace(int minimumValue, int maximumValue, int incrementSize) {
        assert minimumValue > 0 : "array length must be > 0";
        assert maximumValue > 0 : "array length must be > 0";
        assert maximumValue > minimumValue : " maximumValue must be > minimumValue";
        assert incrementSize > 0 : "incrementSize must be > 0";

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

    /**
     * <b>(copied from online)</b> Convert an ArrayList of Integers to a Java primitive int[]
     * array, while avoiding Java throwing a
     * NullPointerException if any of the elements are null.
     *
     * @see <a href="http://stackoverflow.com/questions/718554/how-to-convert-an-arraylist-containing-integers-to
     * -primitive-int-array">The question on StackExchange</a>
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
            String fileDir = "./results";
            File fileDirObj = new File(fileDir);
            if (!fileDirObj.exists()) fileDirObj.mkdir();

            String filename = fileDir + "./A_" + "RunSettings.txt";

            BufferedWriter br = new BufferedWriter(new FileWriter(filename));
            StringBuilder sb = new StringBuilder();
            {
                sb.append("Using the following runsettings");
                sb.append("Total repetitions: " + totalExperimentRepetitions + "\n");
                sb.append("arrayLengthFromToBy: " + Arrays.toString(arrayLengthFromToBy) + "\n");
                sb.append("Resulting ArrayLengths: " + Arrays.toString(arrayLengths) + "\n");
                sb.append("cycleNumbersFromToBy: " + Arrays.toString(cycleNumbersFromToBy) + "\n");
                sb.append("Resulting cycleNumbers: " + Arrays.toString(cycleNumbers) + "\n");
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

    public int[] getArrayLengthFromToBy() {
        return arrayLengthFromToBy;
    }

    public int[] getCycleNumbersFromToBy() {
        return cycleNumbersFromToBy;
    }

    public int getTotalExperimentRepetitions() {
        return totalExperimentRepetitions;
    }

    public int[] getArrayLengths() {
        return arrayLengths;
    }

    public int[] getCycleNumbers() {
        return cycleNumbers;
    }

}
