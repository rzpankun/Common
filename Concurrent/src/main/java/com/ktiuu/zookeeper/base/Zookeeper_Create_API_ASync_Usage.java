package com.ktiuu.zookeeper.base;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/14
 */
public class Zookeeper_Create_API_ASync_Usage implements Watcher {
    private static CountDownLatch latch  = new CountDownLatch(1);

    private static final String PATH = "/zk-test-ephemeral-";

    public static void main(String[] args) throws  Exception{
        ZooKeeper zooKeeper = new ZooKeeper(ZookeeperConstant.IP_HOST, 5000, new Zookeeper_Create_API_ASync_Usage());
        latch.await();

        zooKeeper.create(PATH, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new IStringCallback(),"i am context");
        zooKeeper.create(PATH, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new IStringCallback(),"i am context");
        zooKeeper.create(PATH, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, new IStringCallback(),"i am context");


        TimeUnit.MICROSECONDS.sleep(Integer.MAX_VALUE);
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        latch.countDown();
    }


}
class IStringCallback implements AsyncCallback.StringCallback{

    @Override
    public void processResult(int i, String s, Object o, String s1) {
        System.out.println("Create path result : [" + i + "," + s + ", " + o + ", real path name " + s1);
    }
}
