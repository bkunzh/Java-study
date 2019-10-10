package lock;

/**
 * @author bkunzhang
 * @date 2019/10/10
 * 自己实现一个可重入锁
 */
public class Lock2 {
    boolean isLocked = false;
    Thread  lockedBy = null;
    int lockedCount = 0;
    public synchronized void lock()
            throws InterruptedException{
        Thread thread = Thread.currentThread();
        while(isLocked && lockedBy != thread){
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = thread;
    }
    public synchronized void unlock(){
        if(Thread.currentThread() == this.lockedBy){
            lockedCount--;
            if(lockedCount == 0){
                isLocked = false;
                notify();
            }
        }
    }

}
