package com.ktiuu.zookeeper.base;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/15
 */
public class Zookeeper_GetChildren_API_ASync_Usage implements Watcher {
    private static CountDownLatch latch = new CountDownLatch(1);
    private static final String PATH = "/zk-book";
    private static ZooKeeper zooKeeper= null;

    public static void main(String[] args) throws Exception{
        zooKeeper = new ZooKeeper(ZookeeperConstant.IP_HOST, 5000, new Zookeeper_GetChildren_API_ASync_Usage());
        latch.await();

        zooKeeper.create(PATH + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        zooKeeper.getChildren(PATH, true, new IChildren2Callback(), null);

        zooKeeper.create(PATH+"/c2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            if(Event.EventType.None == watchedEvent.getType() && null ==watchedEvent.getPath()){
                latch.countDown();
            }else if(Event.EventType.NodeChildrenChanged == watchedEvent.getType()){
                try {
                    System.out.println("ReGetChild : "+zooKeeper.getChildren(PATH, true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
class IChildren2Callback implements AsyncCallback.Children2Callback{

    @Override
    public void processResult(int i, String s, Object o, List<String> list, Stat stat) {
        System.out.println("Get Children znode result : [response code:" + i + ",param path :" + s + ", ctx :" + o + ", children list :" +list + ", stat : " +stat );
    }
}

