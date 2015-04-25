package org.hayabaya.loopers;

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;

public class LoopersLongBoxed extends Loopers{

    private Long[] array;
    private Long myNumber = rand.nextLong();
    public final Tpe type = Tpe.LONG_BOXED;

    public LoopersLongBoxed(int arraySizeMin, int cyclesMin) {

        super(arraySizeMin, cyclesMin, Tpe.LONG_BOXED);
    }


    @Override
    void operateLoop(Operation operation) {
        switch (operation) {
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
        array = new Long[arrayLength];
    }

    public Long[] getArray() {
        return array;
    }
}

// </editor-fold>