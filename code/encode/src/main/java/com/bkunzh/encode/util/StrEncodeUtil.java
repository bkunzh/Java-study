package com.bkunzh.encode.util;

import java.nio.charset.Charset;

/**
 * @author bingkun_zhang
 * @date 2020/8/28
 */
public class StrEncodeUtil {
    public static final String UTF8 = "utf-8";
    public static final Charset UTF8_CHARSET = Charset.forName(UTF8);

    public static byte[] getUTF8Bytes(String s) {
        return s.getBytes(UTF8_CHARSET);
    }

    public static String getUTF8Str(byte[] bytes) {
        return new String(bytes, UTF8_CHARSET);
    }
}
