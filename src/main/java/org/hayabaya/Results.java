package org.hayabaya;

/**
 * Created by ktakagaki on 15/04/08.
 */
public class Results {

    public long[][] data;
    public int[] cycles;
    public Tpe type;
    public Operation operation;

    private Results() {}

    public Results(long[][] data, int[] cycles, Tpe type, Operation operation){

        assert data.length == cycles.length :
                "Number of columns in data array must be the same as specified cycles";

        this.data = data;
        this.cycles = cycles;
        this.type = type;
        this.operation = operation;
    }

}