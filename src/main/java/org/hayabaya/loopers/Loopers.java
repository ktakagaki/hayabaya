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

    int[] arrayLengths = runSettings.getArrayLengths();
    int[] cycleNumbers = runSettings.getCycleNumbers();
    int currentArrayLength;
    int currentCycleNumber;
    int currentRepetitionNumber = 0;
    protected Tpe type = null;

    /**
     * Override method in the type specific child class implementations.
     *
     * @return Tpe the type of numerical representation that is tested.
     */
    public abstract Tpe getType();


    protected Loopers() { // made protected to avoid external initialization
    }

    /**
     * Loopers superclass constructor. This constructor is called from each child class to set the fields
     * arrayLengths, cycles and type. The specific child class implements the abstract {@link #initializeArrayElements(int)} method
     * to do the actual initialization of the arrays.
     *
     * @param type The specific datatype of a given child class
     */
    public Loopers(Tpe type) {
        assert type != null : "A Type must be given, not null";
        this.type = type;
    } //end constructor


    /**
     * Initialize the correct primitive/boxed array with random values.
     * This is not done generically, in order to explicity
     * profile primitive operations.
     *
     * @param arrayLength The length of the array
     */
    abstract protected void initializeArrayElements(int arrayLength);


    /**
     * This is where the core computations are actually performed; by calling it from performOperation
     * which measures the computation time, the child classes will deal with the data type specific
     * implementations of computing on the arrays.
     *
     * @param operation The type of operation to perform (+,-,/,*) on the array for n cycles
     */
    abstract protected void performOperation(Operation operation);


    /**
     * Runs [[performOperation]] for the given operation/type and bundles the results
     * into [[Results]] object.
     */
    abstract public void makeResults();

//
//        int rowIndex = 0; //index is just used for writing to data[][] object, not for actual for termination
//        for (int lengthOfArray: arrayLengths){ // Rows
//            int columnIndex = 0; //index is just used for writing to data[][] object, not for actual for termination
//            for (int numberOfCycles: cycleNumbers){ // columns
//                long data[][] = new long[lengthOfArray][numberOfCycles];
//                data[rowIndex][columnIndex] = performOperation(operation);
//                return new Results(data, RunSettings.cycleNumbers, getType(), operation)
//            }
//        }
//
//        /**           Number of Cycles
//                  1k, 2k, 3k, 4k, 5k, 6k
//        ArrayLen
//        1.000    {{1,  2, 2,  2,  2,  3},
//        2.000     {3,  3, 3,  5,  4,  5},
//        3.000     {4,  5, 6,  6,  7,  8},
//        4.000     {6,  8, 7,  8,  9,  10},
//        5.000     {7, 11, 9, 10, 12,  13}}
//
//         */
//        long data[][] = new long[RunSettings.arrayLengths.length/*numberOfRowsArrayLength*/][RunSettings.cycleNumbers.length/*numberOfColumnsCycle*/];
//
//        // #row loop#
//
//        for (int rowCountArraySize: RunSettings.arrayLengths){
//            // #column loop#
//
//            for (int columnCountCycleNumbers : RunSettings.cycleNumbers) {
//                setArrayLength(rowCountArraySize);
//                setCycles(columnCountCycleNumbers);
//                data[rowIndex][columnIndex] = performOperation(operation);
//
//                columnIndex ++;
//            }
//            rowIndex ++;
//        }
//
//        //What is the reason for sending RunSettings.cycleNumbers within the results?
//        //Why not just send the whole RunSettings object?
//        //   (or, instead of sending, you can assume the same "RunSettings" for the whole object)
//        //What is so special about the "cycleNumbers" that it has to be extracted?
//        return new Results(data, RunSettings.cycleNumbers, getType(), operation);

    @Override
    public String toString(){
        return type.toString();
    }

}
