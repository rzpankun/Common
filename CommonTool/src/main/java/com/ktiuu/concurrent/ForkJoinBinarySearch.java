package com.ktiuu.concurrent;

import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Create by pankun
 * @DATE 2020/12/4
 */
public class ForkJoinBinarySearch extends RecursiveTask<Integer> {
    private static final int limit = Runtime.getRuntime().availableProcessors();
    static int threshold;
    Integer[] a;
    Integer target;

    public ForkJoinBinarySearch(Integer[] a,Integer target) {
        this.a = a;
        this.target = target;
        if(threshold == 0){
            threshold = a.length / limit;
        }
    }

    @Override
    protected Integer compute() {
        if(a.length > threshold){
            Integer i = a.length / 2;
            Integer[] left = (Integer[])Arrays.copyOfRange(a, 0, i);
            Integer[] right = (Integer[])Arrays.copyOfRange(a, i, a.length);
            ForkJoinBinarySearch leftSearch = new ForkJoinBinarySearch(left,target);
            ForkJoinBinarySearch rightSearch = new ForkJoinBinarySearch(right,target);
            ForkJoinTask<Integer> fork = leftSearch.fork();
            Integer compute = rightSearch.compute();
            Integer join = fork.join();
            return  join == null ? compute : join;
        }else{
            if(a[0]<=target && a[a.length -1] >=target){
                return Search(a, target);
            }else{
                return null;
            }
        }
    }




    public static Integer Search(Integer[] a , Integer target){
        for (int i = 0; i < a.length; i++) {
            if(a[i] == target){
                return a[i];
            }
        }
        return null;
    }
}
