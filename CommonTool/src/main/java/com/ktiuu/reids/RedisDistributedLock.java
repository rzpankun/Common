package com.ktiuu.reids;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Arrays;
import java.util.Random;

/**
 * @Create by pankun
 * @DATE 2020/5/25
 * 1.互斥性：在任意时刻只有一个时间，只能有一个客户端获取到锁，并且也只有一个客户端请求锁成功
 * 2.即使获获取锁的客户端没有释放锁，也能保证正常过期，后续客户端能正常加锁
 * 3.只要大部分Redis结点可用，客户端就能正常加锁
 * 4，自旋重试
 */
public class RedisDistributedLock {
    private static final int LOCK_TIME = 5;
    private static final String LOCK_FLAG = "REDISDISTRIBUTEDLOCK";
    private static final int WAIT_TIME = 500;
    private static final int RETRY_TIME = 3;


    public boolean tryLock(String random){
        try(Jedis jedis = RedisUtils.jedisPool.getResource()){
            int time = RETRY_TIME;
            while(true){
                SetParams setParams = new SetParams();
                setParams.ex(LOCK_TIME);
                setParams.nx();
                String set = jedis.set(LOCK_FLAG, random, setParams);
                System.out.println(set);
                if(set!=null && set.equals("OK")){
                    return true;
                }else{
                    if(--time == 0){
                        break;
                    }
                    long l = System.currentTimeMillis();
                    //等待时间循环内不做任何操作
                    while(System.currentTimeMillis() - l <= WAIT_TIME){
                    }
                }
            }
        }
        return false;
    }


    public void tryUnlock(String random){
        try(Jedis jedis = RedisUtils.jedisPool.getResource()){
            String lua = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then " +
                    "return redis.call(\"del\",KEYS[1])" +
                    "else" +
                    " return 0" +
                    " end";
            Object evalsha = jedis.eval(lua, Arrays.asList(LOCK_FLAG), Arrays.asList(random));
//            int a = (int) evalsha;

        }
    }

    public static class MyThread extends Thread{
        private RedisDistributedLock lock;


        public MyThread(RedisDistributedLock lock){
            this.lock = lock;
        }

        @Override
        public void run() {
            long l = new Random().nextLong();
            String str = l + "";
            boolean b = lock.tryLock(str);
            System.out.println("线程"+Thread.currentThread().getName() + "是否获得到分布式锁" + b + " " + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(b){
                lock.tryUnlock(str);
            }
        }
    }

    public static void main(String[] args) {
        RedisDistributedLock lock = new RedisDistributedLock();
        for (int i = 0 ;i < 10 ; i ++){
            new MyThread(lock).start();
        }
    }
}
