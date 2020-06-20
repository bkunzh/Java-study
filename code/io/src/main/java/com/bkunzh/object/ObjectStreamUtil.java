package com.bkunzh.object;

import java.io.*;

/**
 * @author bingkun_zhang
 * @date 2020/6/17
 */
public class ObjectStreamUtil {
    /**
     * 将对象序列化写入到文件中
     * @param object
     * @param file
     * @throws Exception
     */
    public static void writeTo(Object object, File file) throws Exception {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        fos.flush();
        fos.close();
    }

    /**
     * 从文件中读出对象，反序列化
     * @param file
     * @return
     * @throws Exception
     */
    public static Object readObject(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return ois.readObject();
    }

}
