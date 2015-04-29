package org.hayabaya;

/**
 * Created by cain on 4/20/2015.
 */
public class RunSettings {
    //ToDo: Implement scaling factors so any stepsize can be used and calculation of numberOfX scales accordingly

    /** Global debug variable, controls all debug printing messages etc */
    public static boolean debug = false;
    /** Initialize with predictable numbers like hardcoded myNumber in the Loopers, making it possible to figure out
     * what values should be expected when doing unit test. Also uses smaller arraylength, cycles and step size so it
     * all runs faster
     */
    public static boolean unitTesting = true;


    static final public int ARRAY_SIZE_MIN =    10; // thousand
    static final public int ARRAY_SIZE_MAX =  1000; // hundred thousand
    static final public int ARRAY_SIZE_STEPS =  10;
    static final public int numberOfRowsArrayLength = (int) Math.ceil( ((ARRAY_SIZE_MAX - ARRAY_SIZE_MIN) ) / ARRAY_SIZE_STEPS) + 1;


    static final public int CYCLES_MIN   = 1; // thousand
    static final public int CYCLES_MAX   = 45; // ten thousand
    static final public int CYCLES_STEPS = 5;
    static final public int TOTAL_EXP_REPS = 3;
    //static final public int numberOfColumnsCycle = (int) Math.ceil( ((CYCLES_MAX - CYCLES_MIN) ) / CYCLES_STEPS) + 1;
    static final public int[] cycleNumbers = new int[]{100, 1000};
//                new int[RunSettings.numberOfColumnsCycle];
//        //ToDo 2: make this sort of logarithmic
//        for (int columnCountCycleNumbers = RunSettings.CYCLES_MIN, columnIndex = 0;
//            columnCountCycleNumbers <= RunSettings.CYCLES_MAX;
//            columnCountCycleNumbers += RunSettings.CYCLES_STEPS, columnIndex++) {
//            cycleNumbers[columnIndex] = columnCountCycleNumbers;
//        }

}
