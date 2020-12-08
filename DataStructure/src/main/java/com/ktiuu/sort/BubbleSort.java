package com.ktiuu.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @Create by pankun
 * @DATE 2020/12/7
 * 冒泡排序
 * 1.依次比较相邻的两个元素
 * 2.交换两个元素,从第一队开始一直交换到结尾，这样在最后的元素一定是最大的数
 * 3.针对所有元素重复以上步骤，除了最后一个（已经是最大值，不用比较）
 * 4.重复以上步骤
 */
public class BubbleSort {
    public static int[] sort(int[] arrays){
        int length = arrays.length;
        for(int i = 0 ; i < length -1 ; i ++){
            for(int j = 0 ; j < length - 1 -i ; j ++){
                if(arrays[j] > arrays[j+1]){
                    int temp = arrays[j];
                    arrays[j] = arrays[j+1];
                    arrays[j+1] = temp;
                }
            }
        }
        return arrays;
    }

    public static void main(String[] args) {
        int[] gen = Utils.gen(10);
        System.out.println(Arrays.toString(gen));
        sort(gen);
        System.out.println(Arrays.toString(gen));
    }


}
