package com.ktiuu.zookeeper.curator;

import com.ktiuu.zookeeper.base.ZookeeperConstant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @Create by pankun
 * @DATE 2020/12/16
 */
public class Curator_Del_Data_Sample {
    static String path = "/zk-book/c1";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(ZookeeperConstant.IP_HOST)
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath(path, "init".getBytes());
        Stat stat = new Stat();
        System.out.println(new String(client.getData().storingStatIn(stat).forPath(path)));
        client.delete().deletingChildrenIfNeeded().withVersion(stat.getVersion()).forPath(path);
    }
}
