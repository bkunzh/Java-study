import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bingkun_zhang
 * @date 2020/7/3
 * ObjectId("5efd8a92e4b0e5e520ac7030")
 */
public class ObjectIdToDate {
    public static void main(String[] args) {
        String id = "5efd8a92e4b0e5e520ac7030";
        Date date = new Date(Integer.parseInt(id.substring(0, 8), 16) * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(sdf.format(date));
    }
}
