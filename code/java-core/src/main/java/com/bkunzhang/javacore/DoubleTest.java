package com.bkunzhang.javacore;

public class DoubleTest {
    public static void main(String[] args) {
        // double精度丢失
        System.out.println(1.0 - 0.8);
        System.out.println(0.2 + 0.1);
        System.out.println(1.0 - 0.8 == 0.2); //false
        System.out.println(0.2 + 0.1 == 0.3); //false

        //Double.NaN与任何数(包括自己)比较都为false，与js的NaN一样
        double a = Double.NaN;
        double b= a;
        double c = 1;
        System.out.println(a == b); //false
        System.out.println(a == a); //false
        System.out.println(a == Double.NaN); //false
        System.out.println(a > b || a <= b); //false
        System.out.println(a > c || a <= c); //false
        System.out.println(Double.NaN == Double.NaN); //false
        System.out.println(Double.isNaN(0.0 / 0.0)); //NaN

        System.out.println(0.0 / 0.0); //NaN
        System.out.println(0 / 0); //ArithmeticException: / by zero
    }
}
