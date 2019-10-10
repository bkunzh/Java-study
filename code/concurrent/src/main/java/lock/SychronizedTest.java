package lock;

/**
 * @author bkunzhang
 * @date 2019/10/10
 */
public class SychronizedTest {

    public synchronized void print() {
        System.out.println("print:" + Thread.currentThread().getName());
        doAdd();
    }
    public synchronized void doAdd()  {
        //do something
        System.out.println("doAdd:" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        new SychronizedTest().print();
        System.out.println("ok");
    }

}
