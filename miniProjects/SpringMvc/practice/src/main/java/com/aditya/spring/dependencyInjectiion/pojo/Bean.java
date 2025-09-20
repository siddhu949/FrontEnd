package com.aditya.spring.dependencyInjectiion.pojo;

public class Bean {
	private String gender;
	private int age;
	public void setGender(String gender) {
		this.gender=gender;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public void hello(String name) {
		System.out.println("hello "+gender+" "+name+" "+age);
	}
	


}
