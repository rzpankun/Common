package com.ktiuu.zookeeper.curator;

import com.ktiuu.zookeeper.base.ZookeeperConstant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/16
 */
public class Curator_PathChildrenCache_Sample {
    static String path = "/zk-book";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(ZookeeperConstant.IP_HOST)
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 1))
            .build();

    public static void main(String[] args) throws  Exception{
        client.start();
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                switch (pathChildrenCacheEvent.getType()){
                    case CHILD_ADDED:
                        System.out.println("CHILD_ADDED" + pathChildrenCacheEvent.getData().getPath());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("CHILD_UPDATED" + pathChildrenCacheEvent.getData().getPath());
                        break;
                    case CHILD_REMOVED:
                        System.out.println("CHILD_REMOVED" + pathChildrenCacheEvent.getData().getPath());
                        break;
                    default:
                        break;
                }
            }
        });
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
        TimeUnit.SECONDS.sleep(1);
        client.create().withMode(CreateMode.PERSISTENT).forPath(path + "/c1");
        TimeUnit.SECONDS.sleep(1);
        client.delete().forPath(path + "/c1");
    }
}
