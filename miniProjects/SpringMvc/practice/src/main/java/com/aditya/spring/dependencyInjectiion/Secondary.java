package com.aditya.spring.dependencyInjectiion;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aditya.spring.dependencyInjectiion.pojo.Car;

public class Secondary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ApplicationContext ap= new ClassPathXmlApplicationContext("com/aditya/spring/dependencyInjection/xml/refrence.xml");
//		Car ca =(Car)ap.getBean("c");
//		ca.displayDetails();
//		String files[]=new String[] {"com/aditya/spring/dependencyInjection/xml/r1.xml","com/aditya/spring/dependencyInjection/xml/r2.xml"};
//		ApplicationContext ap= new ClassPathXmlApplicationContext(files);
//		Car cb =(Car)ap.getBean("c");
//		cb.displayDetails();
		ApplicationContext ap= new ClassPathXmlApplicationContext("com/aditya/spring/dependencyInjection/xml/inner.xml");
		Car cb = (Car)ap.getBean("c");
		cb.displayDetails();
		
		
	}

}
