package com.ktiuu.sort;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * @Create by pankun
 * @DATE 2020/12/8
 */
public class QuickSort {
    private static final int CUT_OFF = 100;
    public static void sort(int[] a){
        sort(a, 0, a.length-1);
    }

    private static void sort(int[] a ,int left ,int right){
        if(left + CUT_OFF <= right){
            int pivot = median3(a, left, right);
            int i = left, j = right - 1;
            for(;;){
                while(a[++i] < pivot){
                }
                while(a[--j]>pivot){
                }
                if(i < j){
                    Utils.swap(a, i, j);
                }else {
                    break;
                }
            }
            Utils.swap(a, i, right - 1);
            sort(a, left, i-1);
            sort(a, i+1, right);
        }else{
            InsertionSort.sort(a,left,right);
        }
    }

    public static int median3(int[] a , int left ,int right){
        int center = (left + right) / 2;
        if(a[left] > a[center]){
            Utils.swap(a, left, center);
        }
        if(a[right] < a[left]){
            Utils.swap(a, left, right);
        }
        if(a[center] > a[right]){
            Utils.swap(a, center, right);
        }
        Utils.swap(a, center, right -1);
        return a[right-1];
    }


    public static void main(String[] args) {
        int[] gen = Utils.gen(1000000);
        Instant start = Instant.now();
        sort(gen);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }
}
