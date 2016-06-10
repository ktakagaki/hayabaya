package org.hayabaya.datarelated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * General Utility functions used to execute the project.
 */
public class Utility {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Utility.class);


    /**
     * Ensures that correct number of arguments are passet to Hayabaya from the commandline
     * @param args String[] arguments passed from calling from CLI
     * @throws IllegalArgumentException if more than one argument is given
     */
    public static void validateArgsLength(String[] args) throws IllegalArgumentException {
        if (args.length != 1) throw new IllegalArgumentException("Give only one argument, the name of the CPU");

    }

    /**
     * Ensures that the type and value of args passed to main are of the correct type
     * @param args String[]
     * @throws IllegalArgumentException If filename contains illegal finename
     * characters such as "~#;{}"
     */
    public static void validateArgsValues(String[] args) throws IllegalArgumentException {
        String name = args[0];
//        String sampleSize = args[1].toLowerCase();
//        String repetitions = args[2];

        Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\^]");
        Matcher matcher = pattern.matcher(name);

        if (matcher.find() || name.length() <= 0 || name.length() > 20) throw new IllegalArgumentException("Bad name " +
                "arguments");

//        if (!sampleSize.matches("small|medium|large")) throw new IllegalArgumentException("sample size must be either" +
//                " \"small\" , \"medium\" or \"large\" ");
//
//        if (!isInteger(repetitions)) throw new NumberFormatException("non integer rep argument");
//        if (isInteger(repetitions)) {
//            int reps = Integer.parseInt(args[2]);
//            if (reps <= 0) throw new NumberFormatException("repetitions must be > 0");
//            if (reps > 100) throw new NumberFormatException("too many repetitions");
//        }
    }

    /**
     * Validate that an element from a String[] array is an integer
     * @param str String[] array
     * @return true if element is integer, false if it is not
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c <= '/' || c >= ':') {
                return false;
            }
        }
        return true;
    }
}
