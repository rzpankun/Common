package com.ktiuu.zookeeper.base;

import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/14
 */
public class Zookeeper_GetChildren_API_Sync_Usage implements Watcher {
    private static CountDownLatch latch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;
    private static final String PATH = "/zk-book";

    public static void main(String[] args) throws  Exception{
        zooKeeper = new ZooKeeper(ZookeeperConstant.IP_HOST, 5000, new Zookeeper_GetChildren_API_Sync_Usage());
        latch.await();
        zooKeeper.create(PATH, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create(PATH + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        List<String> children = zooKeeper.getChildren(PATH, true);
        System.out.println(children);
        zooKeeper.create(PATH+"/c2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);


        TimeUnit.MICROSECONDS.sleep(Integer.MAX_VALUE);
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            if(Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                latch.countDown();
            }else if(Event.EventType.NodeChildrenChanged == watchedEvent.getType()){
                try {
                    System.out.println("ReGetChild :" + zooKeeper.getChildren(PATH, true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
