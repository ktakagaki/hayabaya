package org.hayabaya.datarelated;

import java.util.Arrays;

/**
 * <p>Results is the class used to store the data obtained from running the Hayabaya profiling project. The class stores
 * the runtimes from each profiling operation + data type in a 2D array of type long.  The 2D array holds the runtime
 * in ms for array length in the rows, and the number of cycles in the columns.</p>
 *  <p>By calling {@link org.hayabaya.datarelated.Utility#writeResultsToCsv(Results)} the 2D array is written to disk as
 * a text file with the naming convention <b>results.[data type].[operation].[experiment repetion].csv</b></p>
 *
 * <TABLE>
 * <CAPTION>Example of the 2D array</CAPTION>
 * <TR>
 *<TD></TD>
 *<TH>1.000 Cycles</TH>
 *<TH>2.000 Cycles</TH>
 *</TR>
 *<TR>
 *<TH>100 element array</TH>
 *<TD>10 (ms)</TD>
 *<TD>20 (ms)</TD>
 *</TR>
 *<TR>
 *<TH>200 element array</TH>
 *<TD>23 (ms)</TD>
 *<TD>53 (ms)</TD>
 *</TR>
 *</TABLE>
 */
public class Results {

    public long[][] data;
    public int[] cycles;
    public Tpe type;
    public Operation operation;

    private Results() {}

    /**
     *
     * @param data assumed to be (non-null) and <b>non-ragged</b>
     * @param cycles assumed to be (non-null and with length same as data[0])
     * @param type the data type
     * @param operation the operation to perform (+,-,/,*)
     */
    public Results(long[][] data, int[] cycles, Tpe type, Operation operation) {

        assert type != null : "Type most must be null when creating Results";
        assert operation != null : "A getType of operation must be specified";
        assert data != null : "data can't be null";
        assert cycles != null : "cycles can't be null";

        assert data[0] != null : "data must have a non-null 0th element";
        assert data[0].length == cycles.length :
                "Number of columns in data array must be the same as specified cycles";

        this.data = data;
        this.cycles = cycles;
        this.type = type;
        this.operation = operation;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        // Name
        result.append("\t \t \t" + Utility.ANSI_RED + "\033[1m Results Object \033[0m" + Utility.ANSI_RESET + "\n");

        result.append("\t" + Utility.ANSI_BLUE + "{Object:            "
                + Utility.ANSI_RESET + this.getClass().getSimpleName() + "}" + NEW_LINE);

        // Type
        result.append("\t" + Utility.ANSI_PURPLE + "{Type:              " + Utility.ANSI_RESET +
                type + "}" + NEW_LINE);

        // Operation
        result.append("\t" + Utility.ANSI_GREEN + "{Operation:          " + Utility.ANSI_RESET +
                operation + "}" + NEW_LINE);

        // Cycles
        result.append("\t" + Utility.ANSI_CYAN + "{Cycles:            " + Utility.ANSI_RESET +
                Arrays.toString(cycles) + "}" + NEW_LINE);

        result.append("\n \n \t \t" + Utility.ANSI_RED + "\033[1m Last 10 rows of long[][] for the runtime (in ms) " +
                "\033[0m" + Utility.ANSI_RESET);

        //Printing information from the last 5 rows
        //regarding the long[][] array with the actual runtimes.
        long[][] subData = Arrays.copyOfRange(data, data.length - 5, data.length);

        result.append("\n================================================\n");
        result.append("data.length: \t" + data.length + "\n");
        result.append("data[0].length: \t" + data[0].length + "\n");

        result.append("last 5 rows: \n");
        result.append(" \t" + Arrays.deepToString(subData) + NEW_LINE);
        result.append("\n================================================\n");


        return result.toString();
    }


}