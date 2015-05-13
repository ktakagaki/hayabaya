package org.hayabaya;

/**This static class encapsulates the settings for experimental runs
 * which are common for all types (Int/Float/Double, etc.)
 *
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


    //ToDo: rewrite these to have a fixed list (like cycleNumbers below)
    //That would allow you to encapsulate that code within the RunSettings class
    //and would also let you do quick-and-dirty testing by setting the variable here:
    // arrayLengths = new int[]{10, 20, 30};
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
    //ToDo 2: make this sort of logarithmic (either hard-coded by hand, or with function)
//                new int[RunSettings.numberOfColumnsCycle];
//        for (int columnCountCycleNumbers = RunSettings.CYCLES_MIN, columnIndex = 0;
//            columnCountCycleNumbers <= RunSettings.CYCLES_MAX;
//            columnCountCycleNumbers += RunSettings.CYCLES_STEPS, columnIndex++) {
//            cycleNumbers[columnIndex] = columnCountCycleNumbers;
//        }

}
