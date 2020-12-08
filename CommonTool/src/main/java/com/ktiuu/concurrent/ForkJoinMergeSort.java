package com.ktiuu.concurrent;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

/**
 * @Create by pankun
 * @DATE 2020/12/8
 */
public class ForkJoinMergeSort extends RecursiveAction {
    private static final int CUT_OFF = 20;
    int[] a;
    int[] temp;
    int begin;
    int end;

    public ForkJoinMergeSort(int[] a,int[] temp, int begin, int end) {
        this.a = a;
        this.temp = temp;
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected void compute() {
//        System.out.println("Thread" + Thread.currentThread().getName());
        if(begin + CUT_OFF <  end){
            int center = (begin + end) / 2;
            ForkJoinMergeSort leftSort = new ForkJoinMergeSort(a,temp ,begin, center);
            ForkJoinMergeSort rightSort = new ForkJoinMergeSort(a, temp,center + 1, end);
            ForkJoinTask<Void> fork = leftSort.fork();
            rightSort.compute();
            fork.join();
            //invokeAll(leftSort, rightSort);
            merge(a,temp, begin, center+1, end);
        }else{
            insertionSort(a, begin, end);
        }
    }

    public static void insertionSort(int[] arrays ,int begin,int end){
        int length = end - begin + 1;
        for (int i =begin + 1; i <= end; i++) {
            int temp = arrays[i];
            int preIndex = i -1;
            while(preIndex >= begin && arrays[preIndex] > temp){
                arrays[preIndex + 1] = arrays[preIndex];
                preIndex--;
            }
            arrays[preIndex + 1] = temp;
        }
    }


    private void merge(int[] a ,int[] temp, int leftPos , int rightPos , int rightEnd){
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        while(leftPos <= leftEnd && rightPos <= rightEnd){
            if(a[leftPos] < a[rightPos]){
                temp[tempPos++] = a[leftPos++];
            }else {
                temp[tempPos++] = a[rightPos++];
            }
        }
        while(leftPos <= leftEnd){
            temp[tempPos++] = a[leftPos++];
        }
        while(rightPos <= rightEnd){
            temp[tempPos++] = a[rightPos++];
        }
        for(int i = 0; i < numElements;i++ ,rightEnd--){
            a[rightEnd] = temp[rightEnd];
        }
    }

    public static void main(String[] args) {
        /*ForkJoinPool pool = new ForkJoinPool(1);
        int[] gen = gen(1000000);
        int[] temp = new int[gen.length];
//        System.out.println(Arrays.toString(gen));
        Instant start = Instant.now();
        pool.invoke(new ForkJoinMergeSort(gen, temp,0, gen.length - 1));
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());*/
//        System.out.println(Arrays.toString(gen));
        /*int[] gen = gen(10);
        System.out.println(Arrays.toString(gen));
        insertionSort(gen, 3, gen.length-1);
        System.out.println(Arrays.toString(gen));*/

        Random random = new Random();
        IntStream ints = random.ints(0, 10000);
        Instant start = Instant.now();
        int[] ints1 = ints.limit(1000000).parallel().sorted().toArray();
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        System.out.println(ints1.length);
//        System.out.println(Arrays.toString(ints1));
    }


    public static int[] gen(int length){
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = new Random().nextInt(10000);
        }
        return array;
    }
}
