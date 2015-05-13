package org.hayabaya.loopers;

import org.hayabaya.RunSettings;
import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Tpe;
import org.hayabaya.datarelated.Utility;

import java.util.Arrays;

public class LoopersLongBoxed extends Loopers{

    private Long[] array;
    private Long myNumber = (RunSettings.unitTesting) ? new Long(5) : rand.nextLong();
    public static final Tpe type = Tpe.LONG_BOXED;
    @Override
    public final Tpe getType() { return type; }

    public LoopersLongBoxed(int arraySizeMin, int cyclesMin) {
        super(arraySizeMin, cyclesMin, type);
    }

    @Override
    void operateLoop(Operation operation) {
        /** I am storing the last used kind of operation for debug purposes */
        setLastSetOperation(operation);

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
        for(int c = 0; c < array.length; c++) array[c] = new Long( rand.nextLong() );
    }

    public Long[] getArray() {
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

        Long[] subArray = Arrays.copyOfRange(array, 1, 10);

        result.append(" \t" +Arrays.toString(subArray) +NEW_LINE);
        result.append("\n\t");



        return result.toString();
    }
}

// </editor-fold>