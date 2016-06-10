package org.hayabaya.datarelated;

/**
 * The operations that are available in Hayabaya
 * <ul>
 *     <li><b>ADD:</b> Addition</li>
 *     <li><b>SUBTRACT:</b> Subtraction</li>
 *     <li><b>MULTIPLY:</b> Multiplication</li>
 *     <li><b>DIVIDE:</b> Division</li>
 *     <li><b>LESSTHAN:</b> a less than b comparison </li>
 *     <li><b>GREATERTHAN:</b> a greater than b comparison </li>
 *     <li><b>EQUALS:</b> \= equality comparison </li>
 *     <li><b>NOTEQUAL:</b> \!\= non equality comparison </li>
 * </ul>
 */
public enum Operation {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    LESSTHAN,
    GREATERTHAN,
    EQUALS,
    NOTEQUAL;

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
            case GREATERTHAN:
                return "GreaterThan";
            case EQUALS:
                return "Equals";
            case NOTEQUAL:
                return "NotEqual";
            default:
                throw new IllegalArgumentException();
        }

    }

}
