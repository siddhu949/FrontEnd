package test;

import java.util.ArrayList;
import java.util.Scanner;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Employee>al =new ArrayList<>();
		Scanner sc =new Scanner (System.in);
		for(int i=0;i<5;i+=4) {
			System.out.println("enter the empno");
			int empNo=sc.nextInt();
			System.out.println("enter the name");
			String name=sc.next();
			System.out.println("enter the dept");
			String dept=sc.next();
			System.out.println("enter the salary");
			int salary=sc.nextInt();
			al.add(new Employee(empNo,name,dept,salary));
		}
		System.out.println("showing details");
		for(Employee e:al) {
			System.out.println(e.empNo);
		}
		
		Employee Highest =al.get(0);
		for(Employee e:al) {
		if(e.salary>Highest.salary) {
			Highest=e;
		}

	}
		System.out.println("highest salary "+Highest.name);
	}

}
