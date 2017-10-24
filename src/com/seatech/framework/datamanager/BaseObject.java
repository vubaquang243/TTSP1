package com.seatech.framework.datamanager;

import java.lang.reflect.Field;


//import org.apache.log4j.Logger;

/**
 * Base class of many objects utilized and observed by the Framework.
 * Provides capabilities for logging.
 */
public class BaseObject extends Object {
    //************************************************************************
    // Public Methods
    //************************************************************************

    /**
     * Constructor
     */
    public BaseObject() {
        super();
    }

    /**
     * Prints the message using the log4j.xml settings.
     * Around for backwards capatability.  Use logMessage( String, FramemworkLogEventType ) instead.
     *
     * @param  msg(String)
     */
    public void printMessage(String msg) {
//        logMessage(msg);
    }


    /**
     * Prints the message to Log4j.
     * Use logMessage(String, FrameworkLogEventType) instead.
     *
     * @param   msg(String)
     */
    static public void printTheMessage(String msg) {
//        logMessage(msg);
    }

    /**
     * Logs the message to Log4j as info
     * @param    msg(String)
     */
    static public void logMessage(String msg) {
        logMessage(msg, LogEventType.INFO_LOG_EVENT_TYPE);
    }

    static public void logInfoMessage(String msg) {
        logMessage(msg, LogEventType.INFO_LOG_EVENT_TYPE);
    }

    static public void logWarnMessage(String msg) {
        logMessage(msg, LogEventType.WARNING_LOG_EVENT_TYPE);
    }

    static public void logErrorMessage(String msg) {
        logMessage(msg, LogEventType.ERROR_LOG_EVENT_TYPE);
    }

    static public void logDebugMessage(String msg) {
        logMessage(msg, LogEventType.DEBUG_LOG_EVENT_TYPE);
    }

    /**
     * Logs the message to Log4J, either as  a debug, info, warn, or  error.
     *
     * @param msg
     * @param logEventType
     */
    static public void logMessage(String msg, LogEventType logEventType) {
        if (log4JConfigured == false) {

            //            // attempt to load the Log4J default configuration file that should be located
            //            // in the framework home directory
            //            try
            //            {
            //                new DOMConfigurator().doConfigure( FrameworkNameSpace.LOG4J_INPUT_STREAM, LogManager.getLoggerRepository() );
            //                log4JConfigured = true;
            //            }
            //            catch( Throwable exc )
            //            {
            //                System.out.println( "Failed to load Log4J configuration file: " + FrameworkNameSpace.DEFAULT_LOG4J_CONFIGURATION_FILE + " : " + exc );
            //            }
        }

//        if (log4JConfigured == true) {
//            Logger logger = Logger.getLogger("TTSP");          
//            if (logger != null) {
//                if (logger.isDebugEnabled()) {
//                    if (logEventType.isInfoLogEventType())                        
//                        logger.info(msg);
//                    else if (logEventType.isDebugLogEventType())
//                        logger.debug(msg);
//                    else if (logEventType.isErrorLogEventType())
//                        logger.error(msg);
//                    else if (logEventType.isWarningLogEventType())
//                        logger.warn(msg);
//                    else
//                        System.out.println(msg);
//                }
//            } else
//                // perhaps an older version of log4j is in the classpath....
//                System.out.println(msg);
//        } else {
//            System.out.println(msg);
//        }
    }

    /**
     * Handles error related message logging.
     *
     * @deprecated
     *
     * @param    msg(String)
     */
    public void enterErrorLogMsg(String msg) {
        // delegate internally
        printMessage(msg);
    }

    /**
     * Retrieves the value of the passed in field.
     * This method is needed to retrieve protected and private
     * field instance values.
     *
     * @param field
     * @return Object
     * @exception IllegalAccessException
     * @exception IllegalArgumentException
     * @exception NullPointerException
     */
    protected Object getFieldValue(Field field) throws IllegalAccessException,
                                                       IllegalArgumentException,
                                                       NullPointerException {
        return field.get(this);
    }


    /**
     * log4j configured indicator
     */
    private static boolean log4JConfigured = false;
}
