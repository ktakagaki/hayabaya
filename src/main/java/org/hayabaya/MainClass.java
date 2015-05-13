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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;


/**Main class for testing java operations.
 *
 *           APPROACH TO GENERATE DATA FOR REPORT:
 * 1: create 2D arrays for each datatype and operation getType int:[add,sub,mult] + float:[add,sub,mult] etc.
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
    public static Logger logger = LoggerFactory.getLogger(MainClass.class);

    public static void main(String[] args){

        logger.info("Starting up logback");

        // assume SLF4J is bound to logback in the current environment
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        // print logback's internal status
        StatusPrinter.print(lc);

        System.out.println("Hello to the HayaBaya project \n");

        Results result = null;
        List<Loopers> looperList = new ArrayList<>();

        int aSize = RunSettings.ARRAY_SIZE_MIN;
        int aCycle = RunSettings.CYCLES_MIN;


        looperList.add(new LoopersInt(         aSize, aCycle));
        looperList.add(new LoopersLong(        aSize, aCycle));
        looperList.add(new LoopersFloat(       aSize, aCycle));
        looperList.add(new LoopersDouble(      aSize, aCycle));

        looperList.add(new LoopersIntegerBoxed(aSize, aCycle));
        looperList.add(new LoopersLongBoxed(   aSize, aCycle));
        looperList.add(new LoopersFloatBoxed(  aSize, aCycle));
        looperList.add(new LoopersDoubleBoxed( aSize, aCycle));

        //<editor-fold desc="Temporarely not used for debug purposes">
/*        boolean debugPrintStuff = true;
        *//** The first and outermost loop only loops over the repetitions of the entire experiment *//*
        for ( int repetitions = 0; repetitions < RunSettings.TOTAL_EXP_REPS; repetitions++ ){

            *//** Loop over each getType of Looper object in the LooperList *//*
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
                for (Operation thisOperation : Operation.values()) {

                    result = thisLooper.makeResults(thisOperation);
                    Utility.writeResultsToCsv(result);
                    //ToDo Rewrite:
                    //  Utility.writeResultsToCsv(result, repetitions);
                    //delete
                    //  Utility.setResultCounter()

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
            System.out.println("Rows: " + RunSettings.numberOfRowsArrayLength +
                    " Columns: " + RunSettings.cycleNumbers.length);//numberOfColumnsCycle);
        }



        logger.info("Hayabya finished running, Exiting logback..");

        // assume SLF4J is bound to logback-classic in the current environment
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.stop();
    }
}