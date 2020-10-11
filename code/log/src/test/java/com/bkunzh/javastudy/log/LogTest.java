package com.bkunzh.javastudy.log;

import com.bkunzh.constant.LogConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bkunzh
 * @date 2020/10/11
 */
@Slf4j
public class LogTest {
    private static Logger consoleLog = LoggerFactory.getLogger(LogConstants.CONSOLE_LOG_NAME);
    public static void main(String[] args) {
        // 当前根目录
        consoleLog.debug("当前根目录={}", Thread.currentThread().getContextClassLoader().getResource("").getPath());
        log.info("info msg");
        log.debug("de msg");
        consoleLog.info("i");
        consoleLog.debug("j");
    }
}
