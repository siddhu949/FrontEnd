package com.aditya.spring.dependencyInjectiion.pojo;

public class CBean {
	private String name;
	private int age;
	private String email;
	
	//constructor parameterized 
	CBean(String name, int age,String email){
		this.name=name;
		this.age=age;
		this.email=email;
	}
	//method to display
	public void display() {
		System.out.println(name+"\n"+age+"\n"+email);
	}

}
