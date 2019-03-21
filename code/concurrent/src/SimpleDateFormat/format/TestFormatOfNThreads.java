package SimpleDateFormat.format;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by zhbk on 2019/3/21.
 * 自己模拟了SimpleDateFormat.format的线程不安全的例子，直接用SimpleDateFormat.format本来的代码线程安全问题不能复现
 * 见TestSimpleDateFormat类
 */
public class TestFormatOfNThreads {
    static boolean isError = false;

    static MySimpleDateFormat mySimpleDateFormat = new MySimpleDateFormat();

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] strs = new String[] {"2016-01-01 10:24:00", "2016-01-02 20:48:00", "2016-01-11 12:24:00"};
        Date date = new Date();
        StringBuffer sb = new StringBuffer();
        int k = 0;
        while (!isError && k < 10) {
            ++k;
            CountDownLatch latch2 = new CountDownLatch(10);

            CountDownLatch latch3 = new CountDownLatch(1);

            //等这10个线程运行完，如果线程0没有异常的数据，再来一次，一直到有异常数据为止
            for (int i = 0; i < 10; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            latch.await(); //所有线程都等到main执行到countDown，在这一起开始
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String threadName = Thread.currentThread().getName();

                        //不是0线程都等着
                        if (!"tttttt-0".equals(threadName)) {
                            try {
                                latch3.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        for (int i = 0; i < 10; i++) {
                            try {
                                String formatStr = null;
                                //0线程的第1、2个数，让它正常
                                if ("tttttt-0".equals(threadName) && i <=1) {
//                                    System.out.println("我是0线程:" + i);

                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(date);
                                    cal.add(Calendar.DAY_OF_MONTH, i);

                                    Date formatDate = cal.getTime();
                                    formatStr = mySimpleDateFormat.format(formatDate);


                                    //0线程的第1、2个数正常了，允许其他线程执行
                                    if (i == 1) {
                                        latch3.countDown();
                                    }
                                } else {
                                    int n = 100;
                                    Thread.sleep((int) (Math.random() * n));

                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(date);
                                    cal.add(Calendar.DAY_OF_MONTH, i);

                                    //不是0线程，直接加10年，这样如果后面有任意一个线程重新设置了线程0的calendar，线程0就不对了
                                    if (!"tttttt-0".equals(threadName)) {
                                        cal.add(Calendar.YEAR, 10);
                                    }
                                    Date formatDate = cal.getTime();
                                    formatStr = mySimpleDateFormat.format(formatDate);
                                    //线程0是否有异常的
                                    if ("tttttt-0".equals(threadName) && formatStr.contains("2029")) {
                                        isError = true;
                                    }
                                }

                                sb.append(Thread.currentThread().getName() + ":" + i + ":" + formatStr + "\n");


                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        latch2.countDown();
                    }
                }, "tttttt-" + i).start();
            }

            latch.countDown();

            try {
                latch2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //线程全部运行完再看是true还是false
            System.out.println("isError=" + isError);
            if (isError) {
                System.out.println(sb);
            }

        }

    }
}
