package com.ktiuu.concurrent;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @Create by pankun
 * @DATE 2020/12/3
 */
public class ForkJoinFirstTest extends RecursiveAction {
    int begin;
    int end;

    public ForkJoinFirstTest(int begin, int end) {
        super();
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected void compute() {
        System.out.println(Thread.currentThread().getName() + "----------------");
        if(end - begin > 2){
            int middle = (end + begin) / 2;
            ForkJoinFirstTest leftAction = new ForkJoinFirstTest(begin, middle);
            ForkJoinFirstTest rightAction = new ForkJoinFirstTest(middle + 1, end);
            ForkJoinTask.invokeAll(leftAction, rightAction);
        }else{
            System.out.println("打印组合为" + begin + "-" + end);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ForkJoinPool pool = new ForkJoinPool(1);
        pool.submit(new ForkJoinFirstTest(0,10000));
        Instant now = Instant.now();
        Thread.sleep(2000);
        Instant end = Instant.now();
        System.out.println(Duration.between(now, end).toMillis());
    }
}
