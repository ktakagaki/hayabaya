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
    public void programExitsCorrectWithCorrectArguments() {
        exit.expectSystemExit();
        MainClass.main(new String[]{"i7", "small", "1"});
    }

    /*Test the functions
    validate args
    validateArgsValues
    isInteger
    * */

    @Test
    public void testValidateArgsWithBadArgs() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Give 3 arguments");
        MainClass.validateArgs(new String[]{"i7", "small"});
    }

    @Test
    public void testValidateArgsValuesBadName() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Bad name arguments");
        MainClass.validateArgsValues(new String[]{"i7+3770k", "small", "1"});
        //ToDo: Use parameterized test to check for all illegal chars
    }

    @Test
    public void testValidateArgsValuesZeroLengthName() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Bad name arguments");
        MainClass.validateArgsValues(new String[]{"", "small", "1"});
    }

    @Test
    public void testValidateArgsValuesValueIncorrectSampleSize() throws IllegalArgumentException {
        String mstr = "sample size must be either \"small\" , \"medium\" or \"large\" ";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(mstr);
        MainClass.validateArgsValues(new String[]{"i7", "mall", "1"});
    }

    @Test
    public void testValidateArgsValuesDoubleReps() throws NumberFormatException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("non integer rep argument");
        MainClass.validateArgsValues(new String[]{"i7", "small", "1.0"});
    }

    @Test
    public void testValidateArgsValuesNegativeReps() throws NumberFormatException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("repetitions must be > 0");
        MainClass.validateArgsValues(new String[]{"i7", "small", "0"});
    }

    @Test
    public void testValidateArgsValuesTooManyReps() throws NumberFormatException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("too many repetitions");
        MainClass.validateArgsValues(new String[]{"i7", "small", "101"});
    }
}
