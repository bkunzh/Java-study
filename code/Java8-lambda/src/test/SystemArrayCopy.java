package test;

import java.util.Arrays;

/**
 * Created by zhbk on 2019/2/23.
 */
public class SystemArrayCopy {

    public static void main(String[] args) {
        int[] srcArr = {5, 1, 2};
        int[] destArr = new int[8];
        System.arraycopy(srcArr, 0, destArr, 1, srcArr.length);
        System.out.println(Arrays.toString(destArr));
    }

}
