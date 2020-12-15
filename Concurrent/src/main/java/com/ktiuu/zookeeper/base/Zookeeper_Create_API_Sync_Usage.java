package com.ktiuu.zookeeper.base;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/14
 */
public class Zookeeper_Create_API_Sync_Usage implements Watcher {
    private static CountDownLatch latch = new CountDownLatch(1);
    private static final String PATH = "/zk-test-ephemeral-";
    public static void main(String[] args) throws Exception{
        ZooKeeper zooKeeper = new ZooKeeper(ZookeeperConstant.IP_HOST, 5000, new Zookeeper_Create_API_Sync_Usage());
        latch.await();
        String s = zooKeeper.create(PATH, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Success create znode : " + s);
        String s1 = zooKeeper.create(PATH, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Success create znode : " + s1);

        TimeUnit.SECONDS.sleep(10);
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            latch.countDown();
        }
    }
}
