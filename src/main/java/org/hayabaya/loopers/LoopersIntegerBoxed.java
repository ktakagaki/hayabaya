package org.hayabaya.loopers;

import org.hayabaya.RunSettings;
import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;
import org.hayabaya.datarelated.Utility;

import java.util.Arrays;

public class LoopersIntegerBoxed extends Loopers {

    private Integer[] array;
    private Integer myNumber = rand.nextInt();
    public static final Tpe type = Tpe.INTEGER_BOXED;
    @Override
    public final Tpe getType() { return type; }

    public LoopersIntegerBoxed()  {
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
        array = new Integer[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = new Integer( rand.nextInt() );
    }

    public Integer[] getArray() {
        return array;
    }
}