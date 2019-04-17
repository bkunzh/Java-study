package lock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/** 参考https://blog.csdn.net/m0_38075425/article/details/81606504
 * Created by bkunzhang on 2019/4/16.
 */
public class ReentrantLockTest {

    private final ReentrantLock lock = new ReentrantLock();
    //创建10个线程
    public void userPrint() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {

                public void run() {
                    print();
                }
            }).start();
        }
    }

    public void print() {
        lock.lock();
        System.out.println("线程" + Thread.currentThread().getName() + "正在使用打印机");
        try {
            Thread.sleep(new Random().nextInt(3000));
            System.out.println("线程" + Thread.currentThread().getName() + "资源使用打印机");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获取等待得到同步锁的线程个数
        System.out.println("还剩 " + lock.getQueueLength() + "人要使用打印机");
        lock.unlock();
    }

    public static void main(String[] args) {
        new ReentrantLockTest().userPrint();
    }
}
