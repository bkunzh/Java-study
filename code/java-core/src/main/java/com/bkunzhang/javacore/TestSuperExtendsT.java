package com.bkunzhang.javacore;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bingkun_zhang
 * @date 2020/7/15
 */
public class TestSuperExtendsT {
    @Test
    public void nullTest() {
        System.out.println(null instanceof String); // false
        System.out.println(null instanceof Map); // false
        Object o = null;
        System.out.println((List) o);
    }

    @Test
    public void superExtendsT() {
        List<String> list = new ArrayList<>();
        List<? extends Object> objectList = list;

        Map<String, String> map = new HashMap<>();
        Map<String, ? extends Object> oMap = map;
        t(map);
    }

    void t(Map<String, ? extends Object> oMap) {
        System.out.println(oMap);
    }
}
