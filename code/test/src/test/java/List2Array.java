import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author bingkun_zhang
 * @date 2020/7/9
 */
public class List2Array {
    @Test
    public void t() {
        String[] arr = new String[2];
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        assertTrue(arr == list.toArray(arr));
        assertFalse(arr == list.toArray());
        System.out.println(arr[1]);
    }
}
