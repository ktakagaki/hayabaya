package org.hayabaya.loopers;

import org.hayabaya.Hayabaya;
import org.hayabaya.Operation;
import org.hayabaya.Results;
import org.hayabaya.Tpe;

import java.util.Random;

/**Loopers tests the runtime doing: addition, subtraction, multiplication
 * and division on the data types: int, float, double, long and (autoboxed) Integer, Float, Double and Long.
 * A Loopers object will be of a fixed data type, and the object ensures that operations are performed correctly on
 * the data type.
 * <p>
 * @author cain
 * @author ktakagaki
 *
 */
public abstract class Loopers {

    //@suppresswarnings("unused")

    int arrayLength = 0;
    int cycles = 0;
    Tpe type = null;
    Random rand = new Random(); // testing with seed
    //final int cyclesPreLoop = 3000;

    protected Loopers(){}
    public Loopers( int arrayLength, int cycles/*, Tpe type*/) {

        assert arrayLength > 0 : "array length must be > 0";
        assert cycles > 0 : "repetitions must be > 0";

        this.arrayLength = arrayLength;
        this.cycles = cycles;
        //this.type = type.tolowercase();
        this.type = type;

        initArray(arrayLength);

    } //end constructor

    // <editor-fold defaultstate="collapsed" desc=" to make concrete ">

    //intArray, longArray, etc...
    //myInt, myLong, etc...

    /**switch (operation) {
     case ADD: for( int n = 0; n < cycles; n++) for( int c = 0; c < arrayLength; c++) intArray[c] += myInt;
     case SUBTRACT: for( int n = 0; n < cycles; n++) for( int c = 0; c < arrayLength; c++) intArray[c] -= myInt;
     case MULTIPLY: for( int n = 0; n < cycles; n++) for( int c = 0; c < arrayLength; c++) intArray[c] *= myInt;
     case DIVIDE: for( int n = 0; n < cycles; n++) for( int c = 0; c < arrayLength; c++) intArray[c] /= myInt;
     }*/
    abstract void operateLoop(Operation operation);

    /**initialize the correct primitive/boxed type array.
     * this is not done generically, in order to explicity
     * profile primitive operations.
     */
    abstract protected void initArray(int arrayLength);

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" general getters and setters section ">

    final public void setArrayLength(int arrayLength) {
        assert arrayLength > 0 : "array length must be above zero";
        this.arrayLength = arrayLength;
        initArray(arrayLength);
    }

    final public int getArrayLength() {
        return arrayLength;
    }

    final public int getCycles() {
        return cycles;
    }

    final public void setCycles(int cycles) {
        assert cycles > 0 : "repetitions must be above zero";
        this.cycles = cycles;
    }

    // </editor-fold>



    public long test(Operation operation) {

//      //THIS ATTEMPT AT INLINING WON'T WORK: IT WON'T AFFECT THE REAL LOOP
//      //Initial run to trigger inlining and run-time optimizations
//      switch (operation) {
//          case ADD: for( int n = 0; n < cycles; n++)      operateAdd(0);
//          case SUBTRACT: for( int n = 0; n < cycles; n++) operateSubtract(0);
//          case MULTIPLY: for( int n = 0; n < cycles; n++) operateMultiply(0);
//          case DIVIDE: for( int n = 0; n < cycles; n++)   operateDivide(0);
//      }

        long startTime = System.currentTimeMillis();

        operateLoop(operation);

        long endTime = System.currentTimeMillis();
        return endTime-startTime;
    }

    public Results makeResults(Operation operation) {

        long data[][] = new long[Hayabaya.numberOfRowsSize][Hayabaya.numberOfColumnsCycle];

        int cycles[] = new int[Hayabaya.numberOfColumnsCycle];

        for (int columnCount = Hayabaya.CYCLES_MIN, columnIndex = 0;
             columnCount <= Hayabaya.CYCLES_MAX;
             columnCount += Hayabaya.CYCLES_STEPS, columnIndex++) {

            cycles[columnIndex] = columnCount;

        }

        // row loop
        for (int rowCount = Hayabaya.ARRAY_SIZE_MIN, rowIndex = 0;
             rowCount <= Hayabaya.ARRAY_SIZE_MAX;
             rowCount += Hayabaya.ARRAY_SIZE_STEPS, rowIndex++) {

            // column loop
            for (int columnCount = Hayabaya.CYCLES_MIN, columnIndex = 0;
                 columnCount <= Hayabaya.CYCLES_MAX;
                 columnCount += Hayabaya.CYCLES_STEPS, columnIndex++) {

                setArrayLength(rowCount);
                setCycles(columnCount);

                data[rowIndex][columnIndex] = test(operation);

            }
        }
        return new Results(data, cycles, type, operation);
    }


}