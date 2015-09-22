package org.hayabaya.loopers;

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;

/**
 * Specific implementation of the Loopers class for the data type double
 */
public class LoopersDouble extends Loopers {

    public static final Tpe type = Tpe.DOUBLE;
    private double[] array; // The array to be operated on
    private double[] myNumberArray; // Array to pre-initialize with random numbers for operating on the above arra in
    // order to avoid an overhead of "generating" the numbers on the fly, or the compiler optimizing the operations
    // by always using the same number again and again for the operation

    /**
     * Primary constructor called by the {@link org.hayabaya.loopers.LooperFactory} class. Values for array lengths,
     * cyclenumbers and total number of replicates are read in {@link org.hayabaya.RunSettings}
     */
    public LoopersDouble() {
        super(type);
        initializeArrayElements(arrayLengths[0]);
        currentArrayLength = arrayLengths[0];
        currentCycleNumber = cycleNumbers[0];
        calculateArraySums();
    }


    protected int arraySum;
    protected int cycleSum;
    protected int totalSums;

    /**
     *     It is possible for the compiler to optimize the computations on an array if the same number is used repeatedly in
     an operation. To avoid this we use a new number for every operation. Generating this number would cause a
     significant overhead not related to timing the operation itself. So a pre-initialized array with random numbers
     is generated for this purpose (myNumberArray). This function calculates the total number of elements needed in
     such an array given the total number of operations to be performed when considering all of the array lengths,
     cyclenumbers and repetitions to be tested for each of the type of operation.
     */
    public void calculateArraySums(){
        int aLengths = arrayLengths.length;
        int bLength = cycleNumbers.length;
        arraySum = 0;
        cycleSum = 0;

        // Calculate the total sum of elements in all of the arrays
        for(int i = 0; i < aLengths; i++){
            arraySum += arrayLengths[i];
        }

        // Calculate the total sum of cycleNumbers in each of the cycleNumbers
        for(int i = 0; i < bLength; i++){
            cycleSum += cycleNumbers[i];
        }

        totalSums = arraySum + cycleSum;
        myNumberArray = new double[totalSums];
        for (int i = 0; i < totalSums; i++) myNumberArray[i] = (double) rand.nextDouble();
    }

    @Override
    public final Tpe getType() {
        return type;
    }

    /**
     * Specific implementation of {@link org.hayabaya.loopers.Loopers#operateLoop(Operation)} for the data type double
     * @param operation The type of operation to perform (+,-,/,*) on the array for n cycles
     */
    @Override
    void operateLoop(Operation operation) {

        switch( operation ) {
            case ADD:
                for (int n = 0; n < currentCycleNumber; n++) for (int c = 0; c < currentArrayLength; c++) array[c] += myNumberArray[n+c];
                break;
            case SUBTRACT:
                for (int n = 0; n < currentCycleNumber; n++) for (int c = 0; c < currentArrayLength; c++) array[c] -= myNumberArray[n+c];
                break;
            case MULTIPLY:
                for (int n = 0; n < currentCycleNumber; n++) for (int c = 0; c < currentArrayLength; c++) array[c] *= myNumberArray[n+c];
                break;
            case DIVIDE:
                for (int n = 0; n < currentCycleNumber; n++) for (int c = 0; c < currentArrayLength; c++) array[c] /= myNumberArray[n+c];
                break;
            default:
                throw new IllegalArgumentException("Invalid operation reached in LoopersDouble");
        }
    }

    /**
     * Specific implementation of {@link org.hayabaya.loopers.Loopers#initializeArrayElements(int)} for the data type double
     * @param arrayLength The length of the array
     */
    @Override
    protected void initializeArrayElements(int arrayLength) {
        array = new double[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = rand.nextDouble();

    }
}