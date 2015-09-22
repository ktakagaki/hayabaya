package org.hayabaya.datarelated;

import org.hayabaya.RunSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class stores all of the data that is relevant to the profiling project. It gathers the data and makes it
 * possible to write it to disk in csv files.
 * <p>By calling {@link org.hayabaya.datarelated.Utility#writeResultsToCsv(Results)} the 2D array is written to disk as
 * a text file with the naming convention <b>results.[data type].[operation].[experiment repetion].csv</b></p>
 */
public class Results {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Results.class);

    //<editor-fold desc="data[][] Outline">
    /*
    The long[][] Array contains the actual runtimes of the operations as measured in milliseconds. The table has the
    form of increasing arraylength in each column, and increasing number of cycles in the rows. See outline below
    +--------+-------+--------+-------+-------+
    |        |       | Cycles |       |       |
    +--------+-------+--------+-------+-------+
    | Length | 1k    | 2k     | 3k    | 4k    |
    +--------+-------+--------+-------+-------+
    | 200    | 10 ms | 20 ms  | 30 ms | 40 ms |
    +--------+-------+--------+-------+-------+
    | 400    | 20 ms | 30 ms  | 40 ms | 50 ms |
    +--------+-------+--------+-------+-------+
    | 500    | 30 ms | 40 ms  | 50 ms | 60 ms |
    +--------+-------+--------+-------+-------+
     */
    //</editor-fold>
    public long[][] data; // 2D array of Runtime in milliseconds
    public int theRepetitionNumber;
    public Tpe type;
    public Operation operation;
    public String name;
    public String isBoxed;

    public long[][] getData() {
        return data;
    }

    public int getTheRepetitionNumber() {
        return theRepetitionNumber;
    }

    public Tpe getType() {
        return type;
    }

    public Operation getOperation() {
        return operation;
    }

    public String getName() {
        return name;
    }

    private Results() {
    }

    /**
     * Primary constructor to be used by clients.
     * @param data                a 2D long[][] array assumed to be (non-null) and <b>non-ragged</b>
     * @param theRepetitionNumber The current repetitions number for this result out of the total replicates
     * @param type                the data type (int, float, double, long) and their Boxed counterparts
     * @param operation           the operation performed (+,-,/,*)
     */
    public Results(long[][] data, int theRepetitionNumber, Tpe type, Operation operation) {

        RunSettings runSettings = RunSettings.getRunSettings();
        this.name = runSettings.getNameOfProcessor();
        this.data = data;
        this.theRepetitionNumber = theRepetitionNumber;
        this.type = type; // Can be null from creation from Loopers abstract class!
        this.operation = operation;
        this.isBoxed = isBoxed();
    }

    /**
     * Return a string for the filename following the res_[CPU]_[Data Type]_[Operation]_[Current Repetitions].csv
     */
    public String getFileName(){
        String fileName = "res_" + "name_" + type.toString() + "_" + operation.toString() + "_" + isBoxed +
                "_" + theRepetitionNumber + ".csv";
        return fileName;
    }

    /**
     * Determine if this instance contains data for Boxed or primitive data types
     * @param type The data type used for the instantiation
     * @return A string indicating a boolean answer with TRUE for Boxed types and FALSE for primitive data types
     * @throws IllegalArgumentException is thrown if no type was identified
     */
    public String isBoxed(Tpe type) throws IllegalArgumentException {
        switch (type) {
            case INT:
                return "FALSE";
            case INTEGER_BOXED:
                return "TRUE";
            case FLOAT:
                return "FALSE";
            case FLOAT_BOXED:
                return "TRUE";
            case DOUBLE:
                return "FALSE";
            case DOUBLE_BOXED:
                return "TRUE";
            case LONG:
                return "FALSE";
            case LONG_BOXED:
                return "TRUE";
            default:
                throw new IllegalArgumentException("Type given is not valid");
        }
    }

    /**
     * Determine if this instance contains data for Boxed or primitive data types
     * @return A string indicating a boolean answer with TRUE for Boxed types and FALSE for primitive data types
     * @throws IllegalArgumentException is thrown if no type was identified
     */
    public String isBoxed() throws IllegalArgumentException {
        switch (this.type) {
            case INT:
                return "FALSE";
            case INTEGER_BOXED:
                return "TRUE";
            case FLOAT:
                return "FALSE";
            case FLOAT_BOXED:
                return "TRUE";
            case DOUBLE:
                return "FALSE";
            case DOUBLE_BOXED:
                return "TRUE";
            case LONG:
                return "FALSE";
            case LONG_BOXED:
                return "TRUE";
            default:
                throw new IllegalArgumentException("Type given is not valid");
        }
    }

}