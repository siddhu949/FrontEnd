package com.bean.BeanFactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.aditya.spring.J2EE.ApplicatonContext;
import com.aditya.spring.J2EE.ClasspathXmlApplicationContext;
import com.bean.BeanFactory.bean.Test;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
       //load the resource
    	Resource r = new ClassPathResource("com/bean/BeanFactory/resources/resource.xml");
    	// Create BeanFactory from XML
        BeanFactory factory = new XmlBeanFactory(r);
        BeanFactory f1 = new XmlBeanFactory(r);
        BeanFactory f2 = new XmlBeanFactory(r);
        
       
        //bean object
        Object o =factory.getBean("t");
        Object o1 =f1.getBean("t");
        Object o2=f2.getBean("t");
        Test t= (Test) o;
        t.hello();
        System.out.println(o1==o2);
    }
}
