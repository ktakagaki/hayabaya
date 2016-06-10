package org.hayabaya.datarelated;

/**
 * The data types available in the Hayabaya project
 * <ul>
 *     <li><b>INT</b> Integer</li>
 *     <li><b>INTEGER_BOXED</b> Boxed Integer</li>
 *     <li><b>FLOAT</b> Float</li>
 *     <li><b>FLOAT_BOXED</b> Boxed Float</li>
 *     <li><b>DOUBLE</b> Double</li>
 *     <li><b>DOUBLE_BOXED</b> Boxed Double</li>
 *     <li><b>LONG</b> Long</li>
 *     <li><b>LONG_BOXED</b> Boxed Long</li>
 * </ul>
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