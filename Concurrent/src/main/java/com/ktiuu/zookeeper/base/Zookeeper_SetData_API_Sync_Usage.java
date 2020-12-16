package com.ktiuu.zookeeper.base;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/15
 */
public class Zookeeper_SetData_API_Sync_Usage implements Watcher {
    private static final String PATH = "/zk-book";
    private static CountDownLatch latch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception{
        zooKeeper = new ZooKeeper(ZookeeperConstant.IP_HOST, 5000, new Zookeeper_SetData_API_Sync_Usage());
        latch.await();

        zooKeeper.create(PATH, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        zooKeeper.getData(PATH, true, null);

        Stat stat = zooKeeper.setData(PATH, "456".getBytes(), -1);
        System.out.println(stat.getCzxid() + "," + stat.getMzxid() + "," + stat.getVersion());
        Stat stat1 = zooKeeper.setData(PATH, "456".getBytes(), stat.getVersion());
        System.out.println(stat1.getCzxid() + "," + stat1.getMzxid() + "," + stat1.getVersion());

        try{
            zooKeeper.setData(PATH, "456".getBytes(),stat.getVersion());
        }catch (KeeperException e){
            System.out.println(e.code() + ", " + e.getMessage());
        }

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
