package org.hayabaya.loopers;

import org.hayabaya.RunSettings;
import org.hayabaya.datarelated.*;

import java.util.Arrays;

public class LoopersDoubleBoxed extends Loopers {

    private Double[] array;
    private Double myNumber = rand.nextDouble();
    public static final Tpe type = Tpe.DOUBLE_BOXED;
    @Override
    public final Tpe getType() { return type; }



    public LoopersDoubleBoxed()  {
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
        array = new Double[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = new Double( rand.nextDouble() );

    }
}