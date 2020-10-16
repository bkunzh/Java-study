package com.bkun.my;

import java.util.Arrays;

/**
 * 在java项目中创建package以后，可以在package下放一个package-info.java文件。
 * 这个文件有什么作用？
 * 1. 为标注在包上Annotation提供便利；
 * 2. 声明包类和包常量；
 * 3. 提供包的整体注释说明。
 * @author bkunzh
 * @date 2020/10/16
 */
public class PackageInfoTest {
    public static void main(String[] args) {
        Package aPackage = Package.getPackage("com.bkun.my");
        PackageAnno anno = aPackage.getAnnotation(PackageAnno.class);
        if (anno != null) {
            System.out.println(anno.value());
            System.out.println(Arrays.toString(anno.names()));
        }

        MyA myA = new MyA();
        System.out.println(myA);
        System.out.println(MyA.NAME);
    }
}
