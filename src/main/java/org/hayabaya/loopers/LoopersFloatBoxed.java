package org.hayabaya.loopers;

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;

public class LoopersFloatBoxed extends Loopers {

    private Float[] array;
    private Float myNumber = rand.nextFloat();
    public final Tpe type = Tpe.FLOAT_BOXED;


    public LoopersFloatBoxed(int arraySizeMin, int cyclesMin) {
        super(arraySizeMin, cyclesMin, Tpe.FLOAT_BOXED);
    }

    @Override
    void operateLoop(Operation operation) {
        switch( operation ) {
            case ADD:
                for (int n = 0; n < cycles; n++) for (int c = 0; c < arrayLength; c++) array[c] += myNumber;
            case SUBTRACT:
                for (int n = 0; n < cycles; n++) for (int c = 0; c < arrayLength; c++) array[c] -= myNumber;
            case MULTIPLY:
                for (int n = 0; n < cycles; n++) for (int c = 0; c < arrayLength; c++) array[c] *= myNumber;
            case DIVIDE:
                for (int n = 0; n < cycles; n++) for (int c = 0; c < arrayLength; c++) array[c] /= myNumber;
        }
    }


    @Override
    protected void initArray(int arrayLength) {
        array = new Float[arrayLength];
    }

    public Float[] getArray() {
        return array;
    }






}
