/**
 * MainClass
 *
 * Measure the performance of arrays consisting of different primitive data types (int, float, double) and autoboxed
 * data types (Integer, Float).
 *
 * It is possible to test the 4 fundamental operations (Addition, Subtraction, Multplication and Division) on an
 * array of arbitrary length, and with an arbitrary number of cycles repeated on each array.
 */
package org.hayabaya;

import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Results;
import org.hayabaya.datarelated.Utility;
import org.hayabaya.loopers.*;
import org.hayabaya.datarelated.*;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;


/**Main class for testing java operations.
 *
 *           APPROACH TO GENERATE DATA FOR REPORT:
 * 1: create 2D arrays for each datatype and operation type int:[add,sub,mult] + float:[add,sub,mult] etc.
 * ROWS:  Arraylength, start: 1.000 length, end: 100.length, stepsize: +1.000 length, TOTAL = 100
 * COLUMNS: Repetitions, start: 1.000 reps, end: 10.000 reps, stepsize: +1.000 reps, TOTAL = 10
 * 2: final is 100 rows X 10 colums (array length, repetitions)
 * 3: Write each datatype/operationtype to a csv file and process in R.
 *
 * Created by ktakagaki on 15/03/16.
 *
 * @author Ktakagaki
 * @author Slentzen Demian
 */
public class MainClass {

    public static void main(String[] args){
        Logger logger = LoggerFactory.getLogger(MainClass.class);
        logger.info("Starting up logback");

        // print internal state
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);

        System.out.println("Hello to the HayaBaya project \n");
        long startTime = System.currentTimeMillis();

        Results result = null;
        List<Loopers> looperList = new ArrayList<>();

        int asize = RunSettings.ARRAY_SIZE_MIN;
        int acycle = RunSettings.CYCLES_MIN;


        Loopers loopersInt =            new LoopersInt(         asize, acycle);
//        Loopers loopersLong =           new LoopersLong(        asize, acycle);
//        Loopers loopersFloat =          new LoopersFloat(       asize, acycle);
//        Loopers loopersDouble =         new LoopersDouble(      asize, acycle);

//        Loopers loopersIntegerBoxed =   new LoopersIntegerBoxed(asize, acycle);
//        Loopers loopersLongBoxed =      new LoopersLongBoxed(   asize, acycle);
//        Loopers loopersFloatBoxed =     new LoopersFloatBoxed(  asize, acycle);
//        Loopers loopersDoubleBoxed =    new LoopersDoubleBoxed( asize, acycle);


        looperList.add(loopersInt);
//        looperList.add(loopersLong);
//        looperList.add(loopersFloat);
//        looperList.add(loopersDouble);

//        looperList.add(loopersIntegerBoxed);
//        looperList.add(loopersLongBoxed);
//        looperList.add(loopersFloatBoxed);
//        looperList.add(loopersDoubleBoxed);

        //<editor-fold desc="Temporarely not used for debug purposes">
/*        boolean debugPrintStuff = true;
        *//** The first and outermost loop only loops over the repetitions of the entire experiment *//*
        for ( int repetitions = 0; repetitions < RunSettings.TOTAL_EXP_REPS; repetitions++ ){

            *//** Loop over each type of Looper object in the LooperList *//*
            for (Loopers thisLooper : looperList) {

                *//** Loop over the types of operations ADD, SUBTRACT etc. *//*
                for (Operation operation : Operation.values()) {

                    result = thisLooper.makeResults(operation);
                    Utility.writeResultsToCsv(result);

                    if (debugPrintStuff){
                        *//** DEBUG INSERTS START *//*
                        String loopString = thisLooper.toString();
                        System.out.println(loopString);

                        String resultString = result.toString();
                        System.out.println(resultString);

                        *//** DEBUG INSERTS START *//*
                    }


                }
            }
            Utility.setResultCounter(repetitions);
        }*/
        //</editor-fold>


        /** Loops over the repetitions of the entire experiment */
        for ( int repetitions = 0; repetitions < RunSettings.TOTAL_EXP_REPS; repetitions++ ){

            /** Loops over objects (Loopers) in ArrayList */
            for (Loopers thisLooper : looperList) {

                /** Loops over operations ADD, SUBTRACT etc. */
                for (Operation operation : Operation.values()) {

                    result = thisLooper.makeResults(operation);
                    Utility.writeResultsToCsv(result);

                    if (RunSettings.debug){
                        /** DEBUG INSERTS START */
                        System.out.println(thisLooper.toString());
                        System.out.println(result.toString());
                        /** DEBUG INSERTS START */
                    }


                }
            }
            Utility.setResultCounter(repetitions);
        }


        long endTime = System.currentTimeMillis();
        long totalRunTime =  endTime-startTime;

        if(RunSettings.debug){
            /** A small summary report over this run */
            System.out.println("\n \n");
            System.out.println("The total runtime was: " + totalRunTime);
            System.out.println("The following Rows and Columns were generated for the 2D arrays...\n");
            System.out.println("Rows: " + RunSettings.numberOfRowsSize +
                    " Columns: " + RunSettings.numberOfColumnsCycle);
        }



        logger.info("Hayabya finished running, Exiting logback..");
    }
}