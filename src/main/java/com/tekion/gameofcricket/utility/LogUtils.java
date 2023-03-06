package com.tekion.gameofcricket.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMethod;

public class LogUtils {

    @Lazy
    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);

    public static void logTrace(String message) {
        logger.trace(message);
    }

    public static void logDebug(String message) {
        logger.debug(message);
    }

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logWarn(String message) {
        logger.warn(message);
    }

    public static void logError(String message) {
        logger.error(message);
    }

    public static void logApiCall(RequestMethod method, String endpoint) {
        logInfo(method.name() + " call received : http://localhost:3004" + endpoint);
    }
}
