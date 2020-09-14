package com.bkunz.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class RedisTtl {
    @Test
    public void t() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.keys("*").stream().forEach(t -> {
            System.out.println(t);
        });
        while (true) {
            long ttl = jedis.ttl("shiro_redis_cache:2ff32c4a-bd0e-481d-8e77-e9bd6793d18d");
            if ("1".equals(jedis.get("exit"))) {
                System.out.println("exit 1");
                break;
            }
            if (ttl > 0) {
                System.out.println(ttl);
            } else {
                System.out.println("ttl 0");
                break;
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        jedis.close();
    }
}
