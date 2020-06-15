package com.ktiuu.algorithmAnalysis.chapter01;

/**
 * @Create by pankun
 * @DATE 2020/6/12
 *
 * 编写一个递归方法，它返回数N的二进制表示中1的个数，
 * 提示：利用这样的事实：如果N是奇数，那么其1的个数等于N/2二进制表示中1的个数加1
 */
public class PracticeFive {
    public static int countBinaryOne(int n){
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        if(n % 2 == 0){
            return countBinaryOne(n/2);
        }
        if(n % 2 == 1){
            return countBinaryOne(n/2) + 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(countBinaryOne(Integer.MAX_VALUE));
    }
}
