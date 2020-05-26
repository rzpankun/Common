package com.ktiuu.reids;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.exceptions.JedisException;

import java.util.Random;

/**
 * @Create by pankun
 * @DATE 2020/5/25
 * 使用zset的score的属性存储时间
 * 请求时，对事件窗口进行操作，统计事件窗口事件，并且删除过期事件
 */
public class SimpleRateLimiter {


    public static boolean isActionAllowed(String key , int period , int maxCount){

        try(Jedis jedis = RedisUtils.jedisPool.getResource()){
            long nowTs = System.currentTimeMillis();
            Pipeline pipelined = jedis.pipelined();
            pipelined.multi();
            pipelined.zadd(key, nowTs, ""+nowTs);
            pipelined.zremrangeByScore(key, 0, nowTs - period * 1000);
            Response<Long> zcard = pipelined.zcard(key);
//            pipelined.expire(key, period + 1);
            pipelined.exec();
            pipelined.close();
            return zcard.get() <= maxCount ;
        }

    }

    public static void main(String[] args) {
        for(int i = 0 ; i < 20 ; i ++){
            System.out.println(SimpleRateLimiter.isActionAllowed("MyKey", 3, 5));
            try {
                Thread.sleep(new Random().nextInt(100) * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        System.out.println(System.currentTimeMillis() - 1000*5);

//        System.out.println(SimpleRateLimiter.isActionAllowed("MyKey", 3, 5));
    }
}
