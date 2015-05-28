package org.hayabaya.loopers;

import org.hayabaya.datarelated.*;


public class LoopersInt extends Loopers {

    private int[] array;
    private int myNumber = rand.nextInt();
    public static final Tpe type = Tpe.INT;

    @Override
    public final Tpe getType() { return type; }


    public LoopersInt() {
        super(type);
        initializeArrayElements(arrayLengths[0]);
        currentArrayLength = arrayLengths[0];
        currentCycleNumber = cycleNumbers[0];
    }

    // <editor-fold defaultstate="collapsed" desc=" operate Loop (common to all classes; pseudo-generic) ">

    @Override
    public void performOperation(Operation operation) {
        System.out.println("Doing Int operation");
//        switch( operation ) {
//            case ADD:
//                for (int n = 0; n < cycles; n++) for (int c = 0; c < arrayLengths; c++) array[c] += myNumber;
//            case SUBTRACT:
//                for (int n = 0; n < cycles; n++) for (int c = 0; c < arrayLengths; c++) array[c] -= myNumber;
//            case MULTIPLY:
//                for (int n = 0; n < cycles; n++) for (int c = 0; c < arrayLengths; c++) array[c] *= myNumber;
//            case DIVIDE:
//                for (int n = 0; n < cycles; n++) for (int c = 0; c < arrayLengths; c++) array[c] /= myNumber;
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