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
import dataobject.Department;
import dataobject.User;
import dataobject.Warehouse;

public class DepartmentDA extends WHConnection{
	
	public Vector<Department> getAllDepartments() {
		String sql = "SELECT * FROM Department";
		Vector<Department> departmentList = new Vector<>();
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				Department department = new Department(rs.getInt("id"),
						rs.getString("departmentname"),
						rs.getString("description"));
						//rs.getString("description"));
				departmentList.add(department);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return departmentList;
	}
	
	public Department getDepartment(int id ){
		String sql = "SELECT de.departmentname, de.description FROM Department de WHERE de.id = " + id;
		try(Connection conn = connect(); 
				Statement stmt = conn.createStatement(); 
				ResultSet rs = stmt.executeQuery(sql)){
			if(rs.next()){
				Department department = new Department();
				
				//cat.setCategoryId(rs.getInt("categoriesid"));
				department.setDepartmentname(rs.getString("departmentname"));
				department.setDescription(rs.getString("description"));
				
				return department;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public DefaultTableModel getDepartment1() {
		String sql = "SELECT * FROM Department";

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			return buildTableModel(rs);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	
	public void insert(String departmentname, String description){
		String spl ="INSERT INTO Department(departmentname, description)"
				+ "VALUES(?, ?)";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(spl)){
			pstmt.setString(1, departmentname);
			pstmt.setString(2, description);
			
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public void update(String departmentname, String description, int departmentid){
		String sql = "UPDATE Department SET departmentname = ?, description = ? "
				+ "WHERE(id = ?)";
		try(Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, departmentname);
			pstmt.setString(2, description);
			pstmt.setInt(3, departmentid);
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
