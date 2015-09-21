package org.hayabaya.loopers;

import org.hayabaya.datarelated.*;
import org.hayabaya.RunSettings;
import org.slf4j.Logger;

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
    private static String TAG;
    private static Class<?> cls;
    private static Logger logger;


    RunSettings runSettings = RunSettings.getRunSettings();
    Random rand = new Random();

    int[] arrayLengths = runSettings.getArrayLengths();
    int[] cycleNumbers = runSettings.getCycleNumbers();
    int currentArrayLength;
    int currentCycleNumber;
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
    abstract protected void initializeArrayElements(int arrayLength);


    /**
     * Runs [[performOperation]] for the given operation/type and bundles the results
     * into [[Results]] object.
     */
    public void makeResults(){

        int totalRepetitions = runSettings.getTotalExperimentRepetitions();

        for (int currentRepetition = 0; currentRepetition < totalRepetitions; currentRepetition++) {

            for (Operation anOperationToUse : Operation.values()) {

                Results result;
                long data[][] = new long[runSettings.getArrayLengths().length][runSettings.getCycleNumbers().length];

                // #row loop#

                int rowIndex = 0;
                for (int rowCountArraySize: runSettings.getArrayLengths()){
                    // #column loop#
                    int columnIndex = 0;

                    for (int columnCountCycleNumbers : runSettings.getCycleNumbers()) {
                        setArrayLength(rowCountArraySize);
                        setCycles(columnCountCycleNumbers);
                        data[rowIndex][columnIndex] = performOperation(anOperationToUse);

                        columnIndex ++;
                    }
                    rowIndex ++;
                }

                result = new Results(data, currentRepetition, getType(), anOperationToUse);
//                Utility.writeResultsToCsv(result);
//                Utility.writeResultsV2(result);
            }
        }
    }

    /**
     * This is where the core computations are actually performed; by calling it from performOperation
     * which measures the computation time, the child classes will deal with the data type specific
     * implementations of computing on the arrays.
     *
     * @param operation The type of operation to perform (+,-,/,*) on the array for n cycles
     * @return runTime Time it took to perform the operation in ms
     */
    protected long performOperation(Operation operation){
        //ToDo initRandom();

        long startTime = System.currentTimeMillis();
        operateLoop(operation);
        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    /**
     * Reset the arraylength of a Loopers object to a new length. This makes it possible for one object to profile
     * arrays of multiple lengths without creating new object instances. The method uses
     * {@link org.hayabaya.loopers.Loopers#initializeArrayElements(int)}  of the specific child classes to re-initialize a new array.
     * @param arrayLength The length of the new array to be initialized
     */
    final public void setArrayLength(int arrayLength) {
        assert arrayLength > 0 : "array length must be above zero";
        this.currentArrayLength = arrayLength;
        initializeArrayElements(arrayLength);
    }

    final public int getArrayLength() {
        return currentArrayLength;
    }

    final public int getCycles() {
        return currentCycleNumber;
    }

    /**
     * Used to increment the number of cycles such that an array can be tested with different number of cycles
     * @param cycles The new number of cycles to run on an array
     */
    final public void setCycles(int cycles) {
        assert cycles > 0 : "repetitions must be above zero";
        this.currentCycleNumber = cycles;
    }

    @Override
    public String toString(){
        return type.toString();
    }

}
