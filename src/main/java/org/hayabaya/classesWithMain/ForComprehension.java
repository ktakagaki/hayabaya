package org.hayabaya.classesWithMain;

import java.util.Arrays;
import java.lang.Math;

/**
 * Created by cain on 5/26/2015.
 */
public class ForComprehension {
    public static void main(String[] args) {
        int myMin = 1;
        int myMax = 100;
        int steps = 10;
        System.out.println("The result of generateIntegerLinspace(" +myMin+ ", " +myMax+ ", " +steps+ ")");

        int[] test = generateIntegerLinspace(myMin, myMax, steps);

        System.out.println(Arrays.toString(test));


    }
    public static int[] generateIntegerLinspace(int min, int max, int steps) {
        assert min > 0 : "array length must be > 0";
        assert max > 0 : "array length must be > 0";
        assert steps > 0 : "steps must be > 0";

        int arrayLength = (int) Math.ceil(((max - min)) / steps) + 1;
        int result[] = new int[arrayLength];
        int dy = (max - min) / (steps -1);

        for (int i = 0; i < steps; i++){
            result[i] = min + (dy * i);
        }

        return result;
    }
}
