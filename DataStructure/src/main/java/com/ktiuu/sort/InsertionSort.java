package com.ktiuu.sort;

import java.util.Arrays;

/**
 * @Create by pankun
 * @DATE 2020/12/7
 */
public class InsertionSort {
    public static void sort(int[] arrays){
        sort(arrays, 0, arrays.length -1);
    }

    public static void sort(int[] arrays , int begin , int end){
        for (int i = begin +1; i <= end; i++) {
            int temp = arrays[i];
            int preIndex = i -1;
            while(preIndex >= begin && arrays[preIndex] > temp){
                arrays[preIndex + 1] = arrays[preIndex];
                preIndex--;
            }
            arrays[preIndex + 1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] gen = Utils.gen(10);
        System.out.println(Arrays.toString(gen));
        sort(gen);
        System.out.println(Arrays.toString(gen));
    }
}
