package com.ktiuu.spring01;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @Create by pankun
 * @DATE 2020/6/28
 */
public class MyNamespaceHandlerSupport extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
        registerBeanDefinitionParser("phone", new PhoneBeanDefinitionParser());
    }
}
