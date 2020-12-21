package com.ktiuu.zookeeper.curator;

import com.ktiuu.zookeeper.base.ZookeeperConstant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @Create by pankun
 * @DATE 2020/12/17
 */
public class Curator_Recipes_Lock {
    static String path = "/curator_recipes_lock_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(ZookeeperConstant.IP_HOST)
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) {
        client.start();
        InterProcessMutex lock = new InterProcessMutex(client, path);
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        latch.await();
                        lock.acquire();
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss|SSS");
                    String format = simpleDateFormat.format(new Date());
                    System.out.println("生成的订单号是：" + format);
                    try{
                        lock.release();
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }
            }).start();
        }
        latch.countDown();
    }
}
