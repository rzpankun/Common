package com.ktiuu.zookeeper.base;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @Create by pankun
 * @DATE 2020/12/14
 */
public class ZookeeperAction implements Watcher {
    private static CountDownLatch latch = new CountDownLatch(1);




    public static void main(String[] args) throws Exception{
        ZooKeeper zooKeeper = new ZooKeeper(ZookeeperConstant.IP_HOST, 5000, new ZookeeperAction());
        System.out.println(zooKeeper.getState());
        latch.await();

        System.out.println("Zookeeper session established");
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event : " + watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            latch.countDown();
        }
    }
}
