package org.hayabaya.loopers;

import org.hayabaya.datarelated.*;

public class LoopersDoubleBoxed extends Loopers {

    private Double[] array;
    private Double myNumber = rand.nextDouble();
    public static final Tpe type = Tpe.DOUBLE_BOXED;
    @Override
    public final Tpe getType() { return type; }

    public LoopersDoubleBoxed()  {
        super(type);
        initializeArrayElements(arrayLengths[0]);
        currentArrayLength = arrayLengths[0];
        currentCycleNumber = cycleNumbers[0];
    }

    @Override
    void operateLoop(Operation operation) {

        switch( operation ) {
            case ADD:
                for (int n = 0; n < currentCycleNumber; n++) for (int c = 0; c < currentArrayLength; c++) array[c] += myNumber;
            case SUBTRACT:
                for (int n = 0; n < currentCycleNumber; n++) for (int c = 0; c < currentArrayLength; c++) array[c] -= myNumber;
            case MULTIPLY:
                for (int n = 0; n < currentCycleNumber; n++) for (int c = 0; c < currentArrayLength; c++) array[c] *= myNumber;
            case DIVIDE:
                for (int n = 0; n < currentCycleNumber; n++) for (int c = 0; c < currentArrayLength; c++) array[c] /= myNumber;
        }
    }

    @Override
    protected void initializeArrayElements(int arrayLength) {
        array = new Double[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = new Double( rand.nextDouble() );

    }
}