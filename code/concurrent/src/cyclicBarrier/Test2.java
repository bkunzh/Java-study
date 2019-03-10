package cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by zhbk on 2019/3/10.
 * 如果说想在所有线程写入操作完之后，进行额外的其他操作可以为CyclicBarrier提供Runnable参数
 *
 * 从结果可以看出，当四个线程都到达barrier状态后，会从四个线程中选择一个线程去执行Runnable。
 */
public class Test2 {
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N,() ->{
            System.out.println("当前线程"+Thread.currentThread().getName());
        });

        for(int i=0;i<N;i++)
            new Writer(barrier).start();
    }
}
