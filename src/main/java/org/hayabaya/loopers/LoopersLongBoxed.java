package org.hayabaya.loopers;

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;

public class LoopersLongBoxed extends Loopers {

    public static final Tpe type = Tpe.LONG_BOXED;
    private Long[] array;
    private Long myNumber = rand.nextLong();

    public LoopersLongBoxed() {
        super(type);
        initializeArrayElements(arrayLengths[0]);
        currentArrayLength = arrayLengths[0];
        currentCycleNumber = cycleNumbers[0];
    }

    @Override
    public final Tpe getType() {
        return type;
    }

    @Override
    void operateLoop(Operation operation) {

        switch( operation ) {
            case ADD:
                for (int n = 0; n < currentCycleNumber; n++) for (int c = 0; c < currentArrayLength; c++) array[c] += myNumber;
                break;
            case SUBTRACT:
                for (int n = 0; n < currentCycleNumber; n++) for (int c = 0; c < currentArrayLength; c++) array[c] -= myNumber;
                break;
            case MULTIPLY:
                for (int n = 0; n < currentCycleNumber; n++) for (int c = 0; c < currentArrayLength; c++) array[c] *= myNumber;
                break;
            case DIVIDE:
                for (int n = 0; n < currentCycleNumber; n++) for (int c = 0; c < currentArrayLength; c++) array[c] /= myNumber;
                break;
            default:
                throw new IllegalArgumentException("Invalid operation reached in LoopersLongBoxed");
        }
    }

    @Override
    protected void initializeArrayElements(int arrayLength) {
        array = new Long[arrayLength];
        for (int c = 0; c < array.length; c++) array[c] = new Long(rand.nextLong());
    }
}