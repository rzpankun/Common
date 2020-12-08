package com.ktiuu.sort;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * @Create by pankun
 * @DATE 2020/12/8
 */
public class MergeSort {
    private static final int CUT_OFF = 20;
    public static void sort(int[] a){
        int[] temp = new int[a.length];
        sort(a, temp,0, a.length-1);
    }

    private static void sort(int[] a ,int[] temp, int begin , int end){
        if(begin +CUT_OFF < end){
            int center = (begin + end) / 2;
            sort(a,temp, begin, center);
            sort(a,temp, center+1, end);
            merge(a,temp, begin, center+1,end);
        }else {
            InsertionSort.sort(a, begin, end);
        }
    }

    private static void merge(int[] a ,int[] temp, int leftPos,int rightPos , int rightEnd){
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
        int[] gen = Utils.gen(1000000);
//        System.out.println(Arrays.toString(gen));
        Instant start = Instant.now();
        sort(gen);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
//        System.out.println(Arrays.toString(gen));
    }
}
