package org.hayabaya.datarelated;

/**
 * Created by ktakagaki on 15/04/08.
 */
public enum Operation {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE;

    @Override
    public String toString() {
        switch (this) {
            case ADD:
                return "Add";
            case SUBTRACT:
                return "Subtract";
            case MULTIPLY:
                return "Multiply";
            case DIVIDE:
                return "Divide";
            default:
                throw new IllegalArgumentException();
        }

    }

}