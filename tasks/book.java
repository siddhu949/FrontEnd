package test;

import java.util.ArrayList;
import java.util.Scanner;

package test;

public class Book {
	String title;
	String author;
	int cost;
	int noOfBooks;
	void display() {
		System.out.println("---the books member data"
	                         +title+ "\n"+author +"/n"+cost+"\n"+noOfBooks);
	
	}
	public Book(String title, String author, int cost, int noOfBooks) {
		this.title = title;
		this.author = author;
		this.cost = cost;
		this.noOfBooks = noOfBooks;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getNoOfBooks() {
		return noOfBooks;
	}
	public void setNoOfBooks(int noOfBooks) {
		this.noOfBooks = noOfBooks;
	}	
	

}

public class Bclass {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<Book>();

        // Sample books
        books.add(new Book("balaji", "siva", 1000, 3));
        books.add(new Book("java", "kumar", 800, 2));
        books.add(new Book("python", "ram", 900, 5));

        System.out.println("Enter the title:");
        String t = sc.next();

        System.out.println("Enter the number of books you want:");
        int n = sc.nextInt();

        boolean found = false;

        for (Book s : books) {
            if (t.equalsIgnoreCase(s.getTitle())) {
                found = true;
                System.out.println("Author: " + s.getAuthor());
                System.out.println("Available books: " + s.getNoOfBooks());
                System.out.println("Cost per book: " + s.getCost());

                if (n <= s.getNoOfBooks()) {
                    System.out.println("Sufficient books available");
                    System.out.println("Total cost is: " + (n * s.getCost()));
                } else {
                    System.out.println("Not sufficient books available");
                }

                break; // Book found, exit loop
            }
        }

        if (!found) {
            System.out.println("Book not found");
        }
    }
}
