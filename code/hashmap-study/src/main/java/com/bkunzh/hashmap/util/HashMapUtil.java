package com.bkunzh.hashmap.util;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 研究HashMap内部数据结构反射工具类
 * @author bkunzh
 * @date 2020/10/15
 */
public class HashMapUtil {
    /**
     * 遍历hashmap的内部散列表和各个元素的next
     */
    public static void traverseHashMapInnerTable(HashMap hashMap) throws NoSuchFieldException {
        Map.Entry<Integer, Object>[] table = (Map.Entry<Integer, Object>[]) getFieldValue("table", hashMap);
        for (Map.Entry<Integer, Object> entry : table) {
            System.out.print(JSON.toJSONString(entry));
            if (entry != null) {
                System.out.print(", " + entry.getKey());
                System.out.print(", " + entry.getValue());
                System.out.print(", " + getFieldValue("hash", entry));
                System.out.print(", " + JSON.toJSONString(getFieldValue("next", entry)));
            }
            System.out.println();
        }

    }

    public static Object getFieldValue(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
