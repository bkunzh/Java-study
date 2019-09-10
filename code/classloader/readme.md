## 测试代码
[跳转](src/test/java)
## 自定义ClassLoader
见 DiskClassLoader
## 3种ClassLoader的加载class的路径，见 ClassLoaderPathTest
```java
    System.out.println(System.getProperty("sun.boot.class.path"));
    System.out.println(System.getProperty("java.ext.dirs"));
    System.out.println(System.getProperty("java.class.path"));
```
## 99. 参考
- <https://blog.csdn.net/briblue/article/details/54973413>