package semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhbk on 2019/3/10.
 * 是否阻塞
 */
public class TestTryAcquire {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(5);
//        Semaphore semaphore = new Semaphore(8);

        //1
//        semaphore.acquire(8); //一直阻塞
        boolean b = false;
        //2
//          b = semaphore.tryAcquire(8); //立即返回
        //3
        b = semaphore.tryAcquire(8, 5, TimeUnit.SECONDS); //最多阻塞5秒
        System.out.println(b);
    }
}
