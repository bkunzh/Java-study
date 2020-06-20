import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author bingkun_zhang
 * @date 2020/6/20
 */
// 单元测试执行多次，多线程每一次都可能不一样
@RunWith(Parameterized.class)
public class UnitTestManyTime {
    ExecutorService executorService = Executors.newFixedThreadPool(8);

    // 单元测试执行多次
    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[10][0]; // repeat count which you want
    }

    @Before
    public void before() {
        System.out.println("before");
    }
    @Test
    public void multiThread() throws InterruptedException {
        final int N = 20;
        final CountDownLatch latch = new CountDownLatch(N);
        for (int i=0; i<20; ++i) {
            final int k = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(k);
                    latch.countDown();
                }
            });
        }
        latch.await();
    }
    @After
    public void after() {
        System.out.println("after");
    }
}
