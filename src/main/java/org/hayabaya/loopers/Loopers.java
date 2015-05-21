package org.hayabaya.loopers;

import org.hayabaya.datarelated.*;
import org.hayabaya.RunSettings;
import java.util.Random;

/**
 * Loopers tests the runtime doing: addition, subtraction, multiplication
 * and division on the data types: int, float, double, long and (autoboxed) Integer, Float, Double and Long.
 * A Loopers object will be of a fixed data getType, and the object ensures that operations are performed correctly on
 * the data getType.
 * <p>
 *
 * @author cain
 * @author ktakagaki
 */
public abstract class Loopers {

    int arrayLength = 0;
    int cycles = 0;
    protected Tpe type = null;

    /**
     * JavaBean style encapsulation of the child classes Tpe type field. Used when creating the results and in the
     * toString method
     * @return Tpe type, the enum type of the child class
     */
    public abstract Tpe getType();

    Operation lastSetOperation = null;
    Random rand = new Random();

    protected Loopers() {
    }

    /**
     * Loopers superclass constructor. This constructor is called from each child class to set the fields
     * arrayLength, cycles and type. The specific child class implements the abstract {@link #initArray(int)} method
     * to do the actual initialization of the arrays.
     * @param arrayLength The length of the array
     * @param cycles The number of times to perform the specific operation on the array
     * @param type The specific datatype of a given child class
     */
    public Loopers(int arrayLength, int cycles, Tpe type) {

        assert arrayLength > 0 : "array length must be > 0";
        assert cycles > 0 : "repetitions must be > 0";
        assert type != null : "A Type must be given, not null";

        this.arrayLength = arrayLength;
        this.cycles = cycles;
        this.type = type;

        initArray(arrayLength);

    } //end constructor


    /**
     * Used to print out what type of operation was performed when HayaBaya is running in debug mode.
     * lastSetOperation is printed out in the Looper child classes toString methods.
     * @param operation
     */
    public void setLastSetOperation(Operation operation) {
        this.lastSetOperation = operation;
    }

    /**
     * This is the meat of the HayaBaya project. operateLoop in the child classes performs the actual computations on
     * the arrays.
     * @param operation The type of operation to perform (+,-,/,*) on the array for n cycles
     */
    abstract void operateLoop(Operation operation);

    /**
     * Initialize the correct primitive/boxed array with random values.
     * This is not done generically, in order to explicity
     * profile primitive operations.
     * @param arrayLength The length of the array
     */
    abstract protected void initArray(int arrayLength);

    /**
     * Reset the arraylength of a Loopers object to a new length. This makes it possible for one object to profile
     * arrays of multiple lengths without creating new object instances. The method uses
     * {@link #initArray(int)}  of the specific child classes to re-initialize a new array.
     * @param arrayLength The length of the new array to be initialized
     */
    final public void setArrayLength(int arrayLength) {
        assert arrayLength > 0 : "array length must be above zero";
        this.arrayLength = arrayLength;
        initArray(arrayLength);
    }

    final public int getArrayLength() {
        return arrayLength;
    }

    final public int getCycles() {
        return cycles;
    }

    /**
     * Used to increment the number of cycles such that an array can be tested with different number of cycles
     * @param cycles The new number of cycles to run on an array
     */
    final public void setCycles(int cycles) {
        assert cycles > 0 : "repetitions must be above zero";
        this.cycles = cycles;
    }

    /**
     * Records the total time in ms that it takes to perform an operation on an array.
     * @param operation The type of operation
     * @return the time it took to perform the given operation
     */
    public long performOperation(Operation operation) {
        //ToDo initRandom();

        long startTime = System.currentTimeMillis();
        operateLoop(operation);
        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }


    /**
     * Runs [[performOperation]] for the given operation
     *
     * @param operation
     * @return Results object, Results(long[][], int cycleNumbers used, Data type, Operation type)
     */
    public Results makeResults(Operation operation) {

        /*           Number of Cycles
                  1k, 2k, 3k, 4k, 5k, 6k
        ArrayLen
        1.000    [1,  2, 2,  2,  2,  3]
        2.000    [3,  3, 3,  5,  4,  5]
        3.000    [4,  5, 6,  6,  7,  8]
        4.000    [6,  8, 7,  8,  9,  10]
        5.000    [7, 11, 9, 10, 12,  13]

         */
        long data[][] = new long[RunSettings.numberOfRowsArrayLength][RunSettings.numberOfColumnsCycle];

        // row loop
        for (int rowCountArraySize = RunSettings.ARRAY_SIZE_MIN, rowIndex = 0;
             rowCountArraySize <= RunSettings.ARRAY_SIZE_MAX;
             rowCountArraySize += RunSettings.ARRAY_SIZE_STEPS, rowIndex++) {

            // column loop
//            int columnIndex = 0;
//            for (int columnCountCycleNumbers : RunSettings.cycleNumbers) {
            for (int columnCountCycleNumbers = RunSettings.CYCLES_MIN, columnIndex = 0;
                 columnCountCycleNumbers <= RunSettings.CYCLES_MAX;
                 columnCountCycleNumbers += RunSettings.CYCLES_STEPS, columnIndex++) {

                setArrayLength(rowCountArraySize);
                setCycles(columnCountCycleNumbers);

                data[rowIndex][columnIndex] = performOperation(operation);
            }
        }
        return new Results(data, RunSettings.cycleNumbers, getType(), operation);
    }

    // <editor-fold defaultstate="collapsed" desc=" toString ">
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        // Title of output
        result.append(Utility.ANSI_YELLOW + "\033[1m Results for " + this.getClass().getSimpleName()
                + "\033[0m" + Utility.ANSI_RESET + "\n");
        // Name of Object
        result.append(Utility.ANSI_BLUE + "{Object:            "
                + Utility.ANSI_RESET + this.getClass().getSimpleName() + "}" + NEW_LINE);
        // Arraylength
        result.append(Utility.ANSI_GREEN + "{ArrayLength:       "
                + Utility.ANSI_RESET + arrayLength + "}" + NEW_LINE);
        // Cycles
        result.append(Utility.ANSI_CYAN + "{Cycles:            " + Utility.ANSI_RESET +
                cycles + "}" + NEW_LINE);
        // The getType (Int, Integer etc.)
        result.append(Utility.ANSI_PURPLE + "{Type:              " + Utility.ANSI_RESET +
                getType() + "}" + NEW_LINE);

        return result.toString();
    }
    // </editor-fold>
}