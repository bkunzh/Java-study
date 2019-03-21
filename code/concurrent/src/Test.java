import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by zhbk on 2019/3/9.
 */
public class Test {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        try {
            //dateFormat.getClass().getSuperclass()获得父类属性
            Field calField = dateFormat.getClass().getSuperclass().getDeclaredField("calendar");
            calField.setAccessible(true);
            Calendar calendar = (Calendar) calField.get(dateFormat);
            System.out.println("thread " + Thread.currentThread().getName() + ":calendar.hashCode=" + calendar.hashCode());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}