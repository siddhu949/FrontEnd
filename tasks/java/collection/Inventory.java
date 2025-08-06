package collection;

import java.util.ArrayList;

public class Inventory {
	ArrayList<Product>products;
	Inventory(){
		products=new ArrayList<Product>();
	}
	void addProduct(Product p) {
		products.add(p);
	}
//	ArrayList<Product> showAll(){
//		return products;
//	}
	void showAll() {
		for(Product p:products) {
			System.out.println("the inventory product\n"+p.id+"\n"+p.pname+"\n"+p.price+"\n"+p.quantity);
		}
	}
	void lowInventory() {
		for(Product p:products) {
			if(p.quantity<100) {
				System.out.println("the low inventory product"+"\n"+p.pname+"\n"+p.price);
			}
		}
	}
	void noProduct() {
		System.out.println(products.size());
	}
	void sumQuantity() {
		int sum=0;
		for(Product p:products) {
			sum=sum+p.quantity;
		}
	}
	void removeProduct(int id) {
		for(int i=0;i<products.size();i++) {
			Product p=products.get(i);
			if(p.id==id) {
				products.remove(i);
				System.out.println("Product with ID " + id + " removed.");
	            return;
			}
					
		}
		
		 System.out.println("Product with ID " + id + " not found.");	
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Inventory i = new Inventory();
       //adding some products
       Product p1=new Product(1,"keyBoard",599.99,50);
       Product p2=new Product(2,"mouse",599.99,150);
       Product p3=new Product(3,"moniter",599.99,60);
       Product p4=new Product(4,"cpu",599.99,250);
       i.addProduct(p1);
       i.addProduct(p2);
       i.addProduct(p3);
       i.addProduct(p4);
       System.out.println("showing all products");
       i.showAll();
       i.lowInventory();
       i.removeProduct(2);
       
       
       
	}

}
