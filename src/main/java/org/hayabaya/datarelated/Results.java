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
    public int theRepetitionNumber;
    public Tpe type;
    public Operation operation;

    private Results() {}

    /**
     *
     * @param data assumed to be (non-null) and <b>non-ragged</b>
     * @param theRepetitionNumber At what current number of repetition the results were made at
     * @param type the data type
     * @param operation the operation performed (+,-,/,*)
     */
    public Results(long[][] data, int theRepetitionNumber, Tpe type, Operation operation) {

        assert type != null : "Type most must be null when creating Results";
        assert operation != null : "A getType of operation must be specified";
        assert data != null : "data can't be null";
        assert theRepetitionNumber >= 0 : "The number of repetitions must be at least 1";

        this.data = data;
        this.theRepetitionNumber = theRepetitionNumber;
        this.type = type;
        this.operation = operation;
    }
}