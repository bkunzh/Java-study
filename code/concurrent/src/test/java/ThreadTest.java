import org.junit.Test;

/**
 * @author bkunzhang
 * @date 2019/9/11
 */
public class ThreadTest {

    //阻塞，直到线程执行完
    //thread.join
    @Test
    public void join() throws InterruptedException {
        Thread thread = new MyThread();
        thread.start();
        System.out.println("start");
        //阻塞，直到线程执行完
        thread.join();
        System.out.println("end");
    }

    static class SleepThread extends Thread {
        private volatile boolean stop = false; //如果线程阻塞，将不会检查此变量

        public void _stop() {
            this.stop = true;
        }

        @Override
        public void run() {
            while (!stop) {
                System.out.println("Thread running...");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted...");
                }
            }
            System.out.println("Thread exiting under request...");
        }
    }

    //中断线程阻塞状态，sleep、wait等会产生InterruptedException。
    // 如果线程没有被阻塞，这时调用interrupt（）将不起作用
    //Thread.interrupt
    @Test
    public void interrupt() throws InterruptedException {
        SleepThread sleepThread = new SleepThread();

        System.out.println("Starting thread...");
        sleepThread.start();
        Thread.sleep(3000);
        System.out.println("Asking thread to stop...");

        //修改停止状态
        sleepThread._stop();
        //中断线程阻塞状态
        sleepThread.interrupt();
        Thread.sleep(3000);
        System.out.println("Stopping application...");
    }

}
