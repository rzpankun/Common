package com.ktiuu.reids;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @Create by pankun
 * @DATE 2020/5/25
 * 1。使用HyperLogLog数据结构统计UV
 *
 */
public class HyperLogLogUtils {
    public static void put(String key,String elements){
        try(Jedis jedis = RedisUtils.jedisPool.getResource()){
            jedis.pfadd(key,elements);
        }
    }

    public static long valueOf(String key){
        try(Jedis jedis = RedisUtils.jedisPool.getResource()){
            return jedis.pfcount(key);
        }
    }

    public static void main(String[] args) {
        /*Random random = new Random();
        for (int i = 0 ; i <100000 ; i ++){
            HyperLogLogUtils.put("MyKey",random.nextLong() + "");
        }*/

        System.out.println(HyperLogLogUtils.valueOf("MyKey"));
    }
}
