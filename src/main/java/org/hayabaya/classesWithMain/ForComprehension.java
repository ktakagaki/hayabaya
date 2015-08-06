package org.hayabaya.classesWithMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cain on 5/26/2015.
 */
public class ForComprehension {
    public static void main(String[] args) {
        System.out.println("Calling f(10, 100, 10): ");
        System.out.println(Arrays.toString(generateIntegerLinspace(10, 100, 10)));

        System.out.println("Calling f(1, 10, 1): ");
        System.out.println(Arrays.toString(generateIntegerLinspace(1, 10, 1)));

        System.out.println("Calling f(5, 50, 10): ");
        System.out.println(Arrays.toString(generateIntegerLinspace(5, 50, 10)));


    }
    public static int[] generateIntegerLinspace(int minimumValue, int maximumValue, int incrementSize) {
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
     * @see <a href="http://stackoverflow.com/questions/718554/how-to-convert-an-arraylist-containing-integers-to
     * -primitive-int-array">The question on StackExchange</a>
     */
    public static int[] convertToPrimitiveArray(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }
}
