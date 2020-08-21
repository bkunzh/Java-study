package com.bkunzhang.javacore;

public class StringTest {
    public static void main(String[] args) {
        String s = new String();
        System.out.println("".equals(s));

        String a = new String("a");
        String b = new String("a");
        System.out.println(a == b);
        System.out.println(a.intern() == b.intern());
    }
}
