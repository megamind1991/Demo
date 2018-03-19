package com.me.ga.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggersUtil {

    public static void error(Class<?> clazz, Throwable t) {

        final Logger logger = LogManager.getLogger(clazz);
        logger.error("Unexpected error has occurred: {}", t.getMessage(), t);
    }

    public static void error(Class<?> clazz, String message, Throwable t) {

        final Logger logger = LogManager.getLogger(clazz);
        logger.error("{} {}", message, t);
    }

    public static void debug(Class<?> clazz, String msg, Throwable t) {

        final Logger logger = LogManager.getLogger(clazz);
        if (logger.isDebugEnabled()) {
            logger.debug(msg, t);
        }
    }

    public static void debug(Class<?> clazz, String msg, Object... args) {

        final Logger logger = LogManager.getLogger(clazz);
        if (logger.isDebugEnabled()) {
            logger.debug(msg, args);
        }
    }

    public static void trace(Class<?> clazz, String msg, Throwable t) {

        final Logger logger = LogManager.getLogger(clazz);
        if (logger.isTraceEnabled()) {
            logger.trace(msg, t);
        }

    }

    public static void trace(Class<?> clazz, String msg, Object... args) {

        final Logger logger = LogManager.getLogger(clazz);
        if (logger.isTraceEnabled()) {
            logger.trace(msg, args);
        }

    }

    public static void info(Class<?> clazz, String message) {

        final Logger logger = LogManager.getLogger(clazz);
        if (logger.isInfoEnabled())
            logger.info(message);
    }

    public static void info(Class<?> clazz, String message, Object... args) {

        final Logger logger = LogManager.getLogger(clazz);
        if (logger.isInfoEnabled())
            logger.info(message, args);
    }

    public static void warning(Class<?> clazz, String message, Object... args) {

        final Logger logger = LogManager.getLogger(clazz);
        if (logger.isWarnEnabled())
            logger.warn(message, args);
    }
    
    public static void error(String name, Throwable t) {

        final Logger logger = LogManager.getLogger(name);
        logger.error("Unexpected error has occurred: {}", t.getMessage(), t);
    }

    public static void error(String name, String message, Throwable t) {

        final Logger logger = LogManager.getLogger(name);
        logger.error("{} {}", message, t);
    }

    public static void debug(String name, String msg, Throwable t) {

        final Logger logger = LogManager.getLogger(name);
        if (logger.isDebugEnabled()) {
            logger.debug(msg, t);
        }
    }

    public static void debug(String name, String msg, Object... args) {

        final Logger logger = LogManager.getLogger(name);
        if (logger.isDebugEnabled()) {
            logger.debug(msg, args);
        }
    }

    public static void trace(String name, String msg, Throwable t) {

        final Logger logger = LogManager.getLogger(name);
        if (logger.isTraceEnabled()) {
            logger.trace(msg, t);
        }

    }

    public static void trace(String name, String msg, Object... args) {

        final Logger logger = LogManager.getLogger(name);
        if (logger.isTraceEnabled()) {
            logger.trace(msg, args);
        }

    }

    public static void info(String name, String message) {

        final Logger logger = LogManager.getLogger(name);
        if (logger.isInfoEnabled())
            logger.info(message);
    }

    public static void info(String name, String message, Object... args) {

        final Logger logger = LogManager.getLogger(name);
        if (logger.isInfoEnabled())
            logger.info(message, args);
    }

    public static void warning(String name, String message, Object... args) {

        final Logger logger = LogManager.getLogger(name);
        if (logger.isWarnEnabled())
            logger.warn(message, args);
    }
}