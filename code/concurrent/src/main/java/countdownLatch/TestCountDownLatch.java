package countdownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 参考 https://www.cnblogs.com/dolphin0520/p/3920397.html
 */
public class TestCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        final int N = 2;
        CountDownLatch latch = new CountDownLatch(N);

        for (int i=0; i<N; ++i) {
            new Thread(() -> {
                try {
                    System.out.println("thread " + Thread.currentThread().getName() + " 正在执行");
                    Thread.sleep(3000);
                    System.out.println("thread " + Thread.currentThread().getName() + " 执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //计数器-1
                    latch.countDown();
                }
            }).start();
        }

        System.out.println("等待" + N + "个子线程执行完毕...");

        //阻塞当前线程，等待计数器为0，再继续向下
        latch.await();
        System.out.println(N + "个子线程执行完毕");
        System.out.println("继续执行主线程");

    }
}
