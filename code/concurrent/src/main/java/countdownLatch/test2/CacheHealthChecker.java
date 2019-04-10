package countdownLatch.test2;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhbk on 2019/3/9.
 */
public class CacheHealthChecker extends BaseHealthChecker {
    public CacheHealthChecker (CountDownLatch latch)  {
        super("Cache Service", latch);
    }

    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
