package cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by zhbk on 2019/3/10.
 * 故意让最后一个线程启动延迟，因为在前面三个线程都达到barrier之后，等待了指定的时间发现第四个线程还没有达到barrier，就抛出异常并继续执行后面的任务
 * 输出：（多次运行有点不一样）
 D:\software\java\bin\java -javaagent:D:\program_software\ideaIU-2017.1.2.win\lib\idea_rt.jar=64908:D:\program_software\ideaIU-2017.1.2.win\bin -Dfile.encoding=UTF-8 -classpath D:\software\java\jre\lib\charsets.jar;D:\software\java\jre\lib\deploy.jar;D:\software\java\jre\lib\ext\access-bridge-64.jar;D:\software\java\jre\lib\ext\cldrdata.jar;D:\software\java\jre\lib\ext\dnsns.jar;D:\software\java\jre\lib\ext\jaccess.jar;D:\software\java\jre\lib\ext\jfxrt.jar;D:\software\java\jre\lib\ext\localedata.jar;D:\software\java\jre\lib\ext\nashorn.jar;D:\software\java\jre\lib\ext\sunec.jar;D:\software\java\jre\lib\ext\sunjce_provider.jar;D:\software\java\jre\lib\ext\sunmscapi.jar;D:\software\java\jre\lib\ext\sunpkcs11.jar;D:\software\java\jre\lib\ext\zipfs.jar;D:\software\java\jre\lib\javaws.jar;D:\software\java\jre\lib\jce.jar;D:\software\java\jre\lib\jfr.jar;D:\software\java\jre\lib\jfxswt.jar;D:\software\java\jre\lib\jsse.jar;D:\software\java\jre\lib\management-agent.jar;D:\software\java\jre\lib\plugin.jar;D:\software\java\jre\lib\resources.jar;D:\software\java\jre\lib\rt.jar;G:\to_github\Java-deep-study\code\concurrent\out\production\concurrent cyclicBarrier.Test3
 线程Thread-0正在写入数据...
 线程Thread-1正在写入数据...
 线程Thread-2正在写入数据...
 线程Thread-2写入数据完毕，等待其他线程写入完毕
 线程Thread-1写入数据完毕，等待其他线程写入完毕
 线程Thread-0写入数据完毕，等待其他线程写入完毕
 java.util.concurrent.BrokenBarrierException
 3:await之后,threadName=Thread-0; e.getClass()=class java.util.concurrent.BrokenBarrierException
 at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:250)
 Thread-0所有线程写入完毕，继续处理其他任务...
 at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
 3:await之后,threadName=Thread-2; e.getClass()=class java.util.concurrent.BrokenBarrierException
 at cyclicBarrier.Test3$Writer2.run(Test3.java:78)
 java.util.concurrent.TimeoutException
 Thread-2所有线程写入完毕，继续处理其他任务...
 at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:257)
 1:await之后,threadName=Thread-1; e.getClass()=class java.util.concurrent.TimeoutException
 at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
 Thread-1所有线程写入完毕，继续处理其他任务...
 at cyclicBarrier.Test3$Writer2.run(Test3.java:78)
 java.util.concurrent.BrokenBarrierException
 at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:250)
 at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
 at cyclicBarrier.Test3$Writer2.run(Test3.java:78)
 线程Thread-3正在写入数据...
 java.util.concurrent.BrokenBarrierException
 线程Thread-3写入数据完毕，等待其他线程写入完毕
 at java.util.concurrent.CyclicBarrier.dowait(CyclicBarrier.java:207)
 3:await之后,threadName=Thread-3; e.getClass()=class java.util.concurrent.BrokenBarrierException
 at java.util.concurrent.CyclicBarrier.await(CyclicBarrier.java:435)
 Thread-3所有线程写入完毕，继续处理其他任务...
 at cyclicBarrier.Test3$Writer2.run(Test3.java:78)

 Process finished with exit code 0

 */
public class Test3 {
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N);

        for(int i=0;i<N;i++) {
            if(i<N-1)
                new Writer2(barrier).start();
            else {
                try {
                    //故意让最后一个线程启动延迟，因为在前面三个线程都达到barrier之后，
                    // 等待了指定的时间发现第四个线程还没有达到barrier，就抛出异常并继续执行后面的任务
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    System.out.println("打断？"); //没执行到这
                    e.printStackTrace();
                }
                new Writer2(barrier).start();
            }
        }
    }
    static class Writer2 extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer2(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                try {
                    cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                    System.out.println("1:await之后,threadName=" + Thread.currentThread().getName()
                            + "; e.getClass()=" + e.getClass());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("2:await之后,threadName=" + Thread.currentThread().getName()
                            + "; e.getClass()=" + e.getClass());

                }catch(BrokenBarrierException e){
                    e.printStackTrace();
                    System.out.println("3:await之后,threadName=" + Thread.currentThread().getName()
                            + "; e.getClass()=" + e.getClass());

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"所有线程写入完毕，继续处理其他任务...");
        }
    }
}


