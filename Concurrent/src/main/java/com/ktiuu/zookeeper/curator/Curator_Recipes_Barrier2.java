package com.ktiuu.zookeeper.curator;

import com.ktiuu.zookeeper.base.ZookeeperConstant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/17
 */
public class Curator_Recipes_Barrier2 {
    static String path = "/curator_recipes_barrier_path";

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(ZookeeperConstant.IP_HOST)
                                .sessionTimeoutMs(5000)
                                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                                .build();
                        client.start();
                        DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(client, path, 5);
                        TimeUnit.SECONDS.sleep(Math.round(Math.random()+ 3));
                        System.out.println(Thread.currentThread().getName() + "号进入barrier");
                        barrier.enter();
                        System.out.println("启动。。。");
                        TimeUnit.SECONDS.sleep(Math.round(Math.random()+ 3));
                        barrier.leave();
                        System.out.println("退出");
                    }catch(Exception e){

                    }

                }
            }).start();
        }
    }
}
