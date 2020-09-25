package com.bkunzhang.javacore;

import java.util.ArrayList;
import java.util.List;
/*
1、finally中的代码总会被执行。
2、当try、catch中有return时，也会执行finally。return的时候，要注意返回值的类型，是否受到finally中代码的影响。
3、finally中有return时，会直接在finally中退出，导致try、catch中的return失效。

https://www.cnblogs.com/pcheng/p/10968841.html
 */
public class FinallyReturnTest {

    private static int testReturn1() {
        int i = 1;
        try {
            i++;
            System.out.println("try:" + i);
            // finally中没有return
            // 因为当try中带有return时，会先执行return前的代码，然后暂时保存需要return的信息，
            // 再执行finally中的代码，最后再通过return返回之前保存的信息。
            return i;
        } catch (Exception e) {
            i++;
            System.out.println("catch:" + i);
        } finally {
            i++;
            System.out.println("finally:" + i);
        }
        return i;
    }

    // 问题出在参数类型上，上一个例子用的是基本类型，这里用的引用类型。
    // list里存的不是变量本身，而是变量的地址，所以当finally通过地址改变了变量，还是会影响方法返回值的。
    private static List<Integer> testReturn2() {
        List<Integer> list = new ArrayList<>();
        try {
            list.add(1);
            System.out.println("try:" + list);
            return list;
        } catch (Exception e) {
            list.add(2);
            System.out.println("catch:" + list);
        } finally {
            list.add(3);
            System.out.println("finally:" + list);
        }
        return list;
    }

    // catch中return与try中一样，会先执行return前的代码，
    // 然后暂时保存需要return的信息，再执行finally中的代码，最后再通过return返回之前保存的信息。
    // 所以，这里方法返回的值是try、catch中累积计算后的3，而非finally中计算后的4。
    private static int testReturn3() {
        int i = 1;
        try {
            i++;
            System.out.println("try:" + i);
            int x = i / 0 ;
        } catch (Exception e) {
            i++;
            System.out.println("catch:" + i);
            return i;
        } finally {
            i++;
            System.out.println("finally:" + i);
        }
        return i;
    }

    // 　当finally中有return的时候，try中的return会失效，在执行完finally的return之后，
    // 就不会再执行try中的return。这种写法，编译是可以编译通过的，
    // 但是编译器会给予警告，所以不推荐在finally中写return，这会破坏程序的完整性，而且一旦finally里出现异常，会导致catch中的异常被覆盖。
    private static int testReturn4() {
        int i = 1;
        try {
            i++;
            System.out.println("try:" + i);
            return i;
        } catch (Exception e) {
            i++;
            System.out.println("catch:" + i);
            return i;
        } finally {
            i++;
            System.out.println("finally:" + i);
            return i;
        }
    }

    // return finally，finally中有return
    public static int inallyReturn() {
        try {
            System.out.println("a");
            return 5;
        } finally {
            System.out.println("b");
            return 2;
        }
    }

    public static int inallyReturn2() {
        try {
            System.out.println("a");
            int a = 5 / 0;
            return 5;
        } catch (Exception e) {
            System.out.println(e.getMessage());
          return 3;
        } finally {
            System.out.println("b");
            return 2;
        }
    }



    public static void main(String[] args) {
//        System.out.println("main1: " + inallyReturn());
//        System.out.println("main2: " + inallyReturn2());
        System.out.println("main3:" + testReturn1());
        System.out.println("main4:" + testReturn2());
        System.out.println("main5:" + testReturn3());
        System.out.println("main6:" + testReturn4());
    }
}
