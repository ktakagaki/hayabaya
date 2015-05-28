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
    }

    // <editor-fold defaultstate="collapsed" desc=" operate Loop (common to all classes; pseudo-generic) ">

    @Override
    void operateLoop(Operation operation) {
        /** I am storing the last used operation for debug purposes in the toString method */
        setLastSetOperation(operation);

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
        array = new int[arrayLength];
        for(int c = 0; c < array.length; c++) array[c] = rand.nextInt();
    }

    public int[] getArray() {
        return array;
    }


    @Override
    public String toString() {
        String oldString = super.toString();
        StringBuilder result = new StringBuilder(oldString);
        String NEW_LINE = System.getProperty("line.separator");

        // myNumber used to operate with
        result.append(Utility.ANSI_YELLOW + "{myNumber:          " + Utility.ANSI_RESET +
                myNumber + "}" + NEW_LINE);


        if (lastSetOperation == null)
        {
            result.append(Utility.ANSI_RED + "{Last Operation:    NULL} \n" + Utility.ANSI_RESET);
        } else if (lastSetOperation != null) {
            result.append(Utility.ANSI_GREEN + "{Last Operation:    " + Utility.ANSI_RESET +
                    lastSetOperation.toString() + "}" + NEW_LINE);
        } else {
            System.out.println("An uknown error happened with" +
                    " the lastSetOperation variable");
        }

        int[] subArray = Arrays.copyOfRange(array, 1, 10);

        result.append(" \t" +Arrays.toString(subArray) +NEW_LINE);
        result.append("\n\t");

        return result.toString();
    }



}