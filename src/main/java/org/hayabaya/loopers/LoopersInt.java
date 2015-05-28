package org.hayabaya.loopers;

import org.hayabaya.RunSettings;
import org.hayabaya.datarelated.*;

import java.util.Arrays;


public class LoopersInt extends Loopers {

    private int[] array;
    private int myNumber = rand.nextInt();
    public static final Tpe type = Tpe.INT;

    @Override
    public final Tpe getType() { return type; }


    public LoopersInt() {
        super(type);
        initializeArrayElements(arrayLength[0]);
        currentArrayLength = arrayLength[0];
        currentCycleNumber = cycleNumbers[0];
    }

    // <editor-fold defaultstate="collapsed" desc=" operate Loop (common to all classes; pseudo-generic) ">

    @Override
    void operateLoop(Operation operation) {
//        switch( operation ) {
//            case ADD:
//                for (int n = 0; n < cycles; n++) for (int c = 0; c < arrayLength; c++) array[c] += myNumber;
//            case SUBTRACT:
//                for (int n = 0; n < cycles; n++) for (int c = 0; c < arrayLength; c++) array[c] -= myNumber;
//            case MULTIPLY:
//                for (int n = 0; n < cycles; n++) for (int c = 0; c < arrayLength; c++) array[c] *= myNumber;
//            case DIVIDE:
//                for (int n = 0; n < cycles; n++) for (int c = 0; c < arrayLength; c++) array[c] /= myNumber;
//        }
    }

    // </editor-fold>

    @Override
    protected void initializeArrayElements(int arrayLength) {
        array = new int[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = rand.nextInt();
    }

    public int[] getArray() {
        return array;
    }

}