package com.ktiuu.algorithmAnalysis.chapter01;

import java.util.ArrayList;

/**
 * @Create by pankun
 * @DATE 2020/6/12
 * 编写带有下列声明的例程
 * public void permute(String str)
 * public void permute(Char[] str, int low , int high)
 * 第一个例程是驱动程序，它调用第二个例程并显示str中字符的所有排列
 */
public class PracticeSix {
    public void permute(String str){
        permute(str.toCharArray(), 0, str.length() - 1);
    }

    public void permute(char[] str , int low ,int high){
        if(low == high){
            System.out.println(new String(str));
        }else{
            for(int i = low ; i <= high ; i ++){
                char temp = str[low];
                str[low] = str[i];
                str[i] = temp;
                permute(str , low + 1 , high);
                temp =  str[low];
                str[low] = str[i];
                str[i] = temp;
            }
        }

    }

    public static void main(String[] args) {
        new PracticeSix().permute("abc");
    }
}
