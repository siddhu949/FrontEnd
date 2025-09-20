package com.aditya.spring.dependencyInjectiion;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aditya.spring.dependencyInjectiion.pojo.CBean;

public class Constructor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ap= new ClassPathXmlApplicationContext("com/aditya/spring/dependencyInjection/xml/constructor.xml");
		CBean cb=(CBean)ap.getBean("bean");
		cb.display();

	}

}
