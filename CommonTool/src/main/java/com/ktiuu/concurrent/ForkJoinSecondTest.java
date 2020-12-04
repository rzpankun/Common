package com.ktiuu.concurrent;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @Create by pankun
 * @DATE 2020/12/4
 */
public class ForkJoinSecondTest extends RecursiveTask<Long> {
    private static long threshold = 2500000000l;
    long  begin;
    long end;

    public ForkJoinSecondTest(long begin, long end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Long compute() {
        System.out.println("当前线程是" + Thread.currentThread().getName());
        if(end - begin > threshold){
            long middle = (end + begin) / 2;
            ForkJoinSecondTest leftTest = new ForkJoinSecondTest(begin, middle);
            ForkJoinSecondTest rightTest = new ForkJoinSecondTest(middle + 1, end);
            leftTest.fork();
            rightTest.fork();
            return leftTest.join() + rightTest.join();
        }else{
            long a = 0l;
            for (long i = begin  ; i <= end ; i++) {
                a+=i;
            }
            return a;
        }
    }

    public static void main(String[] args) {
        Instant begin = Instant.now();
        test1(0,10000000000l);
        Instant end = Instant.now();
        System.out.println(Duration.between(begin, end).toMillis());
    }
    public static void test1(long begin1,long end1){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Long invoke = forkJoinPool.invoke(new ForkJoinSecondTest(begin1, end1));
        System.out.println(invoke);
    }

    public static void test2(long begin1,long end1){
        long a = 0l;
        for (long i = begin1; i <=end1 ; i++) {
            a += i;
        }
        System.out.println(a);
    }

    public static void test3(long begin1,long end1){
        long reduce = LongStream.rangeClosed(begin1, end1).parallel().reduce(0, Long::sum);
        System.out.println(reduce);
    }
}
