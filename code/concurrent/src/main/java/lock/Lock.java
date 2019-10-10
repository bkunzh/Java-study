package lock;

/**
 * @author bkunzhang
 * @date 2019/10/10
 * 自己实现一个不可重入锁
 */
public class Lock{
    private boolean isLocked = false;
    public synchronized void lock() throws InterruptedException{
        while(isLocked){
            wait();
        }
        isLocked = true;
    }
    public synchronized void unlock(){
        isLocked = false;
        notify();
    }
}