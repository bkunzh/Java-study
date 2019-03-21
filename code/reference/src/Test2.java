import java.util.Date;
import java.util.WeakHashMap;

/**
 * Created by zhbk on 2019/3/21.
 */
public class Test2 {
    public static void main(String[] args) {
        //WeakHashMap的key为弱引用
        WeakHashMap<String, Object> weakHashMap = new WeakHashMap<>();
        weakHashMap.put("ab", new Date());
        System.out.println(weakHashMap.values());
    }
}
