import org.junit.Test;

import java.util.*;

/**
 * Created by bkunzhang on 2019/4/14.
 * HashMap不支持线程的同步，即任一时刻可以有多个线程同时写HashMap，可能会导致数据的不一致。
 * 如果需要同步，可以用Collections.synchronizedMap(HashMap map)方法使HashMap具有同步的能力。
 * Hashtable与HashMap类似，不同的是：它不允许记录的键或者值为空；它支持线程的同步
 * LinkedHashMap保存了记录的插入顺序
 * TreeMap能够把它保存的记录根据键排序，默认是按升序排序，也可以指定排序的比较器
 */
public class TestMap {
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

    @Test
    public void test1() {
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("121", "abc");
        treeMap.put("111", "ccc");
        treeMap.put("012", "ddd");
        System.out.println(treeMap);
    }

    @Test
    public void test2() {
//        Map<String, String> map = new TreeMap<>();
//        Map<String, String> map = new HashMap<>();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("aaa121", "abc");
        map.put("ddddf111", "ccc");
        map.put("ccc012", "ddd");
        map.put("01112", "ddd");
        map.put("0112", "ddd");
        System.out.println(map);
    }
}
