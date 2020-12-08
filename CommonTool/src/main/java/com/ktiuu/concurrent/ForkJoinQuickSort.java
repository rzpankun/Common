package com.ktiuu.concurrent;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @Create by pankun
 * @DATE 2020/12/8
 */
public class ForkJoinQuickSort extends RecursiveAction {
    private static final int CUT_OFF = 100;
    int[] a;
    int begin;
    int end;

    public ForkJoinQuickSort(int[] a, int begin, int end) {
        this.a = a;
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected void compute() {
        if(begin + CUT_OFF < end){
            int pivot = median3(a, begin, end);
            int i = begin, j = end - 1;
            for(;;){
                while(a[++i] < pivot){
                }
                while(a[--j]>pivot){
                }
                if(i < j){
                    swap(a, i, j);
                }else {
                    break;
                }
            }
            swap(a, i, end - 1);
            ForkJoinQuickSort leftSort = new ForkJoinQuickSort(a, begin, i - 1);
            ForkJoinQuickSort rightSort = new ForkJoinQuickSort(a, begin, i + 1);
            leftSort.fork();
            rightSort.compute();
            leftSort.join();
        }else{
            ForkJoinMergeSort.insertionSort(a, begin, end);
        }
    }



    public static int median3(int[] a , int left ,int right){
        int center = (left + right) / 2;
        if(a[left] > a[center]){
            swap(a, left, center);
        }
        if(a[right] < a[left]){
            swap(a, left, right);
        }
        if(a[center] > a[right]){
            swap(a, center, right);
        }
        swap(a, center, right -1);
        return a[right-1];
    }

    public static void swap(int[] a , int i , int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] gen = gen(1000000);
        Instant start = Instant.now();
        pool.invoke(new ForkJoinQuickSort(gen, 0, gen.length-1));
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }

    public static int[] gen(int length){
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = new Random().nextInt(10000);
        }
        return array;
    }
}
