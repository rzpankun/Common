package com.ktiuu.examle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @Create by pankun
 * @DATE 2020/5/26
 * 给定一个输入参数输出给定对数的小括号
 * 1.小括号对具有严格的对称性
 * 2.以左括号和右括号为研究对象都可以
 * 3.怎么去除重复（使用set集合的特性）
 */
public class PrintCurves {
    private static final String STR_PARENS = "()";


    /**
     *
     * @param count
     * @return
     *
     *
     *
     *
     *
     */
    public static Set<String> generateParens(int count){
        Set<String> set = new HashSet<>();
        if(count == 0){
            set.add("");
        }else{
            Set<String> strings = generateParens(count - 1);
            for (String str : strings){
                for (int i = 0; i < str.length(); i++) {
                    if(str.charAt(i) == '('){
                        String s = insertInside(str, i);
                        set.add(s);
                    }
                }
                if(!set.contains(STR_PARENS + str)){
                    set.add(STR_PARENS + str);
                }
            }
        }
        return set;
    }

    private static String insertInside(String str , int index){
        String sub1 = str.substring(0, index + 1);
        String sub2 = str.substring(index + 1, str.length());
        return sub1 + STR_PARENS + sub2;
    }




    public static void addParen(ArrayList<String> list , int leftRem , int rightRem , char[] str , int count){
        if(leftRem < 0 || rightRem < 0 ){
            return;
        }
        if(leftRem == 0 && rightRem == 0){
            String s = String.copyValueOf(str);
            list.add(s);
        }else{
            if(leftRem > 0){
                str[count] = '(';
                addParen(list, leftRem - 1 , rightRem, str, count + 1);
            }
            if(rightRem > leftRem){
                str[count] = ')';
                addParen(list, leftRem, rightRem - 1 , str, count+1);
            }
            /*if(rightRem > leftRem){
                str[count] = ')';
                addParen(list, leftRem, rightRem - 1 , str, count+1);
            }*/
        }
    }
    public static void main(String[] args) {
        int count = 10;
        long l1 = System.currentTimeMillis();
        Set<String> strings1 = generateParens(count);
        /*for (String s : strings1) {
            System.out.println(s);
        }*/
        System.out.println(strings1.size());
        long l2 = System.currentTimeMillis() - l1;
        System.out.println(l2);


        long l3 = System.currentTimeMillis();
        char[] str = new char[count * 2];
        ArrayList<String> strings = new ArrayList<>();
        addParen(strings, count, count, str, 0);
        /*for (String string : strings) {
            System.out.println(string);
        }*/
        System.out.println(strings.size());
        long l = System.currentTimeMillis() - l3;
        System.out.println(l);
    }
}
