package test;

import java.util.ArrayList;

public class Store {
	int storeID;
	String storeName;
	ArrayList<String> productList;
	int revenue;
	public Store(int storeID, String storeName) {
		super();
		this.storeID = storeID;
		this.storeName = storeName;
		this.productList = new ArrayList<>();
		this.revenue = 0;
	}
	void addProduct(String productName,int price) {
		productList.add(productName);
		revenue+=price;
	}
	int getTotalRevenue() {
		return revenue;
	}
	void display() {
		System.out.println("store id"+storeID);
		System.out.println("store Name"+storeName);
		System.out.println("productlist"+productList);
		System.out.println("Total Renevue"+revenue);
	}
	public static void main(String[] args){
		Store s =new Store(101,"Smart");
		s.addProduct("apple", 20);
		s.addProduct("banana", 20);
		s.display();
		s.getTotalRevenue();
	}
}
