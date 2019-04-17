import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by bkunzhang on 2019/4/14.
 */
public class TestHashMap {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(11, 55);
        map.put(22, 1);
        System.out.println(map);
        System.out.println("*********************************************");
        testHash();
        test();
    }

    static void testHash() {
        Object key = 11;
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        System.out.println("testHash 11: " + hash);

    }

    static void test() {
        System.out.println("(11 >>> 16)=" + (11 >>> 16));

    }
}
