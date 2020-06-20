package com.bkunzh.object;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author bingkun_zhang
 * @date 2020/6/15
 */
public class ObjectOutputInputStreamTest {

    private static final String DIR = "F:\\studyspace\\Java-deep-study\\code\\io\\src\\main\\resources";

    /**
     * ObjectOutputStream:高级流，对象输出流，只能将支持 java.io.Serializable 接口的对象写入流中
     * *
     * * 将一个特定的数据结构转换为一组字节的过程称之为序列化
     * * 将一组字节转换为特定的数据结构的过程称之为反序列化
     * * 将数据写入硬盘长久保存的过程称之为持久化
     */
    @Test
    public void objectOutputStream() throws Exception {
        ArrayList<String> other = new ArrayList<>();
        other.add("清华大学");
        other.add("软件学院");
        other.add("软件工程");
        Person person = new Person("小明", 22, '男', other);
        FileOutputStream fos = new FileOutputStream(DIR + "\\OOS.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(person);
        oos.flush();
        oos.close();
        System.out.println("对象写入成功！");
    }

    @Test
    public void objectInputStream() throws Exception {
        FileInputStream fis = new FileInputStream(DIR + "\\OOS.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Person person = (Person) ois.readObject();
        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println(person.getSex());
        ArrayList<String> other = person.getOther();
        for (String string : other) {
            System.out.println(string);
        }
        ois.close();
        System.out.println("反序列化成功！");
    }
}
