package com.bkunzhang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DiskClassLoaderTest {
    public static void main(String[] args) {

        //创建自定义classloader对象。
        //DiskClassLoaderTest.class.getClassLoader().getResource("classloader_dir").getPath()把class文件放在指定目录
        ClassLoader diskLoader = new DiskClassLoader(DiskClassLoaderTest.class.getClassLoader().getResource("classloader_dir").getPath());
        System.out.println("path:" + DiskClassLoaderTest.class.getClassLoader().getResource("classloader_dir").getPath());
        System.out.println("diskLoader parent:" + diskLoader.getParent());
        try {
            //加载class文件
            Class c = diskLoader.loadClass("com.frank.test.TestBean");

            if(c != null){
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say",null);
                    //通过反射调用Test类的say方法
                    method.invoke(obj, null);
                } catch (InstantiationException | IllegalAccessException
                        | NoSuchMethodException
                        | SecurityException |
                        IllegalArgumentException |
                        InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
