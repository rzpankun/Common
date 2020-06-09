package com.ktiuu.pattern;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Create by pankun
 * @DATE 2020/6/9
 */
public class StringMatch {

    /**
     * 使用Brute-Force算法暴力匹配
     * n = target.length() , m = pattern.length()
     * 最好情况时间复杂度 O(m) ,最坏时间复杂度 O(m * n)
     * @return
     */
    public static int indexOfByBruteForce(String target , String pattern , int begin){
        int n = target.length();
        int m = pattern.length();
        if(begin < 0 ){
            begin = 0;
        }
        if(n == 0 || n < m || begin >= n){
            return -1;
        }
        int i = begin, j = 0;
        while (i < n && j < m){
            if(target.charAt(i) == pattern.charAt(j)){
                i ++;
                j ++;
            }else {
                i = i-j+1;
                j = 0;
                if(n - i < m){
                    break;
                }
            }
        }
        if(j == m){
            return i-j;
        }
        return -1;
    }

    /**
     * 使用KMP算法进行模式匹配
     * KMP算法的时间复杂度是
     * O(n + m)
     * @param target
     * @param pattern
     * @param begin
     * @return
     */
    public static int indexOfByKMP(String target ,String pattern , int begin){
        int n = target.length();
        int m = pattern.length();
        if(begin < 0){
            begin = 0;
        }
        if(n == 0 || n < m || begin >= n){
            return -1;
        }
        int j = 0 ;
        int i = begin;
        int[] next = getNext(pattern);
        while (i < n && j< m) {
            if(j == -1 || target.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }else{
                j = next[j];
                if(n - i +1 < m - j + 1){
                    break;
                }
            }
        }
        if(j == m){
            return i - j;
        }
        return -1;
    }


    /**
     * 获取next数据
     * @param pattern
     * @return
     */
    public static int[] getNext(String pattern){
        int k = -1 , j = 0;
        int[] next = new int[pattern.length()];
        next[0] = -1;
        while(j < pattern.length() - 1){
            if(k == -1 || pattern.charAt(k) == pattern.charAt(j)){
                k++;
                j++;
                if(pattern.charAt(k) != pattern.charAt(j)){
                    next[j] = k;
                }else{
                    next[j] = next[k];
                }
            }else{
                k = next[k];
            }
        }
        return next;
    }




    public static Map<Character , Integer> badCharRule(String pattern){
        Map<Character , Integer> map = new HashMap<>();
        for(int i = 0 ; i < pattern.length() ; i ++){
            map.put(pattern.charAt(i),i);
        }
        return map;
    }
    public static void main(String[] args) {
        String a = "喔喔nidfla";
        Map<Character, Integer> characterIntegerMap = badCharRule(a);
        Integer i = characterIntegerMap.get('s')!=null ? characterIntegerMap.get('s'):-1;
        System.out.println(i);
        /*long l = System.nanoTime();
        System.out.println(indexOfByBruteForce("dfdfldfjldafddfsdfsadfsdfsdfsafdsdfsdfsdfsdfd", "dfd", 4));
        System.out.println(System.nanoTime() - l);


        long l1 = System.nanoTime();
        System.out.println(indexOfByKMP("dfdfldfjldafddfsdfsadfsdfsdfsafdsdfsdfsdfsdfd", "dfd", 4));
        System.out.println(System.nanoTime() - l1);*/
    }
}
