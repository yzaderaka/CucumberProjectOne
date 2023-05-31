package Utils;

import org.apache.log4j.*;

public class Log {

    public static Logger log = Logger.getLogger(Log.class.getName());

    // when the test starts, i should print the logs
    // when my tests stops, i should print the logs
    // if i want to print any message in between, i should print the logs

    public static void startTestCase(String testCaseName) {
        log.info("**********************");
        log.info("**********************");
        log.info("********" + testCaseName + "********");
    }
    public static void endTestCase(String testCaseName){
        log.info("#######################");
        log.info("#######################");
        log.info("########"+testCaseName+"########");
    }
    public static void info(String message){
        log.info(message);
    }
    public static void warning(String warning){
        log.info(warning);
    }
}
