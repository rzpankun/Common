package com.ktiuu.concurrent.jucaction;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/14
 */
public class CountDownLancherAction {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        System.out.println("Thread is reday to run" + Thread.currentThread().getName());
                        countDownLatch.await();
                        System.out.println("Thread is running " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(3);
        countDownLatch.countDown();
      //  TimeUnit.SECONDS.sleep(3);
    }
}
