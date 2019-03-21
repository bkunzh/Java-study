package SimpleDateFormat.format;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhbk on 2019/3/21.
 */
public class MySimpleDateFormat {
    Calendar calendar = Calendar.getInstance();

    public String format(Date date) {
        calendar.setTime(date);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ConcurrentDateUtil.format(calendar.getTime());
    }
}
