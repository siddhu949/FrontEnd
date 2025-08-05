package collection;
import java.util.*;
public class Arraylist {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 // Non-generic ArrayList: holds any type (raw type)
		ArrayList a1=new ArrayList();//creation of array list
		//add method
	    a1.add("balu");
	    a1.add("is");
	    a1.add("my");
	    a1.add("friend");
	    a1.add(2);
	    a1.add(1,"friends");//particular position
	    // size method
	    
	    for(int i=0;i<a1.size();i++) {
	    	System.out.println(a1.get(i));//get method 
	    }
	    
	    ArrayList a2=new ArrayList();
	    a2.add("gopi");
	    a2.add("is my school friend");
	    a2.addAll(a1);
	    for(int i=0;i<a2.size();i++) {
	    	System.out.println(a2.get(i));//get method 
	    }
	    //remove or deletion
	    a1.remove("balu");//remove based on index,object,array,clear()
	    for(int i=0;i<a1.size();i++) {
	    	System.out.println(a1.get(i));//get method 
	    }
	    a2.clear();
        //verification - contains contains all-array
	    System.out.println( a1.contains(2));
	    //updation-set method index,value
	    a1.set(0, a2);
	    for(int i=0;i<a1.size();i++) {
	    	System.out.println(a1.get(i));//get method 
	    }
	    //generic type which is strict follows of data type holds
        //generic type is at java 1.5 which is non primitive data types
	    //here we used the wrapper class which Interger for int 
	    ArrayList<Integer>v1=new ArrayList<>();//() in this we can add another array list also	    
	    v1.add(1);
        v1.add(2);
        for(int i=0;i<v1.size();i++) {
	    	System.out.println(v1.get(i));//get method 
	    }
	}

	

}
