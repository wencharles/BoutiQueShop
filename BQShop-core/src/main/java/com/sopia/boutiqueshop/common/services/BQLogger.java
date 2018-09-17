package com.sopia.boutiqueshop.common.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sopia  on  4:06 PM 18-Oct-17.
 * @project BoutiQueShop
 */
public class BQLogger {
    private Logger logger;

    public BQLogger(Class<?> clazz)
    {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public static final com.sopia.boutiqueshop.common.services.BQLogger getLogger(Class<?> clazz)
    {
        return new com.sopia.boutiqueshop.common.services.BQLogger(clazz);
    }

    public void info(String msg)
    {
        this.logger.info(msg);
    }
    public void info(String format, Object...args)
    {
        this.logger.info(format, args);
    }

    public void debug(String msg)
    {
        this.logger.debug(msg);
    }

    public void debug(String format, Object...args)
    {
        this.logger.info(format, args);
    }

    public void warn(String msg)
    {
        this.logger.info(msg);
    }

    public void warn(String format, Object...args)
    {
        this.logger.info(format, args);
    }

    public void error(String msg)
    {
        this.logger.error(msg);
    }

    public void error(String format, Object...args)
    {
        this.logger.error(format, args);
    }

    public void error(Throwable t)
    {
        this.logger.error(t.getMessage(), t);
    }

    public void error(String msg, Throwable t)
    {
        this.logger.error(msg, t);
    }
}
