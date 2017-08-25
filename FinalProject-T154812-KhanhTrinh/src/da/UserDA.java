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
import dataobject.User;
import dataobject.Warehouse;

public class UserDA extends WHConnection{
	
	public Vector<User> getAllUsers() {
		String sql = "SELECT * FROM User";
		Vector<User> userList = new Vector<>();
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				User user = new User(rs.getInt("id"),
						rs.getString("username"),
						rs.getString("password"));
						//rs.getString("description"));
				userList.add(user);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return userList;
	}
	
	public User getUser(int id ){
		String sql = "SELECT us.username, us.password FROM User us WHERE us.id = " + id;
		try(Connection conn = connect(); 
				Statement stmt = conn.createStatement(); 
				ResultSet rs = stmt.executeQuery(sql)){
			if(rs.next()){
				User user = new User();
				
				//cat.setCategoryId(rs.getInt("categoriesid"));
				user.setName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				
				return user;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public DefaultTableModel getUser1() {
		String sql = "SELECT * FROM User";

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			return buildTableModel(rs);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	
	public void insert(String username, String password){
		String spl ="INSERT INTO User(username, password)"
				+ "VALUES(?, ?)";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(spl)){
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public void update(String username, String password, int userid){
		String sql = "UPDATE User SET username = ?, password = ? "
				+ "WHERE(id = ?)";
		try(Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setInt(3, userid);
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
