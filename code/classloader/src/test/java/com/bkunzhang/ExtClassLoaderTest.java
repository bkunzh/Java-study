package com.bkunzhang;

import org.junit.Test;

public class ExtClassLoaderTest {
    @Test
    public void t() throws ClassNotFoundException {
        Class clazz = Class.forName("com.bkunzhang.util.DateTimeUtils");
        System.out.println(clazz.getClassLoader());

    }
}
