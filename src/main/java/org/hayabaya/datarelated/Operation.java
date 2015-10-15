package org.hayabaya.datarelated;

/**
 * The operations that are available in Hayabaya
 * <ul>
 *     <li><b>ADD:</b> Addition</li>
 *     <li><b>SUBTRACT:</b> Subtraction</li>
 *     <li><b>MULTIPLY:</b> Multiplication</li>
 *     <li><b>DIVIDE:</b> Division</li>
 *     <li><b>LESSTHAN:</b> \< comparison </li>
 * </ul>
 */
public enum Operation {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    LESSTHAN;

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
            case LESSTHAN:
                return "LessThan";
            default:
                throw new IllegalArgumentException();
        }

    }

}