package org.hayabaya.classesWithMain;

import org.hayabaya.RunSettings;
import org.hayabaya.datarelated.Operation;
import org.hayabaya.datarelated.Results;
import org.hayabaya.datarelated.Utility;
import org.hayabaya.loopers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by cain on 5/28/2015.
 */
public class UseSingleton {
    public static void main(String[] args) {
        System.out.println("Hello to the HayaBaya project \n");

        RunSettings runSettings = RunSettings.getInstance();

/*        System.out.println("Using instance:" +runSettings.isUnitTesting());
        System.out.println("Using static public access: " +RunSettings.unitTesting);
        int[] anArray = RunSettings.arrayLengths;
        System.out.println("The array in RunSettings: " + Arrays.toString(anArray));

         place Loopers into a list and iterate over the list performing operations, parsing types to methods
        List<Loopers> aListOfLoopers = new ArrayList<>();


        //ToDo150522: try to change the following to use for comprehension with RunSettings.cycleNumbers and arrayLengths

        //<editor-fold desc="Initialize all Looper instances with Array_Size_Min and Cycles_Min from Runsettings">
        aListOfLoopers.add(new LoopersInt(RunSettings.ARRAY_SIZE_MIN, RunSettings.CYCLES_MIN));
        aListOfLoopers.add(new LoopersLong(runSettings.ARRAY_SIZE_MIN, runSettings.CYCLES_MIN));
        aListOfLoopers.add(new LoopersFloat(runSettings.ARRAY_SIZE_MIN, runSettings.CYCLES_MIN));
        //</editor-fold>


         The first and outermost loop only loops over the repetitions of the entire experiment
        for ( int repetitions = 0; repetitions <= RunSettings.TOTAL_EXP_REPS; repetitions++ ){

             Loops over each getType of Looper object in the LooperList
            for (Loopers aLooperInstance : aListOfLoopers) {

                 Loop over the types of operations ADD, SUBTRACT etc.
                for (Operation anOperationToUse : Operation.values()) {

                    result = aLooperInstance.makeResults(anOperationToUse);
                    Utility.writeResultsToCsv(result);


                    //<editor-fold desc="Print debug information when running Hayabaya">
                    if (runSettings.debug){
                        String loopString = aLooperInstance.toString();
                        System.out.println(loopString);
                        String resultString = result.toString();
                        System.out.println(resultString);
                    }
                    //</editor-fold>
                }
            }
            Utility.setResultCounter(repetitions);
        }*/

    }
}
