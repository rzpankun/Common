package com.ktiuu.zookeeper.zkclient;

import com.ktiuu.zookeeper.base.ZookeeperConstant;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/16
 */
public class ZKClient_Get_Date_Sample {
    public static void main(String[] args) throws Exception{
        String path = "/zk-book";
        ZkClient zkClient = new ZkClient(ZookeeperConstant.IP_HOST, 10000);
        zkClient.createEphemeral(path,"123");
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("Node " + s +" changed,new data :" + o );
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("Node " + s +"deleted");
            }
        });

        System.out.println(zkClient.readData(path).toString());
        zkClient.writeData(path, "456");
        TimeUnit.SECONDS.sleep(1);
        zkClient.delete(path);
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
