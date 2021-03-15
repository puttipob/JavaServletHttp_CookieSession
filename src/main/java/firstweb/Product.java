package firstweb;

public class Product {
	private int pid;
	private String productName;
	private String productDetail;
	private int price;

	public Product() {
	}

	public Product(int pid, String productName, String productDetail, int price) {
		this.pid = pid;
		this.productName = productName;
		this.productDetail = productDetail;
		this.price = price;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}