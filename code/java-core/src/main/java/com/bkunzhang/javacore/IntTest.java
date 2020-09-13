package com.bkunzhang.javacore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IntTest {
    public static void main(String[] args) {
        //整数溢出
        Integer i = Integer.MAX_VALUE;
        System.out.println(i + 1 < i); //true
        System.out.println(i + 1); //-2147483648
        System.out.println((int) (Math.pow(2, 32) - 1) == Integer.MAX_VALUE); //true

        // 无符号int最大值
//        long max_i = Integer.MAX_VALUE + Long.valueOf(Integer.MAX_VALUE) + 1;
        long max_i = Integer.MAX_VALUE;

        System.out.println(max_i);

        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        // 秒->毫秒
        System.out.println(sdf.format(new Date(max_i * 1000)));
        System.out.println(new Date().getTime());
        System.out.println();

    }
}
