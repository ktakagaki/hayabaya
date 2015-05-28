package org.hayabaya.loopers;

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;

public class LoopersLongBoxed extends Loopers{

    private Long[] array;
    private Long myNumber = rand.nextLong();
    public static final Tpe type = Tpe.LONG_BOXED;
    @Override
    public final Tpe getType() { return type; }

    public LoopersLongBoxed() {
        super(type);
        initializeArrayElements(arrayLengths[0]);
        currentArrayLength = arrayLengths[0];
        currentCycleNumber = cycleNumbers[0];
    }

    @Override
    protected void initializeArrayElements(int arrayLength) {
        array = new Long[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = new Long( rand.nextLong() );
    }

    @Override
    public void performOperation(Operation operation) {
        System.out.println("Doing Int operation");
    }
}
