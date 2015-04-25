package org.hayabaya.datarelated;

import java.util.Arrays;

/**
 * Created by ktakagaki on 15/04/08.
 */
public class Results {

    public long[][] data;
    public int[] cycles;
    public Tpe type;
    public Operation operation;

    private Results() {}

    public Results(long[][] data, int[] cycles, Tpe type, Operation operation) {
        assert type != null : "Type most nust be null when creating Results";
        assert operation != null : "A type of operation must be specified";

        assert data.length == cycles.length :
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
        result.append("\t \t \t \t" + Utility.ANSI_RED + "\033[1m Results Object \033[0m" + Utility.ANSI_RESET + "\n");

        result.append("\t" + Utility.ANSI_BLUE + "{Object:            "
                + Utility.ANSI_RESET + this.getClass().getSimpleName()+ "}" + NEW_LINE);
        // Cycles
        result.append("\t" +Utility.ANSI_CYAN + "{Cycles:            " + Utility.ANSI_RESET +
                cycles+ "}" + NEW_LINE);
        // Type
        result.append("\t" +Utility.ANSI_PURPLE + "{Type:              " + Utility.ANSI_RESET +
                type+ "}" + NEW_LINE);
        // Operation
        result.append("\t" +Utility.ANSI_GREEN + "{Operation:              " + Utility.ANSI_RESET +
                operation+ "}" + NEW_LINE);

        result.append(Arrays.deepToString(data));

        result.append("\n \n");

        return result.toString();
    }


}