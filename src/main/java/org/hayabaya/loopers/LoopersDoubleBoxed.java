package org.hayabaya.loopers;

import ch.qos.logback.classic.Logger;
import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;
import org.slf4j.LoggerFactory;

/**
 * Specific implementation of the Loopers class for the data type Boxed Double
 */
public class LoopersDoubleBoxed extends Loopers {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(LoopersDoubleBoxed.class);

    public static final Tpe type = Tpe.DOUBLE_BOXED;
    private Double[] array;
    // To avoid optimization an array with pre-initialized numbers is generated, it must have enough elements such
    // that no number is reused in the entire project.
    private Double[] myNumberArray;

    /**
     * Primary constructor called by the {@link org.hayabaya.loopers.LooperFactory} class. Values for array lengths,
     * cyclenumbers and total number of replicates are read in {@link org.hayabaya.RunSettings}
     */
    public LoopersDoubleBoxed()  {
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
     * It is possible for the compiler to optimize the computations on an array if the same number is used repeatedly in
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


        for(int i = 0; i < aLengths; i++){
            arraySum += arrayLengths[i];
        }

        for(int i = 0; i < bLength; i++){
            cycleSum += cycleNumbers[i];
        }

        totalSums = arraySum + cycleSum;
        myNumberArray = new Double[totalSums];
        for (int i = 0; i < totalSums; i++) myNumberArray[i] = (Double) rand.nextDouble();
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
                for (int n = 0; n < currentCycleNumber; n++){
                    for (int c = 0; c < currentArrayLength; c++){
                        Double a = array[c];
                        Double b = myNumberArray[n + c];
                        array[c] = (a + b);
                    }
                }
                break;
            case SUBTRACT:
                for (int n = 0; n < currentCycleNumber; n++){
                    for (int c = 0; c < currentArrayLength; c++){
                        Double a = array[c];
                        Double b = myNumberArray[n + c];
                        array[c] = (a - b);
                    }
                }
                break;
            case MULTIPLY:
                for (int n = 0; n < currentCycleNumber; n++){
                    for (int c = 0; c < currentArrayLength; c++){
                        Double a = array[c];
                        Double b = myNumberArray[n + c];
                        array[c] = (a * b);
                    }
                }
                break;
            case DIVIDE:
                for (int n = 0; n < currentCycleNumber; n++){
                    for (int c = 0; c < currentArrayLength; c++){
                        Double a = array[c];
                        Double b = myNumberArray[n + c];
                        array[c] = (a / b);
                    }
                }
                break;
            case LESSTHAN:
                for (int n = 0; n < currentCycleNumber; n++){
                    for (int c = 0; c < currentArrayLength; c++){
                        Double a = array[c];
                        Double b = myNumberArray[n + c];
                        if (a < b) array[c] = myNumberArray[n+c];
                        else array[c] = b;
                    }
                }
                break;
            case GREATERTHAN:
                for (int n = 0; n < currentCycleNumber; n++){
                    for (int c = 0; c < currentArrayLength; c++){
                        Double a = array[c];
                        Double b = myNumberArray[n + c];
                        if (a > b) array[c] = myNumberArray[n+c];
                        else array[c] = b;
                    }
                }
                break;
            case EQUALS:
                for (int n = 0; n < currentCycleNumber; n++){
                    for (int c = 0; c < currentArrayLength; c++){
                        Double a = array[c];
                        Double b = myNumberArray[n + c];
                        if (a.equals(b)) array[c] = myNumberArray[n+c];
                        else array[c] = b;
                    }
                }
                break;
            case NOTEQUAL:
                for (int n = 0; n < currentCycleNumber; n++){
                    for (int c = 0; c < currentArrayLength; c++){
                        Double a = array[c];
                        Double b = myNumberArray[n + c];
                        if (!a.equals(b)) array[c] = myNumberArray[n+c];
                        else array[c] = b;
                    }
                }
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
        array = new Double[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = new Double( rand.nextDouble() );

    }
}