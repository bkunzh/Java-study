package com.bkunzh.javastudy.log;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.FileAppender;
import com.bkunzh.constant.LogConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Iterator;

/**
 * @author bkunzh
 * @date 2020/10/11
 */
@Slf4j
public class LogTest {
    private static Logger consoleLog = LoggerFactory.getLogger(LogConstants.CONSOLE_LOG_NAME);
    public static void main(String[] args) {
        // 当前根目录
        //.getPath() 在jar包中运行会报NullPointerException
//        consoleLog.debug("当前根目录={}", Thread.currentThread().getContextClassLoader().getResource(".").getPath());
        consoleLog.debug("当前根目录={}", Thread.currentThread().getContextClassLoader().getResource("."));
        log.info("info msg");
        log.debug("de msg");
        consoleLog.info("i");
        consoleLog.debug("j");
        printLogbackLogFilePath();
        consoleLog.debug("旧电脑预热中，写2小时日志试试，这占不了多少cpu，主要是磁盘预热");
        testLogMany();
        consoleLog.info("结束");
    }

    static void printLogbackLogFilePath() {
        try {
            LoggerContext context = (LoggerContext)LoggerFactory.getILoggerFactory();
            for (ch.qos.logback.classic.Logger logger : context.getLoggerList()) {
                for (Iterator<Appender<ILoggingEvent>> index = logger.iteratorForAppenders(); index.hasNext();) {
                    Appender<ILoggingEvent> appender = index.next();
                    if(appender instanceof FileAppender){
                        FileAppender fileAppender = (FileAppender) appender;
                        File file = new File(fileAppender.getFile());
                        String canonicalPath = file.getCanonicalPath();
                        consoleLog.debug("canonicalPath={}", canonicalPath);
                        consoleLog.debug("path={}", file.getPath());
                        consoleLog.debug("absolutePath={}", file.getAbsolutePath());
                    }
                }
            }
        } catch (Exception e) {
            log.error("printLogbackLogFilePath error ", e);
        }
    }

    /**
     * todo 打印2小时日志，看看旧电脑的性能
     */
    static void testLogMany() {
        long startTime = System.currentTimeMillis();
        final long TIME = 1000 * 60 * 60 * 2;
        final int N = 100 * 1000; // 每写10w次日志控制台打印下
        long count = 0;
        while (System.currentTimeMillis() - startTime < TIME) {
            ++count;
            log.debug(LOG_STR);
            if (count % N == 0) {
                consoleLog.info("打印了{}次日志", count);
            }
            try {
                Thread.sleep(5); // 1秒200次，2小时：200*60*60*2=1,440,000
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static String LOG_STR = "Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed systems (e.g. configuration management, service discovery, circuit breakers, intelligent routing, micro-proxy, control bus, one-time tokens, global locks, leadership election, distributed sessions, cluster state). Coordination of distributed systems leads to boiler plate patterns, and using Spring Cloud developers can quickly stand up services and applications that implement those patterns. They will work well in any distributed environment, including the developer’s own laptop, bare metal data centres, and managed platforms such as Cloud Foundry.";
}
