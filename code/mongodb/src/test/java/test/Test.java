package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bingkun_zhang
 * @date 2020/7/3
 */
public class Test {
    static Logger log = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) {
        log.debug("debug 11");
        log.info("info 11");
    }
}
