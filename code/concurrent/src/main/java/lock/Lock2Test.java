package lock;

/**
 * @author bkunzhang
 * @date 2019/10/10
 */
public class Lock2Test {

    Lock2 lock = new Lock2();

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
        new Lock2Test().print();
        System.out.println("ok");
    }
}

