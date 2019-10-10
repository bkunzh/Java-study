package com.bkunzhang.javacore;

import org.junit.Test;

/**
 * @author bkunzhang
 * @date 2019/10/11
 *
 * 用位运算来代替求模% (用&，^不行)
 *
 * 由于我们知道位运算比较高效，在某些情况下，当b为2的n次方时，有如下替换公式：
 * a % b = a & (b-1)(当b=pow(2, n))
 * 即：a % pow(2, n) = a & (pow(2, n)-1)
 * 参考 https://blog.csdn.net/lonyw/article/details/80519652
 */
public class ModTest {

    //当size为2的幂时，n & (size-1)等价于n % size
    @Test
    public void and() {
        int size = 2;
        System.out.println(15 % size);
        System.out.println(15 & (size-1));
        System.out.println("---");

        size = 4;
        System.out.println(15 % size);
        System.out.println(15 & (size-1));
        System.out.println("---");

        size = 6;
        System.out.println(15 % size);
        System.out.println(15 & (size-1));
        System.out.println("---");
    }

    @Test
    public void mod() {
        System.out.println(15 ^ 2);
        System.out.println(5 % 2);
        System.out.println(5 ^ 2);
        System.out.println(7 % 2);
        System.out.println(-5 % 2);
        System.out.println(-5 ^ 2);
        System.out.println("-----");
        System.out.println(5.3 % 3);
        System.out.println(-5.3 % 3);
        System.out.println("-----");
        System.out.println(3 % 2.3);
    }

    //异或
    @Test
    public void xor() {
        //逻辑异或
        System.out.println(true ^ false);
        System.out.println(true ^ true);
        System.out.println(false ^ true);
        System.out.println(false ^false);

        //按位异或
        int a = 2;
        int b = 5;
        System.out.println("--------");
        System.out.println(a ^ b);
        System.out.println(b ^ a);
        System.out.println("--------");
        a = 7;
        System.out.println(a ^ b);
        System.out.println(b ^ a);
        System.out.println("--------");
        a = 12;
        System.out.println(a ^ b);
        System.out.println(b ^ a);

    }
}
