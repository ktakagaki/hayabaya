package org.hayabaya.datarelated;

import org.hayabaya.RunSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Results is the class used to store the data obtained from running the Hayabaya profiling project. The class stores
 * the runtimes from each profiling operation + data type in a 2D array of type long.  The 2D array holds the runtime
 * in ms for array length in the rows, and the number of cycles in the columns.</p>
 * <p>By calling {@link org.hayabaya.datarelated.Utility#writeResultsToCsv(Results)} the 2D array is written to disk as
 * a text file with the naming convention <b>results.[data type].[operation].[experiment repetion].csv</b></p>
 * <TABLE>
 * <CAPTION>Example of the 2D array</CAPTION>
 * <TR>
 * <TD></TD>
 * <TH>1.000 Cycles</TH>
 * <TH>2.000 Cycles</TH>
 * </TR>
 * <TR>
 * <TH>100 element array</TH>
 * <TD>10 (ms)</TD>
 * <TD>20 (ms)</TD>
 * </TR>
 * <TR>
 * <TH>200 element array</TH>
 * <TD>23 (ms)</TD>
 * <TD>53 (ms)</TD>
 * </TR>
 * </TABLE>
 */
public class Results {

    private static String TAG;
    private static Class<?> cls;
    private static Logger logger;

    static {
        try {
            Class<?> cls = Class.forName("org.hayabaya.datarelated.Results");
            TAG = cls.toString();
            Logger logger = LoggerFactory.getLogger(cls);
            logger.info("Results logger sucessfully initialized");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("The classname was not found and TAG not initialized", e);
        }
    }

    public long[][] data;
    public int theRepetitionNumber;
    public Tpe type;
    public Operation operation;
    public String name;
    public String isBoxed;

    private Results() {
    }

    /**
     * @param data                assumed to be (non-null) and <b>non-ragged</b>
     * @param theRepetitionNumber At what current number of repetition the results were made at
     * @param type                the data type
     * @param operation           the operation performed (+,-,/,*)
     */
    public Results(long[][] data, int theRepetitionNumber, Tpe type, Operation operation) {

        RunSettings runSettings = RunSettings.getRunSettings();
        this.name = runSettings.getName();
        this.data = data;
        this.theRepetitionNumber = theRepetitionNumber;
        this.type = type; // Can be null from creation from Loopers abstract class!
        this.operation = operation;
        this.isBoxed = isBoxed(type);
    }

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

}