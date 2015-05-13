package org.hayabaya.loopers;

import org.hayabaya.RunSettings;
import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Results;
import org.hayabaya.datarelated.Utility;
import org.hayabaya.loopers.*;
import org.hayabaya.datarelated.*;

import java.lang.reflect.Field;


/**
 * Created by cain on 4/28/2015.
 */
public class LittleThings {
    public static void main(String[] args) {

        try {
            RunSettings runBase = new RunSettings();
            Class cls = runBase.getClass();
            System.out.println("Fields =");

            // returns the array of Field objects representing the public fields
            Field f[] = cls.getFields();
            for (int i = 0; i < f.length; i++) {
                System.out.println(f[i]);
            }
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }

//        LittleBase littleBase = new LittleBase();
//        RunSettings runBase = new RunSettings();
//        String runstr = runBase.getClass().getName();
//        String a = littleBase.getClass().getName();
//        System.out.println("Now printing result of: runSettings.getClass().getName(); ");
//        System.out.println(runstr);
//
//        Class<?> c = littleBase.getClass();
//
//
//        System.out.println("Field =");
//
//        try {
//
//            Field floatField = c.getField("myFloat");
//            System.out.println("myFloat: " + floatField.getType().toString());
//
//            Field nameField = c.getField("myName");
//            System.out.println("myName: " + nameField.getType().toString());
//
//            Field doubleField = c.getField("myDouble");
//            System.out.println("myDouble: " + doubleField.getType().toString());
//
//        }
//        catch(NoSuchFieldException e) {
//            System.out.println(e.toString());
//        }
    }
}
