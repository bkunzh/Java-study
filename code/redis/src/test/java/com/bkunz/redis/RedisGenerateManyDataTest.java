package com.bkunz.redis;

import redis.clients.jedis.Jedis;

/**
 * 产生大量redis数据
 *
 * @author bkunzh
 * @date 2020/10/20
 */
public class RedisGenerateManyDataTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        final int timeS = 24 * 60 * 60;
        jedis.select(0);
        for (int i=0; i<100*1000; ++i) {
            jedis.setnx("key" + i, "v" + i);
            jedis.expire("key" + i, timeS);
        }
        jedis.close();
    }
}
