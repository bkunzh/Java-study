package cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by zhbk on 2019/3/10.
 * public CyclicBarrier(int parties)
 */
public class Test1 {
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier  = new CyclicBarrier(N);
        for(int i=0;i<N;i++)
            new Writer(barrier).start();
    }
}
