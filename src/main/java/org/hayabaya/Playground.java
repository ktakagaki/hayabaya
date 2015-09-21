package org.hayabaya;

import java.util.Arrays;

/**
 * Created by cain on 21/09/15.
 */
public class Playground {
    public static void main(String[] args) {

        RunSettings runSettings = RunSettings.getRunSettings();

        int[] test1 = runSettings.generateIntegerLinearSpace(1, 10, 1);

        for (int i = 0; i < test1.length; i++){
            System.out.println("i: " + i + " and ar[i]= " + test1[i]);
        }
        int[] squared = runSettings.generateBaseNSpace(2, test1);
        System.out.println("The original \n" + Arrays.toString(test1) + "And the squared: \n" +Arrays.toString
                (squared));

    }
}
