package com.bkunzh.object;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

/**
 * @author bingkun_zhang
 * @date 2020/6/17
 */
public class ObjectStreamUtilTest {
    File file = new File("F:\\studyspace\\Java-deep-study\\code\\io\\src\\main\\resources\\OOS2.txt");

    @Test
    public void write() throws Exception {
        ArrayList<String> other = new ArrayList<>();
        other.add("清华大学");
        other.add("软件学院");
        other.add("软件工程");
        other.add("软件工程2");
        Person person = new Person("小明", 22, '男', other);
        ObjectStreamUtil.writeTo(person, file);
    }

    @Test
    public void read() throws Exception {
        Person person = (Person) ObjectStreamUtil.readObject(file);
        System.out.println(person);
    }
}
