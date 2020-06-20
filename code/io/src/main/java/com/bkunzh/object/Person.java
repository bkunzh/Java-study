package com.bkunzh.object;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author bingkun_zhang
 * @date 2020/6/17
 */
//Person类实现了Serializable接口,所以该类才能被序列化;反之，如果没有实现该接口的类则不能被序列化。
public class Person implements Serializable {
    /**
     * 序列化的ID,只要加了该版本号，在反序列化的时候不论你的类的属性是否改变，只要是版本号不变那么尽经可能的兼容新版本。
     * 如果版本号改变了，那么反序列化的过程中就会抛出异常。
     */
    private static final long serialVersionUID = 6871740251451383067L;
    private String name;
    private int age;
    private char sex;
    private ArrayList<String> other;

    public Person(){

    }
    public Person(String name, int age, char sex, ArrayList<String> other) {
        super();
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.other = other;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age)throws Exception {
        this.age = age;
    }
    public char getSex() {
        return sex;
    }
    public void setSex(char sex) {
        this.sex = sex;
    }
    public ArrayList<String> getOther() {
        return other;
    }
    public void setOther(ArrayList<String> other) {
        this.other = other;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", other=" + other +
                '}';
    }
}
