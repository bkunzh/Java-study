# 1. 读代码ThreadLocalDemo结论
1. 不同线程都是同一个ThreadLocal变量，因为是类静态变量
2. 不同线程的threadlocal.get()的实例不同
3. threadlocal.set(T)替换当前线程的threadlocal.get()实例

# 2. ThreadLocal实现
1. **Thread**类中有一个ThreadLocalMap的实例变量threadLocals，键是threadLocal变量，包权限
```java
    ThreadLocal.ThreadLocalMap threadLocals = null;
```
2. ThreadLocal
- 在ThreadLocal中，通过Thread.currentThread().threadLocals即可获得ThreadLocalMap实例
> `ThreadLocalMap m = getMap(Thread.currentThread());`
- ThreadLocal.getMap
```java
    ThreadLocalMap getMap(Thread t) {
        return t.threadLocals;
    }
```
- ThreadLocal.get实现如下：
```java
    public T get() {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        
        //第一次获取，设置初始值
        return setInitialValue();
    }
```
- ThreadLocal.setInitialValue
```java
    private T setInitialValue() {
        //设置初始值
        T value = initialValue(); 
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
        return value;
    }
    
```
- ThreadLocal.initialValue，可以重写该方法，指定初始值
```java
    protected T initialValue() {
        return null;
    }
```
- ThreadLocal.set实现如下：
```java
    public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
    }
    
    
```
- ThreadLocal.createMap
```java
//创建ThreadLocalMap，赋值给t.threadLocals
    void createMap(Thread t, T firstValue) {
        t.threadLocals = new ThreadLocalMap(this, firstValue);
    }
```


