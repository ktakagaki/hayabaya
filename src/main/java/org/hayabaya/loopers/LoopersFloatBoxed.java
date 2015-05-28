package org.hayabaya.loopers;

import org.hayabaya.RunSettings;
import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;
import org.hayabaya.datarelated.Utility;

import java.util.Arrays;

public class LoopersFloatBoxed extends Loopers {

    private Float[] array;
    private Float myNumber = rand.nextFloat();
    public static final Tpe type = Tpe.FLOAT_BOXED;
    @Override
    public final Tpe getType() { return type; }


    public LoopersFloatBoxed()  {
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
        array = new Float[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = new Float( rand.nextFloat() );

    }

    public Float[] getArray() {
        return array;
    }
}
