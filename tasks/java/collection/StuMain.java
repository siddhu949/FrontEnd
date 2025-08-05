package collection;

import java.util.ArrayList;

public class StuMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      ArrayList <ArrayObj> ao=new  ArrayList<>();
      ao.add(new ArrayObj(503,"adi"));
      ao.add(new ArrayObj(580,"madhu"));
      ao.add(new ArrayObj(584,"latha"));
      ao.add(new ArrayObj(504,"pavi"));
      ao.add(new ArrayObj(565,"sumi"));
    //flag which we used to stop when we found the id
      boolean found=false;
      for(ArrayObj s1:ao) {
    	  System.out.println("student name"+" "+s1.getName()+" "+"student ID"+" "+s1.getId());
      
    	  
      //condition
    	  if(s1.getId()==1) {
    		  System.out.println("found");
    		  found=true;
    		  break;
    	  }
    	  
      
      }
      if(!found) {
    	  System.out.println("not found");
      }
      
	}

}
