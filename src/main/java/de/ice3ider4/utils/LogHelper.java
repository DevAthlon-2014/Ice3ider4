package de.ice3ider4.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: Ice3ider4
 * Project: Devathlon
 * Date: 18.10.2014
 * Time: 11:04
 */

public class LogHelper {

    private Logger logger;

    public LogHelper(Logger logger){
        this.logger = logger;
    }

    public void logInfo(String message){
        logger.log(Level.INFO,message);
    }

    public void logWarning(String message){
        logger.log(Level.WARNING,message);
    }

    public void logSevere(String message){
        logger.log(Level.SEVERE,message);
    }

    public void logFine(String message){
        logger.log(Level.FINE,message);
    }
}
