## sync包
1. 理解下面两种情况，第一种情况直接进可以打印出`main() ***`，第二种需要等待String.class锁，得到锁，才能打印
> 见`TestSynchronized.java`
```
//1
        System.out.println("main() ***");

        //2
        //有这一句，则打印main也要竞争锁
        // 临界区
//        synchronized (String.class) {
//            System.out.println("main() ***");
//        }
```

## [其它早期笔记](note.md)
