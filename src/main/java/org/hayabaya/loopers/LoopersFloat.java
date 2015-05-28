package org.hayabaya.loopers;

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;

public class LoopersFloat extends Loopers {

    private float[] array;
    private float myNumber = rand.nextFloat();
    public static final Tpe type = Tpe.FLOAT;
    @Override
    public final Tpe getType() { return type; }

    public LoopersFloat()  {
        super(type);
        initializeArrayElements(arrayLengths[0]);
        currentArrayLength = arrayLengths[0];
        currentCycleNumber = cycleNumbers[0];
    }

    @Override
    protected void initializeArrayElements(int arrayLength) {
        array = new float[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = rand.nextFloat();

    }

    @Override
    public void performOperation(Operation operation) {
        System.out.println("Doing Float operation");
    }

}