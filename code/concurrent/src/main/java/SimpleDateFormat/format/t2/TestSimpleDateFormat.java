package SimpleDateFormat.format.t2;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zhbk on 2019/3/21.
 * 出现不了，先放着吧
 * SimpleDateFormat.format不能代码实现让线程安全问题出现？
 * 想着不是0线程，直接加10年，这样如果后面有任意一个线程重新设置了线程0的calendar，线程0就不对了。但是线程0的数据全都没问题。。
 * https://github.com/bkunzhang/notes/issues/9
 * 模拟的可以，见TestFormatOfNThreads类
 * SimpleDateFormat.parse可以出现线程安全问题，见Java-deep-study/code/threadlocal/src/DateUtilOld.java
 */
public class TestSimpleDateFormat {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static boolean isError = false;

    public static String format(Date date) {
        return dateFormat.format(date);
    }

    public static Date parse(String dateStr) throws ParseException {
        return dateFormat.parse(dateStr);
    }

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(1);
        Date date = new Date();
        int k = 0;
        while (!isError && k < 10) {
            StringBuffer sb = new StringBuffer();
            final int k2 = k;
            ++k;
            CountDownLatch latch2 = new CountDownLatch(10);
            //等这10个线程运行完，如果线程0没有异常的数据，再循环一次，一直到有异常数据为止，外层while
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
                        for (int i = 0; i < 10; i++) {
                            try {
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

                                //觉得父类中calendar属性有可能在变，不是一个，要不然不可能没有线程安全问题
                                try {
                                    //dateFormat.getClass().getSuperclass()获得父类属性
                                    Field calField = dateFormat.getClass().getSuperclass().getDeclaredField("calendar");
                                    calField.setAccessible(true);
                                    Calendar calendar = (Calendar) calField.get(dateFormat);
                                    System.out.println("k2=" + k2);
                                    System.out.println("thread " + Thread.currentThread().getName() + ":calendar.hashCode=" + calendar.hashCode());
                                } catch (NoSuchFieldException e) {
                                    e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }

                                String formatStr = format(formatDate);

                                //线程0
                                if ("tttttt-0".equals(threadName) && formatStr.contains("2029")) {
                                    isError = true;
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
            System.out.println(sb);

        }

    }

}
