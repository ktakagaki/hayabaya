package org.hayabaya;

/**
 * Created by cain on 4/20/2015.
 */
public class RunSettings {
    // NB: Note the hardcoded "1000" integer used for numberOfRowsSize and numberOfColumnsCycle, set the other variables only in
    // steps thousands
    //ToDo: Implement scaling factors so any stepsize can be used and calculation of numberOfX scales accordingly
    //ToDo 2: you might want to encapsulate all of the following in a class or a static thing

    /** When set to true, all debug printing messages in the project will be activated */
    public static boolean debug = false;
    /** When True, the myNumber variable in all of the looper classes will use a fixed number so the expected output
     * can be calculated and tested against
     */
    public static boolean unitTesting = true;




    static final public int ARRAY_SIZE_MIN =    10; // thousand
    static final public int ARRAY_SIZE_MAX =  1000; // hundred thousand
    static final public int ARRAY_SIZE_STEPS =  10;
    static final public int numberOfRowsSize = (int) Math.ceil( ((ARRAY_SIZE_MAX - ARRAY_SIZE_MIN) ) / ARRAY_SIZE_STEPS) + 1;
    static final public int CYCLES_MIN   = 1; // thousand
    static final public int CYCLES_MAX   = 45; // ten thousand
    static final public int CYCLES_STEPS = 5;
    static final public int TOTAL_EXP_REPS = 3;
    static final public int numberOfColumnsCycle = (int) Math.ceil( ((CYCLES_MAX - CYCLES_MIN) ) / CYCLES_STEPS) + 1;
}
