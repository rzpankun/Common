package com.ktiuu.zookeeper.curator;

import com.ktiuu.zookeeper.base.ZookeeperConstant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Op;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Create by pankun
 * @DATE 2020/12/16
 */
public class Curator_Create_Node_Backgroud_Sample {
    static String path = "/zk-book";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(ZookeeperConstant.IP_HOST)
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();
    static CountDownLatch latch = new CountDownLatch(2);
    static ExecutorService tp = Executors.newFixedThreadPool(2);
    public static void main(String[] args) throws  Exception {
        client.start();
        System.out.println("Main Thread : " + Thread.currentThread().getName());
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("event[code:" + curatorEvent.getResultCode() + ",type:" + curatorEvent.getType() + "]");
                System.out.println("Thread of process Result:" + Thread.currentThread().getName());
                latch.countDown();
            }
        }, tp).forPath(path,"init".getBytes());


        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("event[code:" + curatorEvent.getResultCode() + ",type:" + curatorEvent.getType() + "]");
                System.out.println("Thread of process Result:" + Thread.currentThread().getName());
                latch.countDown();
            }
        }).forPath(path,"init".getBytes());

        latch.await();
        tp.shutdown();
    }
}
