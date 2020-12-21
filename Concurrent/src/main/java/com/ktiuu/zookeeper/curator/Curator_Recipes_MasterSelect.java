package com.ktiuu.zookeeper.curator;

import com.ktiuu.zookeeper.base.ZookeeperConstant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/16
 */
public class Curator_Recipes_MasterSelect {
    static String master_path = "/curator_recipes_master_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(ZookeeperConstant.IP_HOST)
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws  Exception{
        client.start();
        LeaderSelector leaderSelector = new LeaderSelector(client, master_path, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                CountDownLatch countDownLatch = new CountDownLatch(1);
                System.out.println("成为Master角色");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            TimeUnit.SECONDS.sleep(3);
                            countDownLatch.countDown();
                        }catch (Exception e){

                        }
                    }
                }).start();
                countDownLatch.await();
                System.out.println("完成Master操作，释放Master权利");
            }
        });
        leaderSelector.autoRequeue();
        leaderSelector.start();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
