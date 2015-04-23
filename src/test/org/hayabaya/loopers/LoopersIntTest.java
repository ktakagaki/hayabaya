package org.hayabaya.loopers;

import org.hayabaya.RunSettings;
import org.hayabaya.Results;
import org.hayabaya.Tpe;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by cain on 23/04/2015.
 */
public class LoopersIntTest {
    private Loopers loopersInt;

    @Before
    public void setUp() throws Exception {
        loopersInt = new LoopersInt(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN);
        Results result = null;


    }

    @Test
    public void testIntegerType() throws Exception {

        assertThat("Mismatch between types, INT versus FLOAT etc." , loopersInt.type, equalTo(Tpe.INT));
    }
    @Test
    public void testOperateLoop() throws Exception {

    }

    @Test
    public void testInitArray() throws Exception {

    }

    @Test
    public void testOperateLoop1() throws Exception {

    }

    @Test
    public void testInitArray1() throws Exception {

    }

    @Test
    public void testSetArrayLength() throws Exception {

    }

    @Test
    public void testGetArrayLength() throws Exception {

    }

    @Test
    public void testGetCycles() throws Exception {

    }

    @Test
    public void testSetCycles() throws Exception {

    }

    @Test
    public void testTest1() throws Exception {

    }

    @Test
    public void testMakeResults() throws Exception {

    }
}