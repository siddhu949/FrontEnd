package com.aditya.spring.J2EE;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aditya.spring.J2EE.pojo.Test;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext ap =new ClassPathXmlApplicationContext("com/aditya/spring/J2EE/resources/spring.xml");
      Object o=  ap.getBean("t");
      Test t=(Test)o;
      t.hello();
}
}
