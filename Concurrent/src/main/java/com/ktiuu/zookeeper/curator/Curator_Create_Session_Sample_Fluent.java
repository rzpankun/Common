package com.ktiuu.zookeeper.curator;

import com.ktiuu.zookeeper.base.ZookeeperConstant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Create by pankun
 * @DATE 2020/12/16
 */
public class Curator_Create_Session_Sample_Fluent {
    public static void main(String[] args) {
        ExponentialBackoffRetry exponentialBackoffRetry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(ZookeeperConstant.IP_HOST).sessionTimeoutMs(5000).retryPolicy(exponentialBackoffRetry).build();
        client.start();
        System.out.println(client);
    }
}
