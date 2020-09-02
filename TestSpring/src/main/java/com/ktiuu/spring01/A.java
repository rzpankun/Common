package com.ktiuu.spring01;

import com.sun.org.apache.xml.internal.utils.Hashtree2Node;
import io.netty.channel.socket.SocketChannel;

import java.nio.channels.ServerSocketChannel;

/**
 * @Create by pankun
 * @DATE 2020/7/8
 */
public class A {
    private String name;
    private Integer age;
    public A() {
    }

    public A(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void test(){
        System.out.println(name + "_" + age);
    }

    public static void main(String[] args) {
    }
}
