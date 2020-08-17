package com.bkunzhang.javacore;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author bingkun_zhang
 * @date 2020/8/17
 */
public class ByteArrString {
    static Logger logger = LoggerFactory.getLogger(ByteArrString.class);

    @Test
    public void t1() throws UnsupportedEncodingException {
        String s = "广";
        byte[] bs = s.getBytes(); // 3个字节
        logger.info(Arrays.toString(bs));

        logger.info(Charset.defaultCharset().name());
        try {
            bs = s.getBytes("UTF-8"); // 和上面一样，默认就是utf-8，3个字节
            logger.info(Arrays.toString(bs));

            logger.info(new String(bs));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // unicode
//        bs = s.getBytes("UNICODE");
        s = "播";
        bs = s.getBytes("UTF-16LE");
//        bs = s.getBytes("UTF-16BE");
        logger.info(Arrays.toString(bs)); // UTF-16LE UTF-16BE 转成的字节数组顺序不一样

    }

    @Test
    public void t2() {
        final String s = "播";
        byte[] bs = s.getBytes();
        System.out.println(Arrays.toString(bs));
    }

    @Test
    public void t3() throws UnsupportedEncodingException {
        String s = "a";
        byte[] bs = s.getBytes();
        System.out.println(Arrays.toString(bs));

//            bs = s.getBytes("UNICODE");
        bs = s.getBytes("UTF-16LE");
//        bs = s.getBytes("UTF-16BE");
        logger.info(Arrays.toString(bs)); // UTF-16LE UTF-16BE 转成的字节数组顺序不一样
    }
}
