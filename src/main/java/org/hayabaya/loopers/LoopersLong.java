package org.hayabaya.loopers;

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;

public class LoopersLong extends Loopers {

    private long[] array;
    private long myNumber = rand.nextLong();
    public static final Tpe type = Tpe.LONG;
    @Override
    public final Tpe getType() { return type; }

    public LoopersLong()  {
        super(type);
        initializeArrayElements(arrayLengths[0]);
        currentArrayLength = arrayLengths[0];
        currentCycleNumber = cycleNumbers[0];
    }

    @Override
    protected void initializeArrayElements(int arrayLength) {
        array = new long[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = rand.nextLong();

    }

    @Override
    public void performOperation(Operation operation) {
        System.out.println("Doing Long operation");
    }
}