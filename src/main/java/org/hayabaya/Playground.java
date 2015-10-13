package org.hayabaya;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by cain on 21/09/15.
 */
public class Playground {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Playground.class);
    public static void main(String[] args) {
        try {
            throw new IllegalArgumentException("Wrong argument");
        } catch (IllegalArgumentException e) {
            System.out.println("In the catch part now");
            logger.error("Error: {}", e.getMessage());
//            e.printStackTrace();
//            e.printStackTrace();
//            String estr = e.getMessage();
//            System.out.println("Using logger.error with getMessage" + estr);
//            logger.error(e.getMessage(), e.getStackTrace(), e);
        }
    }
}
