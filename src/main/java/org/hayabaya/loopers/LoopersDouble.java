package org.hayabaya.loopers;

import org.hayabaya.RunSettings;
import org.hayabaya.datarelated.*;

import java.util.Arrays;

public class LoopersDouble extends Loopers {

    private double[] array;
    private double myNumber = rand.nextDouble();
    public static final Tpe type = Tpe.DOUBLE;

    @Override
    public final Tpe getType() { return type; }



    public LoopersDouble() {
        super(type);
        initializeArrayElements(arrayLength[0]);
        currentArrayLength = arrayLength[0];
        currentCycleNumber = cycleNumbers[0];
    }


    // <editor-fold defaultstate="collapsed" desc=" operate Loop (common to all classes; pseudo-generic) ">

    @Override
    void operateLoop(Operation operation) {
    }

    // </editor-fold>

    @Override
    protected void initializeArrayElements(int arrayLength) {
        array = new double[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = rand.nextDouble();

    }
}