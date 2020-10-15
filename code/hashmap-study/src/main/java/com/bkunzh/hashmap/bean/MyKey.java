package com.bkunzh.hashmap.bean;

/**
 * 我的key，增加hash冲突的概率
 * @author bkunzh
 * @date 2020/10/15
 */
public class MyKey {
    private Integer key;

    public MyKey(Integer key) {
        this.key = key;
    }

    public Integer getKey() {
        return key;
    }

    @Override
    public String toString() {
        return String.valueOf(key);
    }

    @Override
    public int hashCode() {
        return key % 5;
    }
}
