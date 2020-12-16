package com.ktiuu.zookeeper.curator;

import com.ktiuu.zookeeper.base.ZookeeperConstant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/16
 */
public class Curator_Create_Node_Sample {
    static String PATH = "/zk-book/c1";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(ZookeeperConstant.IP_HOST)
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        String s = client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(PATH, "init".getBytes());
        System.out.println(s);
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
