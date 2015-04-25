package org.hayabaya.loopers;

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;
import org.hayabaya.datarelated.Utility;

import java.util.Arrays;

public class LoopersIntegerBoxed extends Loopers {

    private Integer[] array;
    private Integer myNumber = rand.nextInt();
    public final Tpe type = Tpe.INTEGER_BOXED;

    public LoopersIntegerBoxed(int arraySizeMin, int cyclesMin) {

        super(arraySizeMin, cyclesMin, Tpe.INTEGER_BOXED);
    }

    // <editor-fold defaultstate="collapsed" desc=" operate Loop (common to all classes; pseudo-generic) ">

    @Override
    void operateLoop(Operation operation) {
        /** I am storing the last used kind of operation for debug purposes */
        setLastSetOperation(operation);

        switch( operation ) {
            case ADD:
                for (Integer n = 0; n < cycles; n++) for (Integer c = 0; c < arrayLength; c++) array[c] += myNumber;
            case SUBTRACT:
                for (Integer n = 0; n < cycles; n++) for (Integer c = 0; c < arrayLength; c++) array[c] -= myNumber;
            case MULTIPLY:
                for (Integer n = 0; n < cycles; n++) for (Integer c = 0; c < arrayLength; c++) array[c] *= myNumber;
            case DIVIDE:
                for (Integer n = 0; n < cycles; n++) for (Integer c = 0; c < arrayLength; c++) array[c] /= myNumber;
        }
    }

    // </editor-fold>

    @Override
    protected void initArray(int arrayLength) {
        array = new Integer[arrayLength];
    }

    public Integer[] getArray() {
        return array;
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

        Integer[] subArray = Arrays.copyOfRange(array, 1, 10);

        result.append(" \t" +Arrays.toString(subArray) +NEW_LINE);
        result.append("\n\t");

        return result.toString();
    }


}