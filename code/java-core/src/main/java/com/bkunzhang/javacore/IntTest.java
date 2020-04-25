package com.bkunzhang.javacore;

public class IntTest {
    public static void main(String[] args) {
        //整数溢出
        Integer i = Integer.MAX_VALUE;
        System.out.println(i + 1 < i); //true
        System.out.println(i + 1); //-2147483648
        System.out.println((int) (Math.pow(2, 32) - 1) == Integer.MAX_VALUE); //true

    }
}
