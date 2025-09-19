package com.aditya.spring.privateConstructor;

import java.lang.reflect.Constructor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try {
        	Class c = 	Class.forName("com.aditya.spring.privateConstructor.pojo.Bean");
        	Constructor con[]=c.getDeclaredConstructors();
        	con[0].setAccessible(true);
        	con[0].newInstance();
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
