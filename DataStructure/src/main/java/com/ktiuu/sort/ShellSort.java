package com.ktiuu.sort;

import java.util.Arrays;

/**
 * @Create by pankun
 * @DATE 2020/12/7
 */

public class ShellSort {
    public static void sort(int[] arrays){
        int a = 0;
        int length = arrays.length;
        for(int gap = (int)Math.floor(length / 2.0); gap > 0 ;gap = (int)Math.floor(gap / 2.0)){
            for (int i = gap; i < length;  i ++) {
                int j = i;
                int current = arrays[i];
                while(j - gap >= 0 && current < arrays[j-gap]){
                    arrays[j] = arrays[j-gap];
                    j = j - gap;
                    a++;
                }
                arrays[j] = current;
            }
        }
        System.out.println(a);
    }

    public static void main(String[] args) {
        int[] gen = Utils.gen(10);
        int[] clone = gen.clone();
        System.out.println(gen == clone);
        System.out.println(Arrays.toString(gen));
        System.out.println(Arrays.toString(clone));
        sort(gen);
        InsertionSort.sort(clone);
        System.out.println(Arrays.toString(gen));
        System.out.println(Arrays.toString(clone));
    }
}
