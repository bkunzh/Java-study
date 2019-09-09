/**
 * Created by bkunzhang on 2019/9/9.
 */
public class HelloService {

    public void sayHello() throws InterruptedException {
        this.wait();
        System.out.println("-------hello " + Thread.currentThread().getName() + "-------");
        int i = TestWaitAndNotifyAll.atomicInteger.addAndGet(-1);
        System.out.println("i=" + i);
    }

}
