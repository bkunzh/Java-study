package com.bkunzh.hashmap;

import com.alibaba.fastjson.JSON;
import com.bkunzh.hashmap.bean.MyKey;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.bkunzh.hashmap.util.HashMapUtil.getFieldValue;
import static com.bkunzh.hashmap.util.HashMapUtil.traverseHashMapInnerTable;

/**
 * @author bkunzh
 * @date 2020/10/15
 */
public class Test1 {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // capacity 需要为2的整数次幂，不是在第一次put时会自动纠正（inflateTable(threshold)）
        // 默认loadFactor为0.75，初始table大小为16
        // 下面实验只当初始大小和加载因子
        HashMap<MyKey, Object> map = new HashMap(3, 3F);
        print_capacity(map);

//        HashMap<MyKey, Object> map = new HashMap(5, 2F);
//        HashMap<MyKey, Object> map = new HashMap(8, 2F);
//        map.put(1, "aab");
//        map.put(2, "aab");
//        map.put(3, "aab");
//        map.put(4, "aab");
//        map.put(5, "aab");
//        map.put(6, "aab");
//        map.put(6, "aab");
        map.put(new MyKey(1), "aab");
        print_capacity(map);

        map.put(new MyKey(2), "aab");
        map.put(new MyKey(3), "aab");
        map.put(new MyKey(4), "aab");
        map.put(new MyKey(5), "aab");
        map.put(new MyKey(6), "aab");
        map.put(new MyKey(6), "aab");
        map.put(new MyKey(6), "aab");
        map.put(new MyKey(6), "aab");
        System.out.println("size=" + getFieldValue("size", map));
        System.out.println("threshold=" + getFieldValue("threshold", map));
        print_capacity(map);


        System.out.println("遍历hashmap内部哈希表");
        traverseHashMapInnerTable(map);
        System.out.println("---------");
        System.out.println(map);
    }

    static void print_capacity(HashMap<MyKey, Object> map) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method capacity  = HashMap.class.getDeclaredMethod("capacity");
        capacity.setAccessible(true);
        System.out.println("capacity=" + capacity.invoke(map));
    }



}
