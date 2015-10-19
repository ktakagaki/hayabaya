package org.hayabaya;

import ch.qos.logback.classic.Logger;
import org.hayabaya.datarelated.*;
import org.hayabaya.loopers.LooperFactory;
import org.hayabaya.loopers.Loopers;
import org.slf4j.LoggerFactory;

import java.util.*;


/**
 * Profile how fast the JVM performs arithmetic operations. The program creates 8 arrays of length <i>n</i>
 * for each of the 4 basic data types <i>int, float, double</i> and <i>long</i> and their boxed counterparts. The
 * code then performs the 4 different operations (+,-,/,*) for <i>x</i> number of times on each element of the arrays
 * and measures the runtime. Results are saved to disk in csv files
 *
 * @author Ktakagaki
 * @author Slentzen Demian
 * @version 1.0
 */
public class MainClass {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(MainClass.class);

    /**
     * When run from the command line, Hayabaya takes 3 arguments.
     * <pre>
     *     $java -jar NameOfProcessor SizeOfTestData TotalRepetitions
     *     $java -jar AMDA4 small 3
     * </pre>
     * The arguments are as follows:
     *     <ul>
     *         <li><b>NameOfProcessor</b> To distinguish between results from different systems, the name of the CPU
     *         should be included
     *         <li><b>SizeOfTestData</b> There are 3 preset configurations for convenience, <b>small, medium</b> and
     *         <b>large</b>
     *         <li><b>TotalRepetitions</b> How many times to replicate the entire experiment
     *     </ul>
     *
     * Once Main has verified the values from the command line arguments given, it will pass them on to
     * the {@link RunSettings Runsettings} class which stores project settings.
     *
     * @throws IllegalArgumentException Throws error if 3 arguments are not given on the command line
     * @param args Arguments specifying the name of the CPU being tested, the size of the experiment to run and the
     */
    public static void main(String[] args) {
        RunSettings runSettingsInstance = RunSettings.getRunSettingsInstance();
        logger.debug("Args are: {}", Arrays.toString(args));


        //Validate arguments and then parse them to RunSettings so system is ready to go
        try {
            Utility.validateArgsValues(args);
            Utility.validateArgsLength(args);

//            String sampleSize = "large";
            String sampleSize = "small";
            int repetitions = 3;
            String name = args[0];

            runSettingsInstance.setNameOfProcessor(name);
            runSettingsInstance.setSampleSize(sampleSize);
            runSettingsInstance.setTotalExperimentRepetitions(repetitions);

        } catch (IllegalArgumentException e) {
            logger.error("IllegalArguments {}", e);
            e.printStackTrace();
        }



        List<Loopers> arrayListOfLoopers = new ArrayList<>();

        for (Tpe datatype : Tpe.values()) {// Add a Loopers instance to the arrayList for each value in Tpe

            try {
                arrayListOfLoopers.add(LooperFactory.getLooper(datatype));
            } catch (IllegalArgumentException e) {
                logger.error("No such Looper type: {}", e);
                e.printStackTrace();
            }
        }


        for (Loopers anInstance : arrayListOfLoopers) {

            logger.info("Using Looper type: {}", anInstance);
            for (Operation anOperation : Operation.values()) {

                logger.info("Using operation type: {}", anOperation);

                Results[] opResults = anInstance.makeResults(anOperation);

                logger.info("Results[] length: {}", opResults.length);
                System.out.println("Writing " + anInstance + " operation " + anOperation + "to disk");
                writeResultsArray(opResults);
            }
        }


        logger.info("Trying to dump JVM values");
        WriteResults.writeJVMValuesToDisk();
        logger.info("Exiting Hayabaya");

        System.exit(0);
    }

    private static void writeResultsArray(Results[] rArray){
        for (int i = 0; i<rArray.length; i++){
            System.out.println("Results["+i+"] Filename: " +rArray[i].getFileName());
            WriteResults.writeResultsV2(rArray[i]);
        }
    }

}