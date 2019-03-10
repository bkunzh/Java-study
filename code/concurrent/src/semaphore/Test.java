package semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by zhbk on 2019/3/10.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(5);
//            semaphore.acquire(8);
            semaphore.tryAcquire(8);
            System.out.println("yes");
    }
}
