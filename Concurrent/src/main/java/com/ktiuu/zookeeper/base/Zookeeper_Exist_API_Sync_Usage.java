package com.ktiuu.zookeeper.base;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/15
 */
public class Zookeeper_Exist_API_Sync_Usage implements Watcher {
    private static CountDownLatch latch = new CountDownLatch(1);
    private static final String PATH = "/zk-name";
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception{
        zooKeeper = new ZooKeeper(ZookeeperConstant.IP_HOST, 5000, new Zookeeper_Exist_API_Sync_Usage());
        latch.await();
        zooKeeper.exists(PATH, true);
        zooKeeper.create(PATH, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.setData(PATH, "123".getBytes(), -1);
        zooKeeper.create(PATH + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.delete(PATH + "/c1", -1);
        zooKeeper.delete(PATH, -1);
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);

    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        try{
            if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
                if(Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                    latch.countDown();
                }else if(Event.EventType.NodeCreated == watchedEvent.getType()){
                    System.out.println("Node(" + watchedEvent.getPath() + ")Created");
                    zooKeeper.exists(watchedEvent.getPath(), true);
                }else if(Event.EventType.NodeDeleted == watchedEvent.getType()){
                    System.out.println("Node (" + watchedEvent.getPath() + ") Deleted");
                    zooKeeper.exists(watchedEvent.getPath(), true);
                }else if(Event.EventType.NodeDataChanged == watchedEvent.getType()){
                    System.out.println("Node (" + watchedEvent.getPath() + ") DataChanged");
                    zooKeeper.exists(watchedEvent.getPath(), true);
                }
            }
        }catch (Exception e){

        }
    }
}
