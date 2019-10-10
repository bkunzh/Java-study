package lock;

/**
 * @author bkunzhang
 * @date 2019/10/10
 */
public class LockTest {

    Lock lock = new Lock();

    public void print() throws InterruptedException {
        lock.lock();
        doAdd();
        lock.unlock();
    }
    public void doAdd() throws InterruptedException {
        lock.lock();
        //do something
        lock.unlock();
    }


    public static void main(String[] args) throws InterruptedException {
        new LockTest().print();
        System.out.println("ok");
    }
}
