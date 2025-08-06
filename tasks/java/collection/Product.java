package collection;

public class Product {

	int id;
	String pname;
	double price;
	int quantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuanity() {
		return quantity;
	}
	public void setQuanity(int quantity) {
		this.quantity = quantity;
	}
	public Product(int id, String pname, double price, int quanity) {
		super();
		this.id = id;
		this.pname = pname;
		this.price = price;
		this.quantity = quanity;
	}

}
