package com.ktiuu.zookeeper.base;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/15
 */
public class Zookeeper_SetData_API_ASync_Usage implements Watcher {
    private static CountDownLatch latch = new CountDownLatch(1);
    private static final String PATH = "/zk-name";
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception{
        zooKeeper = new ZooKeeper(ZookeeperConstant.IP_HOST, 5000, new Zookeeper_SetData_API_ASync_Usage());
        latch.await();
        zooKeeper.create(PATH, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zooKeeper.setData(PATH, "456".getBytes(), -1, new IStatCallback(), null);
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            if(Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                latch.countDown();
            }
        }
    }
}
class IStatCallback implements AsyncCallback.StatCallback{

    @Override
    public void processResult(int i, String s, Object o, Stat stat) {
        if( i == 0){
            System.out.println("SUCCESS");
        }
    }
}