package com.ktiuu;

import com.ktiuu.spring01.Phone;
import com.ktiuu.spring01.User;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Hello world!
 *
 */
@Configuration
public class App 
{
    String a;
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
//        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("application.xml"));
//        App app = (App)xmlBeanFactory.getBean("app");
//       ApplicationContext applicationContext = new AnnotationConfigApplicationContext(App.class);
       /* App app = (App)applicationContext.getBean("app");
        app.test();*/
        User user = (User)applicationContext.getBean("user");
        System.out.println(user);
        Phone phone = (Phone) applicationContext.getBean("phone");
        System.out.println(phone);
    }


    @Bean(name = "app1")
    public App app(){
        App app = new App();
        app.setA("pankun");
        return app;
    }
    public void setA(String a){
        this.a = a ;
    }
    public void test(){
        System.out.println(this.a);
    }

}
