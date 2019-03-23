<!-- TOC -->

- [1. CountDownLatch](#1-countdownlatch)
- [2. CyclicBarrier](#2-cyclicbarrier)
- [3. Semaphore](#3-semaphore)
- [4. 总结](#4-总结)

<!-- /TOC -->
# 1. CountDownLatch
>CountDownLatch类位于java.util.concurrent包下，利用它可以实现类似计数器的功能。比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
1. CountDownLatch类只提供了一个构造器：
```java
public CountDownLatch(int count) {  };  //参数count为计数值
```
2. 3个方法
```java
public void await() throws InterruptedException { };   //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };  //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
public void countDown() { };  //将count值减1
```
- [实践代码见](src/countdownLatch)

# 2. CyclicBarrier
>字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了
1. 两个构造器
```java
public CyclicBarrier(int parties, Runnable barrierAction)
public CyclicBarrier(int parties)
```
> 参数parties指让多少个线程或者任务等待至barrier状态；参数barrierAction为当这些线程都达到barrier状态时会执行的内容。
2. await方法
```java
public int await() throws InterruptedException, BrokenBarrierException ;
public int await(long timeout, TimeUnit unit)throws InterruptedException,BrokenBarrierException,TimeoutException;
```
> 第一个版本比较常用，用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务；第二个版本是让这些线程等待至一定的时间，如果还有线程没有到达barrier状态就直接让到达barrier的线程执行后续任务。
- [实践代码见](src/cyclicBarrier)

# 3. Semaphore
Semaphore翻译成字面意思为 信号量，Semaphore可以控同时访问的线程个数，通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可。
1. Semaphore类位于java.util.concurrent包下，它提供了2个构造器：
```java
public Semaphore(int permits) {          //参数permits表示许可数目，即同时可以允许多少线程进行访问
    sync = new NonfairSync(permits);
}
public Semaphore(int permits, boolean fair) {    //这个多了一个参数fair表示是否是公平的，即等待时间越久的越先获取许可
    sync = fair ? new FairSync(permits) : new NonfairSync(permits);
}
```
2. Semaphore类中比较重要的几个方法:
```java
public void acquire() throws InterruptedException {  }     //获取一个许可
public void acquire(int permits) throws InterruptedException { }    //获取permits个许可
public void release() { }          //释放一个许可
public void release(int permits) { }    //释放permits个许可
```
> acquire()用来获取一个许可，若无许可能够获得，则会一直等待，直到获得许可。会被阻塞。

3. 如果想立即/阻塞不超过一定时间得到执行结果，可以使用下面几个方法：
```java
public boolean tryAcquire() { };    //尝试获取一个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException { };  //尝试获取一个许可，若在指定的时间内获取成功，则立即返回true，否则到时间立即返回false
public boolean tryAcquire(int permits) { }; //尝试获取permits个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException { }; //尝试获取permits个许可，若在指定的时间内获取成功，则立即返回true，否则到时间立即返回false
```
4. 得到可用的许可数目
```java
public int availablePermits() {
        return sync.getPermits();
}
```

- [实践代码见](src/semaphore)

# 4. 总结
- 1）CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同：  
    - CountDownLatch一般用于某个/若干线程等待若其他一个/多个线程执行完任务之后，它才执行；
    - 而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
    - 另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。
- 2）Semaphore其实和锁有点类似，它一般用于控制对某组资源的访问权限。

> 参考资料<https://www.cnblogs.com/dolphin0520/p/3920397.html>、谷歌搜索java CountDownLatch
