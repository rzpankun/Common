package com.ktiuu;

import org.springframework.aop.BeforeAdvice;

/**
 * @Create by pankun
 * @DATE 2020/6/24
 */
public class AopBefore  {
    public void check(){
        System.out.println("正在验证");
    }
}
