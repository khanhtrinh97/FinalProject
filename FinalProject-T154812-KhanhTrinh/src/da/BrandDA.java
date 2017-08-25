package da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dataobject.Brand;
import dataobject.Category;

public class BrandDA extends WHConnection{
	
	public Vector<Brand> getBrands() {
		String sql = "SELECT * FROM brand";
		Vector<Brand> brandList = new Vector<>();
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				Brand brand = new Brand(rs.getInt("id"),
						rs.getString("brandname"),
						rs.getString("description"));
				brandList.add(brand);
			}
			return brandList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return brandList;
	}
	
	public Brand getBrand(int id ){
		String sql = "SELECT b.brandname, b.description  FROM brand b WHERE b.id = " + id;
		try(Connection conn = connect(); 
				Statement stmt = conn.createStatement(); 
				ResultSet rs = stmt.executeQuery(sql)){
			if(rs.next()){
				Brand bra = new Brand();
				
				//cat.setCategoryId(rs.getInt("categoriesid"));
				bra.setName(rs.getString("brandname"));
				bra.setDescription(rs.getString("description"));
				return bra;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public DefaultTableModel getBrands1() {
		String sql = "SELECT * FROM brand";

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			return buildTableModel(rs);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	
	public void insert(String name, String description){
		String spl ="INSERT INTO brand(brandname, description)"
				+ "VALUES(?, ?)";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(spl)){
			pstmt.setString(1, name);
			pstmt.setString(2, description);
			
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public void update(String name, String description, int brandid){
		String sql = "UPDATE brand SET brandname = ?, description = ? "
				+ "WHERE(id = ?)";
		try(Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, name);
			pstmt.setString(2, description);
			pstmt.setInt(3, brandid);
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
