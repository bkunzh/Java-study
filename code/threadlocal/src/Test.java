import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhbk on 2019/3/21.
 * test Calendar.add
 */
public class Test {
    public static void main(String[] args) {
        Date date = new Date();
        for (int i=0; i<10; ++i) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_MONTH, i);
            System.out.println(i + ":" + DateUtilOld.format(cal.getTime()));
        }
    }
}
