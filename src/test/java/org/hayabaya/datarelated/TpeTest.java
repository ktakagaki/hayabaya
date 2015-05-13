package org.hayabaya.datarelated;

import org.hayabaya.MyTestAbstract;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by cain on 4/21/2015.
 */
public class TpeTest extends MyTestAbstract {
    
    @Test
    public void testToString() throws Exception {
        assertThat("Integer",       equalTo(Tpe.INT.toString()));
        assertThat("Integer_Boxed", equalTo(Tpe.INTEGER_BOXED.toString()));
        assertThat("Float",         equalTo(Tpe.FLOAT.toString()));
        assertThat("Float_Boxed",   equalTo(Tpe.FLOAT_BOXED.toString()));
        assertThat("Double",        equalTo(Tpe.DOUBLE.toString()));
        assertThat("Double_Boxed",  equalTo(Tpe.DOUBLE_BOXED.toString()));
        assertThat("Long",          equalTo(Tpe.LONG.toString()));
        assertThat("Long_Boxed",    equalTo(Tpe.LONG_BOXED.toString()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIllegalArgumentException() {
        Tpe myType = Enum.valueOf(Tpe.class, "Proton");
    }

}