import org.junit.Test;

public class ThreadTest {

    //thread.join
    @Test
    public void join() throws InterruptedException {
        Thread thread = new MyThread();
        thread.start();
        System.out.println("start");
        thread.join();
        System.out.println("end");
    }

}
