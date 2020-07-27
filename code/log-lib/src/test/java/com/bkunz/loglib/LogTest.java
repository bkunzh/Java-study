package com.bkunz.loglib;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bingkun_zhang
 * @date 2020/7/27
 */
public class LogTest {
    @Test
    public void t1() {
        Logger logger = LoggerFactory.getLogger(Object.class);
        logger.info("log info t");
        logger.error("log error t");
    }
}
