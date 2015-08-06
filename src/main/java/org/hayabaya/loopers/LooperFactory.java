package org.hayabaya.loopers;

import org.hayabaya.datarelated.Tpe;

/**
 * Created by cain on 5/28/2015.
 */
public class LooperFactory {
    private static LooperFactory instance = new LooperFactory();
    private LooperFactory(){}
    public static LooperFactory getinstance(){ return instance; }

    public Loopers createLooperInstance(Tpe type){
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
