package org.hayabaya.loopers;

import org.hayabaya.datarelated.*;

import java.util.Arrays;

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
        /** I am storing the last used kind of operation for debug purposes */
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
        array = new Double[arrayLength];
    }


    @Override
    public String toString() {
        String oldString = super.toString();
        StringBuilder result = new StringBuilder(oldString);
        String NEW_LINE = System.getProperty("line.separator");

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

        Double[] subArray = Arrays.copyOfRange(array, 1, 10);

        result.append(" \t" +Arrays.toString(subArray) +NEW_LINE);
        result.append("\n\t");

        return result.toString();
    }



}