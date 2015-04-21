package org.hayabaya;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


/**
 * Created by cain on 4/21/2015.
 */
public class OperationTest {

    @Test
    public void testToString() throws Exception {
        assertThat("Add",       equalTo(Operation.ADD.toString()));
        assertThat("Subtract",  equalTo(Operation.SUBTRACT.toString()));
        assertThat("Multiply",  equalTo(Operation.MULTIPLY.toString()));
        assertThat("Divide",    equalTo(Operation.DIVIDE.toString()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsIllegalArgumentException() {
        Operation myOperation = Enum.valueOf(Operation.class, "Proton");
    }
}