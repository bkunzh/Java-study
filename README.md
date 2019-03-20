<!-- TOC -->

- [1. ThreadLocal原理和适用场景](#1-threadlocal原理和适用场景)
- [2. Java8 lambda表达式](#2-java8-lambda表达式)
- [3. JDBC批处理](#3-jdbc批处理)
- [4. Java泛型](#4-java泛型)
- [5. java.util.concurrent包的3个类](#5-javautilconcurrent包的3个类)
- [99. 一些说明](#99-一些说明)
    - [用IDEA的md编辑器，在` ```java``` `中的代码片段会报错。](#用idea的md编辑器在-java-中的代码片段会报错)

<!-- /TOC -->
# 1. ThreadLocal原理和适用场景
- [Java进阶（七）正确理解Thread Local的原理与适用场景](http://www.jasongj.com/java/threadlocal/)
>**ThreadLocal 适用于每个线程需要自己独立的实例且该实例需要在多个方法中被使用，也即变量在线程间隔离而在方法或类间共享的场景。**
另外，该场景下，并非必须使用 ThreadLocal ，其它方式完全可以实现同样的效果，只是 ThreadLocal 使得实现更简洁。
- 实践代码[threadlocal](code/threadlocal)
- [笔记](code/threadlocal/note.md)

# 2. Java8 lambda表达式
- [Java8 lambda表达式10个示例](http://www.importnew.com/16436.html)
- 未实践<http://blog.oneapm.com/apm-tech/226.html>
- 有时间看[深入lambda：方法引用（类、实例）](https://www.cnblogs.com/figure9/p/java-8-lambdas-insideout-language-features.html)

- 实践代码[Java8-lambda](code/Java8-lambda)
- [笔记](code/Java8-lambda/note.md)

# 3. JDBC批处理
- 实践代码[jdbc-batch-operate](code/jdbc-batch-operate)
- [笔记](code/jdbc-batch-operate/note.md)

# 4. Java泛型
？？类、方法等的泛型，一直不大清楚，有时间要弄懂

# 5. java.util.concurrent包的3个类
CountDownLatch/CyclicBarrier/Semaphore
- [笔记](code/concurrent/note.md)

# 99. 一些说明
## 用IDEA的md编辑器，在` ```java``` `中的代码片段会报错。
未找到很好的不报错的解决方法。所以在IDEA中编辑md文件时，用` ```jav``` `代替` ```java``` `，等到写好后，再用` ```java `替换` ```jav `即可。
