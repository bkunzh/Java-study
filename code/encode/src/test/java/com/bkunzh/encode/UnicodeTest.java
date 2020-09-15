package com.bkunzh.encode;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

public class UnicodeTest {
    @Test
    public void javaUnicode() {
        String s = "中国";
        char c = '中';
        int cInt = c;
        System.out.println(cInt);

        int b16_1 = 20013%16;
        int b16_2 = 20013/16%16;
        int b16_3 = 20013/16/16%16;
        int b16_4 = 20013/16/16/16%16;
        System.out.printf("%d,%d,%d,%d\n", b16_1, b16_2, b16_3, b16_4); // 0X4E2D

        System.out.println(Integer.toString(cInt, 16));
        System.out.println(Integer.toHexString(cInt));

        System.out.println("\u4E2D"); // unicode表示

        System.out.println("---------");
        System.out.println(string2Unicode("abc"));
        System.out.println(string2Unicode("中国"));
        System.out.println("你好\u4e2d\u56fd");
        System.out.println("你好\u0061\u0062\u0063");

        System.out.println("--------");
        System.out.println(Integer.parseInt("4e2d", 16));
        System.out.println((char) Integer.parseInt("4e2d", 16));
    }

    @Test
    public void urlEn() throws UnsupportedEncodingException {
        String s = URLDecoder.decode("%E4%B8%AD", "utf8");
        System.out.println(s);
    }


    /**
     * 字符串转换unicode
     * @param string
     * @return
     */
    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

}
