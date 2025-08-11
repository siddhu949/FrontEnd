package test;

public class Student1 {
	String name;
	int rollNo;
	public Student1(String name, int rollNo) {
		
		this.name = name;
		this.rollNo = rollNo;
	}
	
	
	
	
public static void main(String[] args) {
	// TODO Auto-generated method stub
	Student1 [] st= {
			new Student1("aditya",202),
			new Student1("satya",203),
			new Student1("prakash",203)
			};
			
			
			System.out.println("the object count is "+st.length);
}
}

