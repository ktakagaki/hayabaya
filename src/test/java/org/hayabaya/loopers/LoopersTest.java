package org.hayabaya.loopers;

import org.hayabaya.MyTestAbstract;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cain on 4/24/2015.
 */
abstract public class LoopersTest extends MyTestAbstract {

    public Loopers looper;

    @Before
    abstract public void setUp() throws Exception;

    @Test
    abstract public void testOperateLoop() throws Exception;

    @Test
    abstract public void testInitArray() throws Exception;

    @Test
    abstract public void testOperateLoop1() throws Exception;

    @Test
    abstract public void testInitArray1() throws Exception;

    @Test
    abstract public void testSetArrayLength() throws Exception;

    @Test
    abstract public void testGetArrayLength() throws Exception;

    @Test
    abstract public void testGetCycles() throws Exception;

    @Test
    abstract public void testSetCycles() throws Exception;

    @Test
    abstract public void testTest1() throws Exception;

    @Test
    abstract public void testMakeResults() throws Exception;
}