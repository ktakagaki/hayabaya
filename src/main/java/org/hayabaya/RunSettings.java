package org.hayabaya;

/**
 * Created by cain on 4/20/2015.
 */
public class RunSettings {
    // NB: Note the hardcoded "1000" integer used for numberOfRowsSize and numberOfColumnsCycle, set the other variables only in
    // steps thousands
    //ToDo: Implement scaling factors so any stepsize can be used and calculation of numberOfX scales accordingly
    //ToDo 2: you might want to encapsulate all of the following in a class or a static thing

    public static boolean debug = false;
    public static boolean unitTesting = false;
    //ToDo: Make use of global static debug and unitTesting variables. So when doing UnitTest, max array length is much smaller and sensible myNumbers. And for Debug everything is printed out, and it can be controlled from one place.



    static final public int ARRAY_SIZE_MIN =    10; // thousand
    static final public int ARRAY_SIZE_MAX =  100; // hundred thousand
    static final public int ARRAY_SIZE_STEPS =  10;
    static final public int numberOfRowsSize = (int) Math.ceil( ((ARRAY_SIZE_MAX - ARRAY_SIZE_MIN) ) / ARRAY_SIZE_STEPS) + 1;
    static final public int CYCLES_MIN   = 1; // thousand
    static final public int CYCLES_MAX   = 25; // ten thousand
    static final public int CYCLES_STEPS = 5;
    static final public int TOTAL_EXP_REPS = 3;
    static final public int numberOfColumnsCycle = (int) Math.ceil( ((CYCLES_MAX - CYCLES_MIN) ) / CYCLES_STEPS) + 1;
}
