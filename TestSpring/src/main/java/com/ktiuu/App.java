package com.ktiuu;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Hello world!
 *
 */
public class App 
{
    String a;
    public static void main( String[] args ) {
//        ClassPathXmlApplicationContext
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("application.xml"));
        App app = (App)xmlBeanFactory.getBean("app");
        app = (App)xmlBeanFactory.getBean("app");
        app.test();
    }


    public void setA(String a){
        this.a = a ;
    }
    public void test(){
        System.out.println(this.a);
    }

}
