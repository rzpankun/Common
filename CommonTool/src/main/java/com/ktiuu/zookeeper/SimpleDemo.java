package com.ktiuu.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @Create by pankun
 * @DATE 2020/6/2
 */
public class SimpleDemo {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("39.105.198.99:2181", 3000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("已经触发了" + watchedEvent.getType() + "事件！");
            }
        });
        /*zooKeeper.create("/testRootPath","testRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create("/testRootPath/testChildPathOne","testChildDataOne".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        String testRootPath = new String(zooKeeper.getData("/testRootPath", false, null));
        System.out.println(testRootPath);
        System.out.println(zooKeeper.getChildren("/testRootPath", true));
        zooKeeper.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(), -1);
        System.out.println(zooKeeper.exists("/testRootPath", true));
        zooKeeper.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(new String(zooKeeper.getData("/testRootPath/testChildPathTwo", true, null)));
        zooKeeper.delete("/testRootPath/testChildPathTwo", -1);
        zooKeeper.delete("/testRootPath/testChildPathOne", -1);
        zooKeeper.delete("/testRootPath", -1);*/
        zooKeeper.close();
    }
}
