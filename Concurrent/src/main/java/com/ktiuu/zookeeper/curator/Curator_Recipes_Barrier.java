package com.ktiuu.zookeeper.curator;

import com.ktiuu.zookeeper.base.ZookeeperConstant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/17
 */
public class Curator_Recipes_Barrier {
    static String path = "/curator_recipes_barrier_path";
    static DistributedBarrier barrier;

    public static void main(String[] args) throws Exception{
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
                        barrier = new DistributedBarrier(client, path);
                        System.out.println(Thread.currentThread().getName() + "号barrier设置");

                        barrier.setBarrier();
                        barrier.waitOnBarrier();
                    }catch(Exception e){

                    }
                    System.out.println("启动");
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(2);
        barrier.removeBarrier();
    }
}
