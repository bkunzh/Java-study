package countdownLatch.test2;

import org.omg.CORBA.TIMEOUT;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhbk on 2019/3/9.
 */
public class ApplicationStartupUtil
{
    //List of service checkers
    private static List<BaseHealthChecker> services;

    //This latch will be used to wait on
    private static CountDownLatch latch;

    private ApplicationStartupUtil()
    {
    }

    private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();

    public static ApplicationStartupUtil getInstance()
    {
        return INSTANCE;
    }

    public static boolean checkExternalServices() throws Exception
    {
        //Initialize the latch with number of service checkers
        latch = new CountDownLatch(3);

        //All add checker in lists
        services = new ArrayList<BaseHealthChecker>();
        services.add(new NetworkHealthChecker(latch));
        services.add(new CacheHealthChecker(latch));
        services.add(new DatabaseHealthChecker(latch));

        //Start service checkers using executor framework
        Executor executor = Executors.newFixedThreadPool(services.size());

        for(final BaseHealthChecker v : services)
        {
            executor.execute(v);
        }

        //Now wait till all services are checked
        //1. 等待没有时间限制
//        latch.await();
        //以下等5s，到时间继续
        latch.await(5, TimeUnit.SECONDS);

        //Services are file and now proceed startup
        for(final BaseHealthChecker v : services)
        {
            if( ! v.isServiceUp())
            {
                return false;
            }
        }
        return true;
    }
}
