package org.hayabaya.loopers;

import org.hayabaya.datarelated.Tpe;

/**
 * Factory class that creates data type specific instances of the Loopers class.
 * The class can return an instance of any of the 8 different datatypes available in Hayabaya.
 * @see org.hayabaya.datarelated.Tpe
 */
public class LooperFactory {
    private static LooperFactory instance = new LooperFactory();
    private LooperFactory(){}
    public static LooperFactory getinstance(){ return instance; }

    /**
     * Returns an instance of one of the Loopers_DATA_TYPE classes. To get an instance of a LoopersInt class use
     * <pre>
     *     LooperFactory looperFactory = LooperFactory.getinstance();
     *     Loopers anIntInstance = looperFactory.createLooperInstance(Tpe.INT);
     * </pre>
     * @param type One of the datatypes from the Tpe enum class @see org.hayabaya.datarelated.Tpe
     * @return A data type specific instance of Loopers sub-classes
     * @throws IllegalArgumentException Default fallthrough if argument does not match any known data type
     */
    public Loopers createLooperInstance(Tpe type) throws IllegalArgumentException{
        switch (type){
            case INT:
                return new LoopersInt();
            case INTEGER_BOXED:
                return new LoopersIntegerBoxed();
            case FLOAT:
                return new LoopersFloat();
            case FLOAT_BOXED:
                return new LoopersFloatBoxed();
            case DOUBLE:
                return new LoopersDouble();
            case DOUBLE_BOXED:
                return new LoopersDoubleBoxed();
            case LONG:
                return new LoopersLong();
            case LONG_BOXED:
                return new LoopersLongBoxed();
            default:
                throw new IllegalArgumentException();
        }
    }
}
