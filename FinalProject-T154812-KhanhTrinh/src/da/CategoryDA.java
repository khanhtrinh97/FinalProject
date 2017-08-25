package da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import dataobject.Category;

public class CategoryDA extends WHConnection{
	
	public Vector<Category> getAllCategories() {
		String sql = "SELECT * FROM categories";
		Vector<Category> categoryList = new Vector<>();
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				Category cat = new Category(rs.getInt("id"),
						rs.getString("categoryname"),
						rs.getString("description"));
				categoryList.add(cat);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return categoryList;
	}
	
	public Category getCategory(int id ){
		String sql = "SELECT c.categoryname, c.description  FROM categories c WHERE c.id = " + id;
		try(Connection conn = connect(); 
				Statement stmt = conn.createStatement(); 
				ResultSet rs = stmt.executeQuery(sql)){
			if(rs.next()){
				Category cat = new Category();
				
				//cat.setCategoryId(rs.getInt("categoriesid"));
				cat.setCategoryName(rs.getString("categoryname"));
				cat.setCategoryDescription(rs.getString("description"));
				return cat;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public DefaultTableModel getCategories1() {
		String sql = "SELECT * FROM categories";

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
		String spl ="INSERT INTO categories(categoryname, description)"
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
	
	public void update(String name, String description, int categoryid){
		String sql = "UPDATE categories SET categoryname = ?, description = ? "
				+ "WHERE(id = ?)";
		try(Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, name);
			pstmt.setString(2, description);
			pstmt.setInt(3, categoryid);
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
