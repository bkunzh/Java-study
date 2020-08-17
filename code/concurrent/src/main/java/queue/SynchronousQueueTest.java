package queue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.SynchronousQueue;

/**
 * @author bingkun_zhang
 * @date 2020/6/24
 */
public class SynchronousQueueTest {
    static Logger log = LoggerFactory.getLogger(SynchronousQueueTest.class);
    @Test
    public void synchronousQueue() {
        SynchronousQueue<Object> synchronousQueue = new SynchronousQueue<>();
        synchronousQueue.offer(new Object());
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("thread end");
                synchronousQueue.peek();
            }
        }.start();
        log.info("end");
    }

}
