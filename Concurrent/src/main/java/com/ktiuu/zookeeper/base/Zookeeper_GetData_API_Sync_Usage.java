package com.ktiuu.zookeeper.base;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/15
 */
public class Zookeeper_GetData_API_Sync_Usage implements Watcher {
    private static CountDownLatch latch = new CountDownLatch(1);
    private static final String PATH = "/zk-book";
    private static ZooKeeper zooKeeper = null;
    private static Stat stat = new Stat();
    public static void main(String[] args) throws  Exception{
        zooKeeper = new ZooKeeper(ZookeeperConstant.IP_HOST, 5000, new Zookeeper_GetData_API_Sync_Usage()) ;
        latch.await();
        zooKeeper.create(PATH, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(new String(zooKeeper.getData(PATH, true, stat)));
        System.out.println(stat.getCzxid() + "," + stat.getMzxid() + "," +stat.getVersion());
        zooKeeper.setData(PATH, "123".getBytes(), -1);
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            if(Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                latch.countDown();
            }else if(Event.EventType.NodeDataChanged == watchedEvent.getType()){
                try {
                    System.out.println(new String(zooKeeper.getData(PATH, true, stat)));
                    System.out.println(stat.getCzxid() + "," + stat.getMzxid() + "," +stat.getVersion());
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
