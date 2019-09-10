package com.bkunzhang;

import org.junit.Test;

public class ClassLoaderPathTest {

    @Test
    public void getProperty() {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }

}
