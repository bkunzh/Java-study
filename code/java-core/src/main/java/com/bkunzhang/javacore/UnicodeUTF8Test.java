package com.bkunzhang.javacore;

import java.io.UnsupportedEncodingException;

/**
 * Created by zhbk on 2017/12/12.
 */
public class UnicodeUTF8Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        t1();
        System.out.println("---------");
        t2();
        System.out.println("\u4e2d");
    }

    static void t1() throws UnsupportedEncodingException {
        System.out.println("a(Unicode)    ：" + "a".getBytes("Unicode").length);
        System.out.println("aa(Unicode)    ：" + "aa".getBytes("Unicode").length);
        System.out.println("啊(Unicode)   ：" + "啊".getBytes("Unicode").length);
        System.out.println("啊啊(Unicode) ：" + "啊啊".getBytes("Unicode").length);
        System.out.println("");
        System.out.println("a(UTF-8)    ：" + "a".getBytes("UTF-8").length);
        System.out.println("aa(UTF-8)   ：" + "aa".getBytes("UTF-8").length);
        System.out.println("啊(UTF-8)   ：" + "啊".getBytes("UTF-8").length);
        System.out.println("啊啊(UTF-8) ：" + "啊啊".getBytes("UTF-8").length);
        System.out.println("");
        System.out.println("a(UTF-16)    ：" + "a".getBytes("UTF-16").length);
        System.out.println("aa(UTF-16)   ：" + "aa".getBytes("UTF-16").length);
        System.out.println("啊(UTF-16)   ：" + "啊".getBytes("UTF-16").length);
        System.out.println("啊啊(UTF-16) ：" + "啊啊".getBytes("UTF-16").length);
    }

    static void t2() throws UnsupportedEncodingException {
        String str = "中国";
        String[] encoding = {"Unicode", "UTF-8", "utf8", "UnicodeBig", "UnicodeLittle", "UnicodeBigUnmarked",
                "UnicodeLittleUnmarked", "UTF-16", "UTF-16BE", "UTF-16LE"};

        for (int i = 0; i < encoding.length; i++) {
            System.out.printf("%-22s %s%n", encoding[i], bytes2HexString(str.getBytes(encoding[i])));
        }
    }

    private final static char[] HEX = "0123456789abcdef".toCharArray();

    public static String bytes2HexString(byte[] bys) {
        char[] chs = new char[bys.length * 2 + bys.length - 1];
        //byte转换为十六进制
        for (int i = 0, offset = 0; i < bys.length; i++) {
            if (i > 0) {
                chs[offset++] = ' ';
            }
//            chs[offset++] = HEX[bys[i] >> 4 & 0xf];
//            chs[offset++] = HEX[bys[i] & 0xf];
            byte b = bys[i];
            chs[offset + 1] = HEX[b & 0xf];
            b >>= 4;
            chs[offset] = HEX[b & 0xf];
            offset += 2;
        }

//        for (int i=0, offset=0; i<bys.length; ++i) {
//            if (i > 0) {
//                chs[offset++] = ' ';
//            }
//            String byteBits = Integer.toBinaryString(bys[i]);
//            String byteHexs = Integer.toHexString(bys[i]);
//
//            if (byteHexs == null || byteHexs.length() != 2) {
//                throw new RuntimeException("异常：byte类型数转换为16进制数不是两位");
//            }
//            chs[offset++] = byteHexs.charAt(0);
//            chs[offset++] = byteHexs.charAt(1);
//        }
        return new String(chs);
    }
}
