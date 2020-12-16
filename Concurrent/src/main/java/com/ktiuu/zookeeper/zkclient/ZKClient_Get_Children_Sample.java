package com.ktiuu.zookeeper.zkclient;

import com.ktiuu.zookeeper.base.ZookeeperConstant;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/16
 */
public class ZKClient_Get_Children_Sample {
    public static void main(String[] args) throws Exception{
        String path = "/zk-book";
        ZkClient zkClient = new ZkClient(ZookeeperConstant.IP_HOST, 10000);
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println(s + "'s child changed,cunrrentChilds :" + list);
            }
        });

        zkClient.createPersistent(path );
        TimeUnit.SECONDS.sleep(1);
        System.out.println(zkClient.getChildren(path));
        TimeUnit.SECONDS.sleep(1);
        zkClient.createPersistent(path+"/c1");
        TimeUnit.SECONDS.sleep(1);
        zkClient.delete(path+"/c1");
        TimeUnit.SECONDS.sleep(1);
        zkClient.delete(path);


        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);

    }
}
