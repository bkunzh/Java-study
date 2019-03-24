package cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by zhbk on 2019/3/10.
 * public CyclicBarrier(int parties)
 */
/**
 output(多线程程序每次结果不一定相同):
 线程Thread-0正在写入数据...
 线程Thread-0睡眠时间:1477
 线程Thread-1正在写入数据...
 线程Thread-1睡眠时间:1981
 线程Thread-2正在写入数据...
 线程Thread-2睡眠时间:773
 线程Thread-3正在写入数据...
 线程Thread-3睡眠时间:3637
 线程Thread-2写入数据完毕，等待其他线程写入完毕
 线程Thread-0写入数据完毕，等待其他线程写入完毕
 线程Thread-1写入数据完毕，等待其他线程写入完毕
 线程Thread-3写入数据完毕，等待其他线程写入完毕
 所有线程写入完毕，继续处理其他任务...，当前线程为:Thread-3
 所有线程写入完毕，继续处理其他任务...，当前线程为:Thread-2
 所有线程写入完毕，继续处理其他任务...，当前线程为:Thread-1
 所有线程写入完毕，继续处理其他任务...，当前线程为:Thread-0
 */
public class Test1 {
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N);
        for(int i=0;i<N;i++)
            new Writer(barrier).start();
    }
}
