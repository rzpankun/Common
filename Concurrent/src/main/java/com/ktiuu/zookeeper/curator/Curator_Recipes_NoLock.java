package com.ktiuu.zookeeper.curator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @Create by pankun
 * @DATE 2020/12/17
 */
public class Curator_Recipes_NoLock {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        countDownLatch.await();
                    }catch(Exception e){

                    }
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss|SSS");
                    String format1 = format.format(new Date());
                    System.out.println("生成的订单号是：" + format1);
                }
            }).start();
        }

        countDownLatch.countDown();
    }
}
