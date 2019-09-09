import org.junit.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by bkunzhang on 2019/9/9.
 */
public class TestWaitAndNotifyAll {

    static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    final static int N = 5;
    static AtomicInteger atomicInteger = new AtomicInteger(N);

    //sleep不释放锁。notify要在wait之后才能唤醒它，所以这里用了sleep，保证在wait之后
    //AtomicInteger
    //起一个线程来唤醒主线程（达到一定条件，前面循环即可）
    @Test
    public void testWaitAndNotifyAll() throws InterruptedException {
        HelloService helloService = new HelloService();
        for (int i = 0; i < N; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    synchronized (helloService) {
                        helloService.sayHello();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(1000L);


        synchronized (helloService) {
            System.out.println("---------main 1-----------");
            helloService.notifyAll(); //要在5个线程之后。所以前面sleep
        }

        //起一个线程来唤醒主线程(5个线程执行完了才继续main 2)
        threadPoolExecutor.execute(() -> {
            //循环直到条件满足
            while(atomicInteger.get() > 0) {
            }
            System.out.println("atomicInteger is 0, continue");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (helloService) {
                System.out.println("notify main thread get lock");
                helloService.notifyAll(); //这里要在main wait之后执行，才能唤醒。所以前面sleep
            }
        });

        //主线程等待
        synchronized (helloService) {
            helloService.wait();
            System.out.println("after main wait");
        }

        System.out.println("---------main 2-----------");

    }
}
