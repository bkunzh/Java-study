package math;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.Test;

/**
 * @author bingkun_zhang
 * @date 2020/7/9
 */
public class RandomTest {
    @Test
    public void t1() {
        RandomDataGenerator generator = new RandomDataGenerator();
        System.out.println(generator.nextHexString(20));
        System.out.println(generator.nextHexString(20));

    }
}
