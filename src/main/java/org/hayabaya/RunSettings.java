package org.hayabaya;

/**This static class encapsulates the settings for experimental runs
 * which are common for all types (Int/Float/Double, etc.)
 *
 * Created by cain on 4/20/2015.
 */
public class RunSettings {
    //ToDo: Implement scaling factors so any stepsize can be used and calculation of numberOfX scales accordingly

    /**
     * Global debug variable, controls all debug printing messages etc
     */
    public static boolean debug = false;
    /**
     * Initialize with predictable numbers like hardcoded myNumber in the Loopers, making it possible to figure out
     * what values should be expected when doing unit test. Also uses smaller arraylength, cycles and step size so it
     * all runs faster
     */
    public static boolean unitTesting = false;


    //ToDo: rewrite these to have a fixed list (like cycleNumbers below)
    //That would allow you to encapsulate that code within the RunSettings class
    //and would also let you do quick-and-dirty testing by setting the variable here:
    public static final int[] arrayLengths = new int[]{10, 20, 30};

    @Deprecated
    static final public int ARRAY_SIZE_MIN = 10; // thousand
    @Deprecated
    static final public int ARRAY_SIZE_MAX = 1000; // hundred thousand
    @Deprecated
    static final public int ARRAY_SIZE_STEPS = 10;
    @Deprecated
    static final public int numberOfRowsArrayLength = (int) Math.ceil(((ARRAY_SIZE_MAX - ARRAY_SIZE_MIN)) / ARRAY_SIZE_STEPS) + 1;


    @Deprecated
    static final public int CYCLES_MIN = 1; // thousand
    @Deprecated
    static final public int CYCLES_MAX = 45; // ten thousand
    @Deprecated
    static final public int CYCLES_STEPS = 5;

    /**The benefit of setting up cycle numbers this way, is that you can do quick and dirty testing of
     * specific values ```int[] cycleNumbers = new int[]{100, 1000};```
     * as well as more complex construction with for loops etc.
     */
    static final public int[] cycleNumbers = new int[]{100, 1000};
    //static final public int[] cycleNumbers = new int[5];
    //for( int i = 0; i<5; i++) {
    //    cycleNumbers[i] = xxxxxxx
    //}

    //ToDo 2: make this sort of logarithmic (either hard-coded by hand, or with function)
//                new int[RunSettings.numberOfColumnsCycle];
//        for (int columnCountCycleNumbers = RunSettings.CYCLES_MIN, columnIndex = 0;
//            columnCountCycleNumbers <= RunSettings.CYCLES_MAX;
//            columnCountCycleNumbers += RunSettings.CYCLES_STEPS, columnIndex++) {
//            cycleNumbers[columnIndex] = columnCountCycleNumbers;
//        }

    static final public int TOTAL_EXP_REPS = 3;

}
