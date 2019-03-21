import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zhbk on 2019/3/21.
 * 使用ThreadLocal解决SimpleDateFormat线程安全问题
 * http://blog.jrwang.me/2016/java-simpledateformat-multithread-threadlocal/
 */
public class DateUtilNew {
    private static ThreadLocal<SimpleDateFormat> threadDateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static String format(Date date) {
        return threadDateFormat.get().format(date);
    }

    public static Date parse(String dateStr) throws ParseException {
        return threadDateFormat.get().parse(dateStr);
    }

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] strs = new String[] {"2016-01-01 10:24:00", "2016-01-02 20:48:00", "2016-01-11 12:24:00"};
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < 10; i++){
                        try {
                            System.out.println(Thread.currentThread().getName()+ "\t" + DateUtilNew.parse(strs[0]));
                            Thread.sleep(100);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        latch.countDown();
    }
}
