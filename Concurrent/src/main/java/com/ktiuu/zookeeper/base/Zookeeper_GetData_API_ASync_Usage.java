package com.ktiuu.zookeeper.base;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/15
 */
public class Zookeeper_GetData_API_ASync_Usage implements Watcher {
    private static final String PATH = "/zk-book";
    private static CountDownLatch latch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws  Exception{
        zooKeeper = new ZooKeeper(ZookeeperConstant.IP_HOST, 5000, new Zookeeper_GetData_API_ASync_Usage());
        latch.await();
        zooKeeper.create(PATH, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zooKeeper.getData(PATH, true, new IDataCallback(), null);
        zooKeeper.setData(PATH, "123".getBytes(), -1);
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            if(Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                latch.countDown();
            }else if(Event.EventType.NodeDataChanged == watchedEvent.getType()){
                zooKeeper.getData(PATH, true, new IDataCallback(), null);
            }
        }
    }
}
class IDataCallback implements AsyncCallback.DataCallback{

    @Override
    public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
        System.out.println(i + ", " + s + ", " + new String(bytes));
        System.out.println(stat.getCzxid() + "," + stat.getMzxid() + "," + stat.getVersion());
    }
}
