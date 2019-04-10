package countdownLatch.test2;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhbk on 2019/3/9.
 */
public abstract class BaseHealthChecker implements Runnable {

    private CountDownLatch latch;
    private String serviceName;
    private boolean serviceUp;

    //Get latch object in constructor so that after completing the task, thread can countDown() the latch
    public BaseHealthChecker(String serviceName, CountDownLatch latch)
    {
        super();
        this.latch = latch;
        this.serviceName = serviceName;
        this.serviceUp = false;
    }

    @Override
    public void run() {
        try {
            verifyService();
            serviceUp = true;
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            serviceUp = false;
        } finally {
            if(latch != null) {
                latch.countDown();
            }
        }
    }

    public String getServiceName() {
        return serviceName;
    }

    public boolean isServiceUp() {
        return serviceUp;
    }
    //This methos needs to be implemented by all specific service checker
    public abstract void verifyService();
}
