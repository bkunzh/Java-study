package com.bkun.my;

import java.util.Arrays;

/**
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
    }
}
