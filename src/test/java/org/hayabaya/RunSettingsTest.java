package org.hayabaya;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by cain on 4/28/2015.
 */
public class RunSettingsTest extends MyTestAbstract{

    RunSettings runSettings = new RunSettings();
    String runstr = runSettings.getClass().getName();
    Class<?> c = runSettings.getClass();


    @Test
    public void testGotRightClass(){
        assertThat(runstr, equalTo("org.hayabaya.RunSettings"));
    }


    @Test
    public void testRunSettingsHasRightNumberOfValues(){
        Field f[] = c.getFields();
//        assertThat(f.length, equalTo(11));
    }


    @Test
    public void testFieldsAreOfCorrectType(){
        try {
            String a = c.getField("debug").getType().toString();
            assertThat("boolean", equalTo(a));

            String b = c.getField("unitTesting").getType().toString();
            assertThat("boolean", equalTo(b));

            String d = c.getField("ARRAY_SIZE_MIN").getType().toString();
            assertThat("int", equalTo(d));

            String e = c.getField("ARRAY_SIZE_MAX").getType().toString();
            assertThat("int", equalTo(e));

            String f = c.getField("ARRAY_SIZE_STEPS").getType().toString();
            assertThat("int", equalTo(f));

            String g = c.getField("numberOfRowsArrayLength").getType().toString();
            assertThat("int", equalTo(g));

            String h = c.getField("CYCLES_MIN").getType().toString();
            assertThat("int", equalTo(h));

            String i = c.getField("CYCLES_MAX").getType().toString();
            assertThat("int", equalTo(i));

            String j = c.getField("CYCLES_STEPS").getType().toString();
            assertThat("int", equalTo(j));

            String k = c.getField("TOTAL_EXP_REPS").getType().toString();
            assertThat("int", equalTo(k));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }
}