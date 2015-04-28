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
        LittleBase littleBase = new LittleBase();
        String a = littleBase.getClass().getName();
        System.out.println("Now printing result of: runSettings.getClass().getName(); ");
        System.out.println(a);

        Class<?> c = littleBase.getClass();


        System.out.println("Field =");

        try {

            Field floatField = c.getField("myFloat");
            System.out.println("myFloat: " + floatField.getType().toString());

            Field nameField = c.getField("myName");
            System.out.println("myName: " + nameField.getType().toString());

            Field doubleField = c.getField("myDouble");
            System.out.println("myDouble: " + doubleField.getType().toString());

        }
        catch(NoSuchFieldException e) {
            System.out.println(e.toString());
        }
    }
}
