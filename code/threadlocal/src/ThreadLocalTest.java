/**
 * Created by zhbk on 2019/3/21.
 * 改自 https://www.cnblogs.com/youzhibing/p/6690341.html
 */
public class ThreadLocalTest
{
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return 1L;
        }
    };
    ThreadLocal<String> stringLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };


    public long getLong()
    {
        return longLocal.get();
    }

    public String getString()
    {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException
    {
        final ThreadLocalTest test = new ThreadLocalTest();

        for (int i=0; i<10; i++)
        {
            System.out.println(test.getString() + " : " + test.getLong());
            test.longLocal.set(test.getLong() + 1);//线程变量+1，多个线程互不影响
        }

        Thread thread1 = new Thread(){
            public void run() {
                for (int i=0; i<10; i++)
                {
                    System.out.println(test.getString() + " : " + test.getLong());
                    test.longLocal.set(test.getLong() + 1);
                }
            };
        };
        thread1.start();

        Thread thread2 = new Thread(){
            public void run() {
                for (int i=0; i<10; i++)
                {
                    System.out.println(test.getString() + " : " + test.getLong());
                    test.longLocal.set(test.getLong() + 1);
                }
            };
        };
        thread2.start();
    }
}