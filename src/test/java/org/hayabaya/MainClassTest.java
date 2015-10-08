package org.hayabaya;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.ExpectedException;

/**
 * Created by cain on 08/10/15.
 */
public class MainClassTest extends MyTestAbstract {
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void exits() {
        exit.expectSystemExit();
        MainClass.main(new String[]{"i7", "small", "1"});
    }


    @Test
    public void testMainThrowsExceptionWithArgumentLengtNotThree() {
        thrown.expect(IllegalArgumentException.class);
//        thrown.expectMessage(containsString("You must supply 3"));
////        thrown.expectMessage(equalTo("You must supply 3 " +
////                "arguments to the program, 1st: name, 2nd: small/medium/large 3rd: " +
////                "repetitions [1-10] \n"));
        MainClass.main(new String[]{"i7", "small"});
    }

//
//    @Test
//    public void testMainTakesThreeArguments() {
//        MainClass.main(new String[] {"i7", "small", "1"});
//    }
}
