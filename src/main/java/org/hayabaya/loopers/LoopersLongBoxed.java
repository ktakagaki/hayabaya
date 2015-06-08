package org.hayabaya.loopers;

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;

public class LoopersLongBoxed extends Loopers {

    private Long[] array;
    private Long myNumber = rand.nextLong();
    public static final Tpe type = Tpe.LONG_BOXED;

    @Override
    public final Tpe getType() {
        return type;
    }

    public LoopersLongBoxed() {
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
        array = new Long[arrayLength];
        for (int c = 0; c < array.length; c++) array[c] = new Long(rand.nextLong());
    }
}