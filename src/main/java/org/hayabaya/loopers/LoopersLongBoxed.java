package org.hayabaya.loopers;

import org.hayabaya.RunSettings;
import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;
import org.hayabaya.datarelated.Utility;

import java.util.Arrays;

public class LoopersLongBoxed extends Loopers{

    private Long[] array;
    private Long myNumber = rand.nextLong();
    public static final Tpe type = Tpe.LONG_BOXED;
    @Override
    public final Tpe getType() { return type; }

    public LoopersLongBoxed() {
        super(type);
        initializeArrayElements(arrayLength[0]);
        currentArrayLength = arrayLength[0];
        currentCycleNumber = cycleNumbers[0];
    }

    @Override
    void operateLoop(Operation operation) {
    }

    @Override
    protected void initializeArrayElements(int arrayLength) {
        array = new Long[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = new Long( rand.nextLong() );
    }

    public Long[] getArray() {
        return array;
    }
}

// </editor-fold>