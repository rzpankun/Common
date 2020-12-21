package com.ktiuu.zookeeper.curator;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Create by pankun
 * @DATE 2020/12/17
 */
public class Curator_Recipes_CyclicBarrier {

    public static CyclicBarrier barrier = new CyclicBarrier(3);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new Runner("1号选手"));
        executorService.submit(new Runner("2号选手"));
        executorService.submit(new Runner("3号选手"));
        executorService.shutdown();
    }
}
class Runner implements Runnable{
    String name;

    public Runner(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try{
            Curator_Recipes_CyclicBarrier.barrier.await();
        }catch(Exception e){

        }
        System.out.println(name + "起跑！at :" + System.currentTimeMillis());
    }
}
