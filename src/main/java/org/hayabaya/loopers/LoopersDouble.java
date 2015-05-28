package org.hayabaya.loopers;

import org.hayabaya.datarelated.*;

public class LoopersDouble extends Loopers {

    private double[] array;
    private double myNumber = rand.nextDouble();
    public static final Tpe type = Tpe.DOUBLE;

    @Override
    public final Tpe getType() { return type; }



    public LoopersDouble() {
        super(type);
        initializeArrayElements(arrayLengths[0]);
        currentArrayLength = arrayLengths[0];
        currentCycleNumber = cycleNumbers[0];
    }

    @Override
    public void performOperation(Operation operation) {
        System.out.println("Doing Double operation");
    }

    @Override
    protected void initializeArrayElements(int arrayLength) {
        array = new double[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = rand.nextDouble();

    }
}