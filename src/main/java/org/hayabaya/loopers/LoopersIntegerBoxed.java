package org.hayabaya.loopers;

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;

public class LoopersIntegerBoxed extends Loopers {

    private Integer[] array;
    private Integer myNumber = rand.nextInt();
    public static final Tpe type = Tpe.INTEGER_BOXED;
    @Override
    public final Tpe getType() { return type; }

    public LoopersIntegerBoxed()  {
        super(type);
        initializeArrayElements(arrayLengths[0]);
        currentArrayLength = arrayLengths[0];
        currentCycleNumber = cycleNumbers[0];
    }

    @Override
    protected void initializeArrayElements(int arrayLength) {
        array = new Integer[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = new Integer( rand.nextInt() );
    }

    @Override
    public void performOperation(Operation operation) {
        System.out.println("Doing IntegerBoded operation");
    }

}