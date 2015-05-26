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
    public static int[] generateIntegerLinspace(int min, int max, int incrementSize) {
        assert min > 0 : "array length must be > 0";
        assert max > 0 : "array length must be > 0";
        assert max > min : " max must be > min";
        assert incrementSize > 0 : "incrementSize must be > 0";
        
        int arrayLength = (int) Math.ceil(((max - min)) / incrementSize) + 1;
        ArrayList<Integer> arl = new ArrayList<>(arrayLength);

        arl.add(min);
        int count = min;

        while (count < max) {
            count = count + incrementSize;
            arl.add(count);
        }
        arl.add(max);

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
