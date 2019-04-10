package countdownLatch.test2;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhbk on 2019/3/9.
 */
public class NetworkHealthChecker extends BaseHealthChecker {
    public NetworkHealthChecker (CountDownLatch latch)  {
        super("Network Service", latch);
    }

    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(7000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
