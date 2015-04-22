package org.hayabaya.loopers;

import org.hayabaya.Operation;
import org.hayabaya.Tpe;

public class LoopersDouble extends Loopers {

    private double[] array;
    private double myNumber = rand.nextDouble();
    public final Tpe type = Tpe.DOUBLE;


    public LoopersDouble(int arraySizeMin, int cyclesMin) {
        super(arraySizeMin, cyclesMin, Tpe.DOUBLE);
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
        array = new double[arrayLength];
    }
}