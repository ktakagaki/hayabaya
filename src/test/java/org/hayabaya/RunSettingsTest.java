package org.hayabaya;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by cain on 4/28/2015.
 */
public class RunSettingsTest extends MyTestAbstract{

    RunSettings runSettings = RunSettings.getRunSettingsInstance();


    @Test
    public void testGenerateIntegerLinearSpaceWorksCorrectly() {

        int[] OneToTen = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] generated = runSettings.generateIntegerLinearSpace(1, 10, 1);
        assertThat(OneToTen, equalTo(generated));

    }

    @Test
    public void testInclusiveFencePostCases() {
        // Length = Ceiling[ ((Max - Min) / Steps) + 1 ]
        int[][] fencePostCases = {
                {1, 10, 1},
                {2, 20, 2}};

        int[] fencePost = runSettings.generateIntegerLinearSpace(1, 10, 4); //[1, 5, 9, 13]
        int fencePostMaxValue = fencePost[fencePost.length - 1];

        //[1, 11, 21, 31, 41, 51, 61, 71, 81, 91, 101]
        int[] fencePost2 = runSettings.generateIntegerLinearSpace(1, 100, 10);
        int fencePost2MaxValue = fencePost2[fencePost2.length - 1];

        //[1, 11, 21, 31, 41, 51, 61, 71, 81, 91, 101]
        int[] fencePost3 = runSettings.generateIntegerLinearSpace(1, 10, 3);
        int fencePost3MaxValue = fencePost3[fencePost3.length - 1];


        //Is the result inclusive?
        assertEquals(fencePost.length, 4);

        // Inclusiveness should never exceed Max+StepSize
        assertTrue(fencePostMaxValue <= 10 + 4);
        assertTrue(fencePost2MaxValue <= 100 + 10);
        assertTrue(fencePost3MaxValue <= 10 + 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionsThrown() throws IllegalArgumentException {
        int[] impossibleValues = runSettings.generateIntegerLinearSpace(-1, -10, -3);
    }
}