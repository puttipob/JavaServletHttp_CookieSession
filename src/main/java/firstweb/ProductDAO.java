package firstweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
	// ประกาศตัวแปรเพื่อเก็บสถานการณ์เชื่อมต่อสำหรับทุก method
	private Connection con;

	// constructor สำหรับเชื่อมต่อฐานข้อมูล
	public ProductDAO() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/blueshop?characterEncoding=utf-8", "root", "");
	}

	// เมธอดปิดการเชื่อมต่อฐานข้อมูล
	public void closeConnection() throws SQLException {
		con.close();
	}

	// เมธอดดึงข้อมูลจาก ResultSet มาเก็บใน JavaBeans
	private Product mappingProduct(ResultSet resultSet) throws SQLException {
		Product product = new Product();
		product.setPid(resultSet.getInt("pid"));
		product.setProductName(resultSet.getString("pname"));
		product.setProductDetail(resultSet.getString("pdetail"));
		product.setPrice(resultSet.getInt("price"));
		return product; // ส่งกลับเป็น javabean
	}

	public Product getProduct(int pid) throws SQLException {

		// เตรียมคำสั่ง SQL
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM product WHERE pid = ?");
		pStatement.setInt(1, pid);

		// ส่งคำสั่ง SQL ไปยังฐานข้อมูล
		ResultSet resultSet = pStatement.executeQuery();

		if (resultSet.next()) { // ถ้าพบข้อมูล
			Product product = mappingProduct(resultSet); // นำผลลัพธ์ที่ฐานข้อมูลส่งกลับแปลงเป็น object
			return product; // ส่งกลับเป็น javabean
		} else { // ถ้าไม่พบข้อมูล
			return null;
		}
	}

	public ArrayList<Product> getAllProduct() throws SQLException {

		// เตรียมคำสั่ง SQL
		PreparedStatement pStatement = con.prepareStatement("SELECT * FROM product");

		// ส่งคำสั่ง SQL ไปยังฐานข้อมูล
		ResultSet resultSet = pStatement.executeQuery();

		// ประกาศอาร์เรย์สำหรับเก็บ javabeans
		ArrayList<Product> productList = new ArrayList<Product>();

		while (resultSet.next()) {
			Product p = mappingProduct(resultSet); // แปลงข้อมูลเป็น javabean
			productList.add(p); // เก็บ javabean ในอาร์เรย์
		}
		return productList; // ส่งอาร์เรย์กลับ
	}

	public void createProduct(Product product) throws SQLException {
		// เตรียมคำสั่ง SQL
		PreparedStatement pStatement = con
				.prepareStatement("INSERT INTO product (pname, pdetail, price) VALUES (?, ?, ?)");
		pStatement.setString(1, product.getProductName());
		pStatement.setString(2, product.getProductDetail());
		pStatement.setInt(3, product.getPrice());

		// ส่งคำสั่ง SQL ไปยังฐานข้อมูล
		pStatement.executeUpdate();
	}

	public void updateProduct(Product product) throws SQLException {
		// เตรียมคำสั่ง SQL
		PreparedStatement pStatement = con
				.prepareStatement("UPDATE product SET pname = ?, pdetail = ?, price = ? WHERE pid = ?");
		pStatement.setString(1, product.getProductName());
		pStatement.setString(2, product.getProductDetail());
		pStatement.setInt(3, product.getPrice());
		pStatement.setInt(4, product.getPid());

		// ส่งคำสั่ง SQL ไปยังฐานข้อมูล
		pStatement.executeUpdate();
	}

	public void deleteProduct(int id) throws SQLException {
		// เตรียมคำสั่ง SQL
		PreparedStatement pStatement = con.prepareStatement("DELETE FROM product WHERE pid = ?");
		pStatement.setInt(1, id);

		// ส่งคำสั่ง SQL ไปยังฐานข้อมูล
		pStatement.executeUpdate();
	}

	public static void main(String[] args) {
		try {
			ProductDAO productDAO = new ProductDAO(); // สร้าง object DAO

			// ดึงข้อมูลตามรหัสสินค้า
			Product p = productDAO.getProduct(1);
			System.out.println(p.getProductName() + " ราคา " + p.getPrice() + " บาท");

			// ดึงข้อมูลสินค้าทั้งหมด
			ArrayList<Product> products = productDAO.getAllProduct();
			for (Product product : products) {
				System.out.println(product.getProductName() + " ราคา " + product.getPrice() + " บาท"); // นำผลลัพธ์มาแสดง
			}

			/**
			 * // เพิ่มข้อมูล (ส่ง pid=0 เมื่อเป็น auto increment) Product product = new
			 * Product(0, "นาฬิกา", "ดูเวลา", 350); productDAO.createProduct(product);
			 * 
			 * // แก้ไขข้อมูล product = new Product(17, "นาฬิกาใหม่", "ดูเวลา +
			 * ดูอัตราเผาผลาญ", 350); productDAO.updateProduct(product);
			 * 
			 * // ลบข้อมูล productDAO.deleteProduct(20);
			 **/

			// ปิดการเชื่อมต่อ
			productDAO.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
