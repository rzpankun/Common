package com.ktiuu.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @Create by pankun
 * @DATE 2020/12/8
 */
public class Utils {
    public static int badPartition(int[] a ,int left , int right){
        int pivot = left;
        int index = pivot + 1;
        for (int i = index ; i <=right ; i++) {
            if(a[i] < a[pivot]){
                swap(a, i, index);
                index++;
            }
        }
        swap(a, pivot, index-1);
        return index-1;
    }

    public static int partition(int[] a , int left , int right){
        return 0;
    }

    public static int median3(int[] a , int left ,int right){
        int center = (left + right) / 2;
        if(a[center] < a[left]){
            swap(a , center, left);
        }
        if(a[right] < a[left]){
            swap(a, right, left);
        }
        if(a[right] < a[center]){
            swap(a,right, center);
        }
        swap(a,center,right-1);
        return a[right-1];
    }


    public static int[] gen(int length){
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = new Random().nextInt(10000);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] a = {1,3,4};
        int[] b = {5,6,7};
        int[] ints = mergeSort(b, a);
        System.out.println(Arrays.toString(ints));
    }


    public static void swap(int[] a , int i , int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public static int[] mergeSort(int[] a , int[] b){
        int[] ints = new int[a.length + b.length];
        int i = 0,j = 0;
        int pos = 0;
        while(i <=a.length -1&&j <= b.length -1){
            if(a[i] < b[j]){
                ints[pos++] = a[i++];
            }else{
                ints[pos++] = b[j++];
            }
        }
        while(i <= a.length -1){
            ints[pos++] = a[i++];
        }
        while(j <= b.length - 1){
            ints[pos++] = b[j++];
        }
        return ints;
    }
}
