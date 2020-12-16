package com.ktiuu.zookeeper.curator;

import com.ktiuu.zookeeper.base.ZookeeperConstant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Create by pankun
 * @DATE 2020/12/16
 */
public class Curator_Create_Session_Sample {
    public static void main(String[] args) {
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(ZookeeperConstant.IP_HOST, 5000, 3000, retry);
        client.start();
        System.out.println(client);

    }
}
