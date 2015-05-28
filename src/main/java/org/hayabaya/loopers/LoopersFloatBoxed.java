package org.hayabaya.loopers;

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;

public class LoopersFloatBoxed extends Loopers {

    private Float[] array;
    private Float myNumber = rand.nextFloat();
    public static final Tpe type = Tpe.FLOAT_BOXED;
    @Override
    public final Tpe getType() { return type; }


    public LoopersFloatBoxed()  {
        super(type);
        initializeArrayElements(arrayLengths[0]);
        currentArrayLength = arrayLengths[0];
        currentCycleNumber = cycleNumbers[0];
    }

    @Override
    protected void initializeArrayElements(int arrayLength) {
        array = new Float[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = new Float( rand.nextFloat() );

    }

    @Override
    public void performOperation(Operation operation) {
        System.out.println("Doing FloatBoxed operation");
    }
}
