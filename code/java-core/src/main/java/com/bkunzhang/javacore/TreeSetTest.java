package com.bkunzhang.javacore;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author bingkun_zhang
 * @date 2020/7/15
 */
public class TreeSetTest {
    @Test
    public void t() {
        // set会重新排序，不会和添加时顺序一样
        Set<String> set = new TreeSet<>();
        set.add("bb");
        set.add("aa");
        set.add("ab");
        for (String s : set) {
            System.out.println(s);
        }
    }
}
