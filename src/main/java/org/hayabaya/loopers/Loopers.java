package org.hayabaya.loopers;

import org.hayabaya.datarelated.*;
import org.hayabaya.RunSettings;
import java.util.Random;

/**
 * Loopers tests the runtime doing: addition, subtraction, multiplication
 *    Loopers DOES NOT do anything, it is abstract.
 *    try to explain what the abstract classes are encapsulating (i.e. what they give to the child classes),
 *    and what will be overriden during inheritance
 * and division on the data types: int, float, double, long and (autoboxed) Integer, Float, Double and Long.
 * A Loopers object will be of a fixed data getType, and the object ensures that operations are performed correctly on
 * the data getType.
 * <p>
 *
 * @author cain
 * @author ktakagaki
 */
public abstract class Loopers {
    RunSettings runSettings = RunSettings.getInstance();
    Random rand = new Random();

    int[] arrayLength = runSettings.getArrayLengths();
    int[] cycleNumbers = runSettings.getCycleNumbers();
    protected Tpe type = null;

    /**
     * Override method in the type specific child class implementations.
     * @return Tpe the type of numerical representation that is tested.
     */
    public abstract Tpe getType();


    protected Loopers() { // made protected to avoid external initialization
    }

    /**
     * Loopers superclass constructor. This constructor is called from each child class to set the fields
     * arrayLength, cycles and type. The specific child class implements the abstract {@link #initializeArrayElements(int)} method
     * to do the actual initialization of the arrays.
     *
     * @param arrayLength The length of the array
     * @param cycles The number of times to perform the specific operation on the array
     * @param type The specific datatype of a given child class
     */
    public Loopers(Tpe type) {
        assert type != null : "A Type must be given, not null";
        this.type = type;
    } //end constructor


    /**
     * This is where the core computations are actually performed; by calling it from {@link performOperation
     * (Operation)} which measures the computation time, the child classes will deal with the data type specific
     * implementations of computing on the arrays.
     * @param operation The type of operation to perform (+,-,/,*) on the array for n cycles
     */
    abstract void operateLoop(Operation operation);

    /**
     * Initialize the correct primitive/boxed array with random values.
     * This is not done generically, in order to explicity
     * profile primitive operations.
     * @param arrayLength The length of the array
     */
    abstract protected void initializeArrayElements(int arrayLength);


    /**
     * Returns the total time in ms that it takes to perform an operation on an array.
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


    /**Runs [[performOperation]] for the given operation/type and bundles the results
     * into [[Results]] object.
     */
    public Results makeResults(Operation operation) {
        int rowIndex = 0; //index is just used for writing to data[][] object, not for actual for termination
        for (int lengthOfArray: arrayLength){ // Rows
            int columnIndex = 0; //index is just used for writing to data[][] object, not for actual for termination
            for (int numberOfCycles: cycleNumbers){ // columns
                long data[][] = new long[lengthOfArray][numberOfCycles];
                data[rowIndex][columnIndex] = performOperation(operation);
                return new Results(data, RunSettings.cycleNumbers, getType(), operation)
            }
        }

        /**           Number of Cycles
                  1k, 2k, 3k, 4k, 5k, 6k
        ArrayLen
        1.000    {{1,  2, 2,  2,  2,  3},
        2.000     {3,  3, 3,  5,  4,  5},
        3.000     {4,  5, 6,  6,  7,  8},
        4.000     {6,  8, 7,  8,  9,  10},
        5.000     {7, 11, 9, 10, 12,  13}}

         */
        long data[][] = new long[RunSettings.arrayLengths.length/*numberOfRowsArrayLength*/][RunSettings.cycleNumbers.length/*numberOfColumnsCycle*/];

        // #row loop#

        for (int rowCountArraySize: RunSettings.arrayLengths){
            // #column loop#

            for (int columnCountCycleNumbers : RunSettings.cycleNumbers) {
                setArrayLength(rowCountArraySize);
                setCycles(columnCountCycleNumbers);
                data[rowIndex][columnIndex] = performOperation(operation);

                columnIndex ++;
            }
            rowIndex ++;
        }

        //What is the reason for sending RunSettings.cycleNumbers within the results?
        //Why not just send the whole RunSettings object?
        //   (or, instead of sending, you can assume the same "RunSettings" for the whole object)
        //What is so special about the "cycleNumbers" that it has to be extracted?
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