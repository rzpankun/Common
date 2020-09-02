package com.ktiuu.spring01;

/**
 * @Create by pankun
 * @DATE 2020/7/8
 */
public class B extends A {
    public B(String name, Integer age) {
        super(name, age);
    }


    public static void main(String[] args) {
        B b = new B("jfdls",11);
        b.test();
    }
}
