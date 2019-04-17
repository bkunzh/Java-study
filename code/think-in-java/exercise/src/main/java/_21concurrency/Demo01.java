package _21concurrency;

import java.util.Iterator;


/**
 * https://github.com/wpde/ThinkingInJava/blob/master/src/_21concurrency/Demo01.java
 * Created by bkunzhang on 2019/4/18.
 */
public class Demo01 {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(new T1()).start();
        }
    }
}

class T1 implements Runnable{



    public T1() {
        super();
        System.out.println("创建了一个T1类");
    }

    @Override
    public void run() {
        for(int i=0;i<3;i++) {
            System.out.println(Thread.currentThread().getName());
            Thread.yield(); //通知线程调度器将cpu让给其它线程执行
        }
        System.out.println(Thread.currentThread().getName()+"任务结束");
        return;
    }

}