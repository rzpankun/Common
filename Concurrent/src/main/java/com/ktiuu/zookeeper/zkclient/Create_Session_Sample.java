package com.ktiuu.zookeeper.zkclient;

import com.ktiuu.zookeeper.base.ZookeeperConstant;
import org.I0Itec.zkclient.ZkClient;

/**
 * @Create by pankun
 * @DATE 2020/12/15
 */
public class Create_Session_Sample {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient(ZookeeperConstant.IP_HOST, 10000);
        System.out.println("Zookeeper session established " + zkClient);
        /*String path = "/zk-book/c1";
        zkClient.createPersistent(path, true);*/

        zkClient.deleteRecursive("/zk-book");
    }
}
