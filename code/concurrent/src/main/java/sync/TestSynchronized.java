package sync;

import java.util.Random;

/**
 * Created by bkunzhang on 2019/4/16.
 */
public class TestSynchronized {

    static int a;
    int b;

    //非同步
    static void f0() {
        System.out.println(commonInfo("f0") + "方法开始");
        System.out.println(commonInfo("f0") + "对a进行操作");
        int b = a;
        try {
            Thread.sleep(new Random().nextInt(300));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b = b + 5;
        a = b;
        System.out.println(commonInfo("f0") + "a=" + a);
        System.out.println(commonInfo("f0") + "方法完毕");
    }

    //同步
    static synchronized void f1() {
        System.out.println(commonInfo("f1") + "方法开始");
        System.out.println(commonInfo("f1") + "对a进行操作");
        int b = a;
        try {
            Thread.sleep(new Random().nextInt(300));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b = b + 5;
        a = b;
        System.out.println(commonInfo("f1") + "a=" + a);
        System.out.println(commonInfo("f1") + "方法完毕");
    }

    //同步块
    static void f2() {
        System.out.println(commonInfo("f2") + "方法开始");
        synchronized (String.class) {
            System.out.println(commonInfo("f2") + "对a进行操作");
            int b = a;
            try {
                Thread.sleep(new Random().nextInt(300));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b = b + 5;
            a = b;
        }
        System.out.println(commonInfo("f2") + "a=" + a);
        System.out.println(commonInfo("f2") + "方法完毕");
    }

    static String commonInfo(String functionName) {
        return "方法" + functionName + "():线程" + Thread.currentThread().getName() + ": ";
    }

    public static void main(String[] args) {
        for (int i=0; i<10; ++i) {
            new Thread(() -> {
//                f0();
//                f1();
                f2();
            }).start();
        }

        //1
        System.out.println("main() ***");

        //2
        //有这一句，则打印main也要竞争锁
        // 临界区
//        synchronized (String.class) {
//            System.out.println("main() ***");
//        }

        System.out.println("main() end");

    }

}
