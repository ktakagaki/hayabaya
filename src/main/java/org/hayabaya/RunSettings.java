package org.hayabaya;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

/**
 * This static class encapsulates the settings for experimental runs
 * which are common for all types (Int/Float/Double, etc.)
 * <p/>
 * Created by cain on 4/20/2015.
 */
public class RunSettings {

    private static RunSettings instance = new RunSettings();

    private String sampleSize;
    private String name;
    private int totalExperimentRepetitions;
    private int[] arrayLengthFromToBy;
    private int[] cycleNumbersFromToBy;

    private int[] arrayLengths, cycleNumbers;


//    private int[] cycleNumbers = {2500, 3844, 5625, 7569, 10000, 12544, 15625, 18769, 22500};
//    private int[] arrayLengths = {100, 400, 900, 1600, 2500, 3600, 4900, 6400, 8100, 10000};

    private RunSettings() {}

    public static RunSettings getInstance() {
        return instance;
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

    public void setName(String n) {
        //if(n == null) throw new IllegalArgumentException("This shouldn't happen")
        //if(n.equals("")) throw new IllegalArgumentException("This shouldn't happen either")
        this.name = Objects.requireNonNull(n, "name must not be null");
    }

    public void setSampleSize(String s) {
        this.sampleSize = Objects.requireNonNull(s, "sampleSize must not be null");
        if (s.equals("small")) {
            this.arrayLengthFromToBy = new int[]{10, 20, 1};
            this.cycleNumbersFromToBy = new int[]{10, 200, 20};
        } else if (s.equals("medium")) {
            this.arrayLengthFromToBy = new int[]{100, 200, 10};
            this.cycleNumbersFromToBy = new int[]{1000, 20000, 2000};
        } else if (s.equals("large")) {
            this.arrayLengthFromToBy = new int[]{1000, 200000, 1000};
            this.cycleNumbersFromToBy = new int[]{10000, 200000, 20000};
        } else {
            throw new IllegalArgumentException("specify small/medium/large for 2nd argument!");
        }
        generateArrays(this.arrayLengthFromToBy, this.cycleNumbersFromToBy);
    }

    private void generateArrays(int[] arl, int[] cnf){
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
        if (r <= 0 || 100 <= r) {
            throw new IllegalArgumentException("third argument must be greater than zero and less than 100!");
        }
        this.totalExperimentRepetitions = Objects.requireNonNull(r, "Repetitions must not be null");
    }

    public int[] getArrayLengths() {
        return arrayLengths;
    }

    public int[] getCycleNumbers() {
        return cycleNumbers;
    }

}
