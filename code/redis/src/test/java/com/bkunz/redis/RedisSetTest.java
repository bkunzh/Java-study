package com.bkunz.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * @author bingkun_zhang
 * @date 2020/8/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springredis.xml")
public class RedisSetTest {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void getAll() {
        Set<Object> set = redisTemplate.opsForSet().members("myset");
        System.out.println(set);
    }

    @Test
    public void add() {
        System.out.println(redisTemplate.opsForSet().add("myset", "3", "22"));
        System.out.println(redisTemplate.opsForSet().add("myset", "aa", "778", "cc"));
    }

    @Test
    public void rm() {
        System.out.println(redisTemplate.opsForSet().size("myset"));
        System.out.println(redisTemplate.opsForSet().remove("myset", "22"));
        System.out.println(redisTemplate.opsForSet().size("myset"));

    }
}
