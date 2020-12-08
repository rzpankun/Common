package com.ktiuu.sort;

import java.util.Arrays;

/**
 * @Create by pankun
 * @DATE 2020/12/7
 *
 */
public class SelectionSort {
    public static void sort(int[] arrays){
        int length = arrays.length;
        int minIndex,temp;
        for (int i = 0; i < length -1; i++) {
            minIndex = i;
            for (int j = i+1; j < length; j++) {
                if(arrays[j] < arrays[minIndex]){
                    minIndex = j;
                }
            }
            temp = arrays[i];
            arrays[i] = arrays[minIndex];
            arrays[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] gen = Utils.gen(10);
        System.out.println(Arrays.toString(gen));
        sort(gen);
        System.out.println(Arrays.toString(gen));
    }
}
