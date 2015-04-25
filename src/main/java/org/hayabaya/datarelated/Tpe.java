package org.hayabaya.datarelated;

/**
 * Created by ktakagaki on 15/04/08.
 */
public enum Tpe {
    INT,
    INTEGER_BOXED,
    FLOAT,
    FLOAT_BOXED,
    DOUBLE,
    DOUBLE_BOXED,
    LONG,
    LONG_BOXED;

    @Override
    public String toString() {
        switch(this) {
            case INT:
                return "Integer";
            case INTEGER_BOXED:
                return "Integer_Boxed";
            case FLOAT:
                return "Float";
            case FLOAT_BOXED:
                return "Float_Boxed";
            case DOUBLE:
                return "Double";
            case DOUBLE_BOXED:
                return "Double_Boxed";
            case LONG:
                return "Long";
            case LONG_BOXED:
                return "Long_Boxed";
            default:
                throw new IllegalArgumentException();
        }
    }

}