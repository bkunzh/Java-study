package lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bkunzhang
 * @date 2019/10/10
 */
public class ReentrantLockLockTest2 {
    ReentrantLock lock = new ReentrantLock();

    public void print() {
        lock.lock();
        doAdd();
        lock.unlock();
    }
    public void doAdd() {
        lock.lock();
        //do something
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        new ReentrantLockLockTest2().print();
        System.out.println("ok");
    }

}
