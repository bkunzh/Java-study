package com.bkunzh.encode;

import com.bkunzh.encode.util.StrEncodeUtil;
import org.apache.commons.net.util.Base64;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

/**
 * @author bingkun_zhang
 * @date 2020/8/28
 */
public class Base64Test {
    static Logger logger = LoggerFactory.getLogger(Base64Test.class);

    @Test
    public void t1() throws UnsupportedEncodingException {
        String s = "13aaaaa22434234a";

        java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
        String enStr2 = new String(encoder.encode(s.getBytes("utf8")));
        System.out.println(enStr2);

        java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
        System.out.println(new String(decoder.decode(enStr2), "utf8"));;
    }

    // 多次base64
    @Test
    public void t2() {
        String s = "ajku78234";
        Base64 base64 = new Base64();
        String enStr1 = base64.encodeToString(s.getBytes());
       logger.info(enStr1);
        String enStr2 = base64.encodeToString(enStr1.getBytes());
       logger.info(enStr2);

       logger.info(new String(base64.decode(enStr2)));
       logger.info(new String(base64.decode(base64.decode(enStr2))));

        java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
        java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();

        String enStr3 = new String(encoder.encode(StrEncodeUtil.getUTF8Bytes(s)));
        String s1 = new String(decoder.decode(enStr3));
       logger.info(s1);
       assertEquals(s, s1);
        assertEquals(enStr3, enStr1.trim());
        String enStr4 = new String(encoder.encode(StrEncodeUtil.getUTF8Bytes(enStr3)));
       logger.info(enStr4);
//        assertEquals(enStr4, enStr2); // enStr1多个换行，所以不相等，但解码是一样的

        
        String s2 = new String(decoder.decode(decoder.decode(enStr4)));
        assertEquals(s, s2);
    }
}
