package org.hayabaya.loopers;

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;

public class LoopersIntegerBoxed extends Loopers {

    private Integer[] array;
    private Integer myNumber = rand.nextInt();
    public static final Tpe type = Tpe.INTEGER_BOXED;
    @Override
    public final Tpe getType() { return type; }

    public LoopersIntegerBoxed()  {
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
        array = new Integer[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = new Integer( rand.nextInt() );
    }
}