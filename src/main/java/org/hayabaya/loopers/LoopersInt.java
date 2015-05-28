package org.hayabaya.loopers;

import org.hayabaya.datarelated.*;


public class LoopersInt extends Loopers {

    private int[] array;
    private int myNumber = rand.nextInt();
    public static final Tpe type = Tpe.INT;

    public LoopersInt() {
        super(type);
        initializeArrayElements(arrayLengths[0]);
        currentArrayLength = arrayLengths[0];
        currentCycleNumber = cycleNumbers[0];
    }

    @Override
    public final Tpe getType() { return type; }

    @Override
    public final void makeResults(){
        Results results = null;
        int totalRepetitions = runSettings.getTotalExperimentRepetitions();
        for (; currentRepetitionNumber <= totalRepetitions; currentRepetitionNumber++){
            // Loop over the types of operations ADD, SUBTRACT etc.
                for (Operation anOperationToUse : Operation.values()){

                }


        }

    }




    // <editor-fold defaultstate="collapsed" desc=" operate Loop (common to all classes; pseudo-generic) ">

    @Override
    public void performOperation(Operation operation) {
        Results results = null;
        int totalRepetitions = runSettings.getTotalExperimentRepetitions();
        for (int i = currentRepetitionNumber; i <= totalRepetitions; i++){

        }
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

}