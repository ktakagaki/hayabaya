package org.hayabaya.datarelated;

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

}