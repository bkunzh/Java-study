package juc;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author bingkun_zhang
 * @date 2020/6/10
 */

public class VolatileTest {

    private final static ExecutorService executorService = Executors.newFixedThreadPool(6);


//    int a = 5;
    volatile int a = 5;
    //volatile int 判断值变化来结束阻塞, 不要volatile不行
    @Test
    public void volatileTest() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 6;
            }
        });
        long start = System.currentTimeMillis();
        while (a == 5 && (System.currentTimeMillis() - start < 5000)) {
        }
        System.out.println("时间:" + (System.currentTimeMillis() - start));
        System.out.println("out");
    }

}
