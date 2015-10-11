package org.hayabaya;

import org.hayabaya.datarelated.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


/**
 * All data related to the profiling experiment are saved in this singleton. This includes which of the data types
 * are to be profiled and with what operations they are to be profiled. Information on the minimum length of arrays
 * to test, their maximum length and the number of steps in between is also saved. Likewise the "CycleNumber"
 * indicating how many times each operation is to be performed on each element of an array is saved in this class.
 * The last type of information saved is the total number of times each experiment should be replicated.
 * Created by cain on 4/20/2015.
 */
public class RunSettings {
    private static RunSettings runSettingsInstance = new RunSettings();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String sampleSize;
    private String nameOfProcessor;
    private int totalExperimentRepetitions;
    private int[] arrayLengthFromToBy;
    private int[] cycleNumbersFromToBy;
    private int[] arrayLengths, cycleNumbers;


    // Singleton constructor
    private RunSettings() {}

    public static RunSettings getRunSettingsInstance() {
        return runSettingsInstance;
    }


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
        if (minimumValue < 0) throw new IllegalArgumentException("minimumValue must be > 0");

//  Could do something like this:
//        if (minimumValue < 0) {
//            IllegalArgumentException temp = new IllegalArgumentException("minimumValue must be > 0");
//            logger.error(temp.getMessage(), temp);
//            throw temp;
//        }
        //Or like this:
        Utility.loggerRequire( minimumValue<0, "minimumValue must be > 0");

        if (incrementSize >= (maximumValue - minimumValue)) throw new IllegalArgumentException("Increment too large");
        if (minimumValue >= maximumValue) throw new IllegalArgumentException("Max must be > min");
        if (maximumValue <= 0) throw new IllegalArgumentException("Max must be > 0");
        if (incrementSize < 0) throw new IllegalArgumentException("Negative increments are impossible");
        if (((maximumValue - minimumValue) / incrementSize) < 2){
            throw new IllegalArgumentException("Increment is too large compared to max and min value ");
        }


        int arrayLength = (int) Math.ceil(((maximumValue - minimumValue)) / incrementSize) + 1; // Estimate the length
        ArrayList<Integer> arl = new ArrayList<>(arrayLength + 5); // Adding a couple of elements for safety

        arl.add(minimumValue);
        int count = minimumValue;

        while (count < maximumValue) {
            count = count + incrementSize;
            arl.add(count);
        }

        int[] result = convertListToPrimitiveArray(arl);

        return result;
    }

    /**
     * Exponentiate each element of an array by another number.
     * <pre>
     *     int[] a = {1, 2, 3, 4}
     *     int[] b = exponentiateArray(2, a)
     *     // a = [2, 4, 8, 16]
     *     exponentiateArray(3, {1, 2, 3, 4}) == [3, 9, 27, 81]
     *
     * </pre>
     * @param exponent What number to exponentiate each of the elements into
     * @param linearArray An integer array to be exponentiated
     * @return An integer array where each element has been raised to the power of the exponent
     */
    public static int[] exponentiateArray(int exponent, int[] linearArray) {
        int[] result = linearArray.clone();

        for (int i = 0; i < result.length; i++) {
            result[i] = (int) Math.pow((double) exponent, (double) result[i]);
        }
        return result;
    }

    /**
     * Convert a Java ArrayList of integers into a primitive int[] array. This function does not throw an exception
     * if any of the elements are null.
     *
     * @param integers list of integers to convert
     * @return int[] returns an array of ints
     */
    public static int[] convertListToPrimitiveArray(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }

    public String getNameOfProcessor() {
        return nameOfProcessor;
    }

    public void setNameOfProcessor(String n) {
        this.nameOfProcessor = Objects.requireNonNull(n, "nameOfProcessor must not be null");
    }

    public void setSampleSize(String s) {
        this.sampleSize = Objects.requireNonNull(s, "sampleSize must not be null");
        if (s.equals("small")) {
            this.arrayLengthFromToBy = new int[]{10, 20, 1};
            this.cycleNumbersFromToBy = new int[]{10, 200, 20};
        } else if (s.equals("medium")) {
            this.arrayLengthFromToBy = new int[]{3000, 5000, 1000};
            this.cycleNumbersFromToBy = new int[]{6000, 9000, 1000};
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
