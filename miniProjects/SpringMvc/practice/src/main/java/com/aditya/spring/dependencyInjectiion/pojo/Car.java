package com.aditya.spring.dependencyInjectiion.pojo;
//secondary data type - class injection (refrence)
public class Car {
	private String company;
	private String carName;
	
	private Engine e;

	public void setCompany(String company) {
		this.company = company;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public void setEngine(Engine e) {
		this.e = e;
	}
	
	public void displayDetails() {
		System.out.println("company :"+company+"\n"+"Car Name :"+carName+"\n"+"engine name :"+e.getEngineName()+"\n"+e.getModelYear());
		
	}

	
	
}
