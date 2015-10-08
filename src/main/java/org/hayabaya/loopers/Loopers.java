package org.hayabaya.loopers;

import org.hayabaya.RunSettings;
import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Results;
import org.hayabaya.datarelated.ResultsCollection;
import org.hayabaya.datarelated.Tpe;

import java.util.Random;

/**
 * Abstract class encapsulating the parts of JVM profiling that does not depend on the specific datatype. Because
 * generics and other advanced features are not available for primitive data types such as int, long, float and
 * double, it is necessary to use a lot of child classes that handles each data type specificially.
 *
 * @author cain
 * @author ktakagaki
 */
public abstract class Loopers {

    protected Tpe type = null; // To be initialized when instantiating a subclass
    RunSettings runSettings = RunSettings.getRunSettingsInstance();
    Random rand = new Random();
    int[] arrayLengths = runSettings.getArrayLengths(); // What size of arrays to be tested, e.g. [1, 1000, 10000]
    int[] cycleNumbers = runSettings.getCycleNumbers(); // What number of times each operation is to be repeated onto
    // each element of each of the arrays, e.g. [10, 2000, 4000]
    int currentArrayLength;
    int currentCycleNumber;


    protected Loopers() { // made protected to avoid external initialization
    }


    /**
     * Primary constructor of the parent class, called from a child class when they are being instantiated.
     * The specific child class implements the abstract {@link #initializeArrayElements(int)} method
     * to do the actual initialization of the arrays.
     *
     * @param type The specific datatype of a given child class
     */
    public Loopers(Tpe type) {
        assert type != null : "A Type must be given, not null";
        this.type = type;
    }

    /**
     * Override method in the data type specific sub-class implementations.
     *
     * @return Tpe the type of numerical representation that is tested.
     */
    public abstract Tpe getType();

    /**
     * Uses a switch statement to determine the type of operation given as the argument and then calls the appropriate
     * loop to measure the runtimes for all of the arraylengths and cyclenumbers.
     * @param operation The type of operation to perform (+,-,/,*) on the array for n cycles
     */
    abstract void operateLoop(Operation operation);

    /**
     * Initialize the array with random values.
     * This is not done generically, in order to explicity
     * profile primitive operations.
     * @param arrayLength The length of the array
     */
    abstract protected void initializeArrayElements(int arrayLength);


    /**
     * For each length of the arrays, CycleNumbers and type of operation call {@link #performOperation(Operation)}
     * which starts the JVM timer and then calls {@link #operateLoop(Operation)}.
     * @return A result datastructure
     */
    public void makeResults(){
        ResultsCollection resultsCollection = ResultsCollection.getInstance();
        //ToDo: Verify that results are initialized correctly in the loops


        int totalRepetitions = runSettings.getTotalExperimentRepetitions();

        for (int currentRepetition = 0; currentRepetition < totalRepetitions; currentRepetition++) {

            for (Operation anOperationToUse : Operation.values()) {

//                Results result;
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

                Results results = new Results(data, currentRepetition, getType(), anOperationToUse);
                resultsCollection.addToResultsList(results);
//                WriteResults.writeResultsV2(result);
            }
        }
    }

    /**
     * This is where the core computations are actually performed; by calling it from performOperation
     * which measures the computation time, the child classes will deal with the data type specific
     * implementations of computing on the arrays.
     *
     * @param operation The type of operation to perform (+,-,/,*) on the array for n cycles
     * @return The time it took to perform the operation in milliseconds
     */
    protected long performOperation(Operation operation){

        long startTime = System.currentTimeMillis();
        operateLoop(operation);
        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    final public int getArrayLength() {
        return currentArrayLength;
    }

    /**
     * Set the length of an array in a Loopers object to a new length. This method is used for testing the different
     * arraylengths specified in {@link org.hayabaya.RunSettings}. The method uses
     * {@link org.hayabaya.loopers.Loopers#initializeArrayElements(int)}  of the specific child classes to re-initialize a new array.
     * @param arrayLength The length of the new array to be initialized
     */
    final public void setArrayLength(int arrayLength) {
        assert arrayLength > 0 : "array length must be above zero";
        this.currentArrayLength = arrayLength;
        initializeArrayElements(arrayLength);
    }

    final public int getCurrentCycleNumber() {
        return currentCycleNumber;
    }

    /**
     * Sets the currentCycleNumber to a new value.
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
