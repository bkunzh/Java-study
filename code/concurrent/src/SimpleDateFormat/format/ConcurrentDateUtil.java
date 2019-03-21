package SimpleDateFormat.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhbk on 2019/3/21.
 * 线程安全的时间工具类
 * SimpleDateFormat
 */
public class ConcurrentDateUtil {

    public final static ThreadLocal<SimpleDateFormat> threadDateFormat = new ThreadLocal<SimpleDateFormat>() {
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
}
