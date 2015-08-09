package org.hayabaya.loopers;

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;


public class LoopersInt extends Loopers {

    public static final Tpe type = Tpe.INT;
    private int[] array;
    private int[] myNumberArray;



    public LoopersInt() {
        super(type);
        initializeArrayElements(arrayLengths[0]);
        currentArrayLength = arrayLengths[0];
        currentCycleNumber = cycleNumbers[0];
        calculateArraySums();
    }


    /*
    This block of code is for creating a long array that will be filled with random numbers. For each time
    an operation is to be performed for the benchmarking, the code will use a new random number from this
    array. This should prevent the compiler and the Java Virtual Machine from performing any kind of
    optimization
     */
    protected int arraySum;
    protected int cycleSum;
    protected int totalSums;

    public void calculateArraySums(){
        int aLengths = arrayLengths.length;
        int bLength = cycleNumbers.length;
        arraySum = 0;
        cycleSum = 0;


        for(int i = 0; i < aLengths; i++){
            arraySum += arrayLengths[i];
        }

        for(int i = 0; i < bLength; i++){
            cycleSum += cycleNumbers[i];
        }

        totalSums = arraySum + cycleSum;
        myNumberArray = new int[totalSums];
        for (int i = 0; i < totalSums; i++) myNumberArray[i] = (int) rand.nextInt();
    }


    @Override
    public final Tpe getType() { return type; }


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
                throw new IllegalArgumentException("Invalid operation reached in LoopersInt");
        }
    }


    @Override
    protected void initializeArrayElements(int arrayLength) {
        array = new int[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = rand.nextInt();
    }

}