import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author bingkun_zhang
 * @date 2020/7/2
 * Dev和Test的锁都锁一天，保证这一天定时remind任务不执行. 从 07-02 13：05到07-03的这个时候
 */
public class RedisTest {
    @Test
    public void t() {
//        Jedis jedis = new Jedis("172.20.176.213", 6379);
        Jedis jedis = new Jedis("127.0.0.1", 6380);
//        jedis.auth("kdzwytest");
        jedis.auth("kk");

        final String lock = "lock:reminderTask";
        final String oldLock = "reminderTask";
        final int timeS = 24 * 60 * 60;
        for (int i=0; i<16; ++i) {
            jedis.select(i);
            jedis.setnx(lock, "1");
            jedis.expire(lock, timeS);
            jedis.set(oldLock, "1");
            jedis.expire(oldLock, timeS);
        }


//        boolean b = redisManager.setStringNX(lock, "1", LOCK_TIME);
//        if (!b){
//            LogHelper.debug(log, "oldLock, return");
//        }

        jedis.close(); //使用完关闭连接
    }
}
