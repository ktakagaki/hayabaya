package org.hayabaya.loopers;

import org.hayabaya.datarelated.*;

public class LoopersDoubleBoxed extends Loopers {

    private Double[] array;
    private Double myNumber = rand.nextDouble();
    public final Tpe type = Tpe.DOUBLE_BOXED;



    public LoopersDoubleBoxed(int arraySizeMin, int cyclesMin) {

        super(arraySizeMin, cyclesMin, Tpe.DOUBLE_BOXED);
    }


    // <editor-fold defaultstate="collapsed" desc=" operate Loop (common to all classes; pseudo-generic) ">

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

    // </editor-fold>

    @Override
    protected void initArray(int arrayLength) {
        array = new Double[arrayLength];
    }
}