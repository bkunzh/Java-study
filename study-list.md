<!-- TOC -->

- [1. ThreadLocal原理和适用场景](#1-threadlocal原理和适用场景)
- [2. Java8 lambda表达式](#2-java8-lambda表达式)
- [3. JDBC批处理](#3-jdbc批处理)
- [4. Java泛型](#4-java泛型)

<!-- /TOC -->
# 1. ThreadLocal原理和适用场景
- [Java进阶（七）正确理解Thread Local的原理与适用场景](http://www.jasongj.com/java/threadlocal/)

>**ThreadLocal 适用于每个线程需要自己独立的实例且该实例需要在多个方法中被使用，也即变量在线程间隔离而在方法或类间共享的场景。**
另外，该场景下，并非必须使用 ThreadLocal ，其它方式完全可以实现同样的效果，只是 ThreadLocal 使得实现更简洁。
# 2. Java8 lambda表达式
- [Java8 lambda表达式10个示例](http://www.importnew.com/16436.html)
- 未实践<http://blog.oneapm.com/apm-tech/226.html>
- 有时间看[深入lambda：方法引用（类、实例）](https://www.cnblogs.com/figure9/p/java-8-lambdas-insideout-language-features.html)

> 实践代码[Java8-lambda](code/Java8-lambda)

# 3. JDBC批处理
- 实践代码[jdbc-batch-operate](code/jdbc-batch-operate)
- [笔记](code/jdbc-batch-operate/note.md)

# 4. Java泛型
？？类、方法等的泛型，一直不大清楚，有时间要弄懂


