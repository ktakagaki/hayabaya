package org.hayabaya.loopers;

import org.hayabaya.datarelated.*;

import org.hayabaya.RunSettings;


import java.util.Random;

/**Loopers tests the runtime doing: addition, subtraction, multiplication
 * and division on the data types: int, float, double, long and (autoboxed) Integer, Float, Double and Long.
 * A Loopers object will be of a fixed data getType, and the object ensures that operations are performed correctly on
 * the data getType.
 * <p>
 * @author cain
 * @author ktakagaki
 *
 */
public abstract class Loopers {

    int arrayLength = 0;
    int cycles = 0;
    protected Tpe type = null;
    public abstract Tpe getType();
    Operation lastSetOperation = null;
    Random rand = new Random();

    protected Loopers(){}

    public Loopers( int arrayLength, int cycles, Tpe type) {

        assert arrayLength > 0 : "array length must be > 0";
        assert cycles > 0 : "repetitions must be > 0";
        assert type != null : "A Type must be given, not null";

        this.arrayLength = arrayLength;
        this.cycles = cycles;
        this.type = type;

        initArray(arrayLength);

    } //end constructor

    // <editor-fold defaultstate="collapsed" desc=" to make concrete ">

    //intArray, longArray, etc...
    //myInt, myLong, etc...

    public void setLastSetOperation(Operation operation){
        this.lastSetOperation = operation;
    }

    /**switch (operation) {
     case ADD: for( int n = 0; n < cycles; n++) for( int c = 0; c < arrayLength; c++) intArray[c] += myInt;
     case SUBTRACT: for( int n = 0; n < cycles; n++) for( int c = 0; c < arrayLength; c++) intArray[c] -= myInt;
     case MULTIPLY: for( int n = 0; n < cycles; n++) for( int c = 0; c < arrayLength; c++) intArray[c] *= myInt;
     case DIVIDE: for( int n = 0; n < cycles; n++) for( int c = 0; c < arrayLength; c++) intArray[c] /= myInt;
     }*/
    abstract void operateLoop(Operation operation);

    /**Initialize the correct primitive/boxed getType array with random values.
     * This is not done generically, in order to explicity
     * profile primitive operations.
     */
    abstract protected void initArray(int arrayLength);

    abstract void initRandom();

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

        //ToDo initRandom();

        long startTime = System.currentTimeMillis();

        //ToDo: Be VERY careful, I may have introduced a bug here to make the autoboxed Loopers work. It must
        //ToDo: Be examined closer ASAP
        operateLoop(operation);

        long endTime = System.currentTimeMillis();
        return endTime-startTime;
    }

    // <editor-fold defaultstate="collapsed" desc=" toString ">
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        // Title of output
        result.append(Utility.ANSI_YELLOW + "\033[1m Results for " +this.getClass().getSimpleName()
          + "\033[0m" + Utility.ANSI_RESET + "\n");
        // Name of Object
        result.append(Utility.ANSI_BLUE + "{Object:            "
                + Utility.ANSI_RESET + this.getClass().getSimpleName()+ "}" + NEW_LINE);
        // Arraylength
        result.append(Utility.ANSI_GREEN + "{ArrayLength:       "
                + Utility.ANSI_RESET + arrayLength + "}" + NEW_LINE);
        // Cycles
        result.append(Utility.ANSI_CYAN + "{Cycles:            " + Utility.ANSI_RESET +
                cycles+ "}" + NEW_LINE);
        // The getType (Int, Integer etc.)
        result.append(Utility.ANSI_PURPLE + "{Type:              " + Utility.ANSI_RESET +
                getType() + "}" + NEW_LINE);

        return result.toString();
    }
    // </editor-fold>

    /**Runs [[test]] for the given operation
     *
     * @param operation
     * @return
     */
    public Results makeResults(Operation operation) {

        long data[][] = new long[RunSettings.numberOfRowsArrayLength][RunSettings.cycleNumbers.length];//numberOfColumnsCycle];

        //int cycleNumbers[] = ///Intialization stuff moved to RunSettings as static array

        //int arraySizes[] =
        //for(

        // row loop
        for (int rowCountArraySize = RunSettings.ARRAY_SIZE_MIN, rowIndex = 0;
             rowCountArraySize <= RunSettings.ARRAY_SIZE_MAX;
             rowCountArraySize += RunSettings.ARRAY_SIZE_STEPS, rowIndex++) {

            // column loop
            int columnIndex = 0;
            for ( int columnCountCycleNumbers : RunSettings.cycleNumbers ){
//            for (int columnCountCycleNumbers = RunSettings.CYCLES_MIN, columnIndex = 0;
//                 columnCountCycleNumbers <= RunSettings.CYCLES_MAX;
//                 columnCountCycleNumbers += RunSettings.CYCLES_STEPS, columnIndex++) {

                setArrayLength(rowCountArraySize);
                setCycles(columnCountCycleNumbers);

                data[rowIndex][columnIndex] = test(operation);
                columnIndex ++;
            }
        }
        return new Results(data, RunSettings.cycleNumbers, getType(), operation);
    }


}