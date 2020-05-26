package com.ktiuu.reids;

import redis.clients.jedis.Jedis;

/**
 * @Create by pankun
 * @DATE 2020/5/25
 */
public class RedisReenterLock {
    private static final String LOCK_KEY = "REDISRENENTERLOCK";
    public static ThreadLocal<Integer> local = new ThreadLocal<>();



    public boolean lock(){
        try(Jedis jedis = RedisUtils.jedisPool.getResource()){
            Long setnx = jedis.setnx(LOCK_KEY, Thread.currentThread().toString());

            if(setnx == 0){
                if(jedis.get(LOCK_KEY).equals(Thread.currentThread().toString())){
                    local.set(local.get() + 1);
                    return true;
                }
                return false;
            }else{
                local.set(1);
                return true;
            }

        }
    }


    public void unlock(){
        try(Jedis jedis = RedisUtils.jedisPool.getResource()){
            if(local.get() > 0){
                if(local.get() == 1){
                    local.set(0);
                    jedis.del(LOCK_KEY);
                }else{
                    local.set(local.get() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {

        new Thread(new Runnable(){
            @Override
            public void run() {
                RedisReenterLock redisReenterLock = new RedisReenterLock();
                boolean lock = redisReenterLock.lock();
                System.out.println(lock);
                System.out.println(RedisReenterLock.local.get());

                lock = redisReenterLock.lock();
                System.out.println(lock);
                System.out.println(RedisReenterLock.local.get());

                redisReenterLock.unlock();
                System.out.println(RedisReenterLock.local.get());
                redisReenterLock.unlock();
                System.out.println(RedisReenterLock.local.get());
            }
        }).start();
    }
}
