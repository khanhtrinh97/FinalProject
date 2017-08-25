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
import dataobject.Warehouse;

public class WarehouseDA extends WHConnection{
	
	public Vector<Warehouse> getAllWarehouses() {
		String sql = "SELECT * FROM WareHouse";
		Vector<Warehouse> warehouseList = new Vector<>();
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				Warehouse ware = new Warehouse(rs.getInt("id"),
						rs.getString("warehousename"),
						rs.getString("address"),
						rs.getString("description"));
				warehouseList.add(ware);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return warehouseList;
	}
	
	public Warehouse getWarehouse(int id ){
		String sql = "SELECT w.warehousename, w.address, w.description  FROM WareHouse w WHERE w.id = " + id;
		try(Connection conn = connect(); 
				Statement stmt = conn.createStatement(); 
				ResultSet rs = stmt.executeQuery(sql)){
			if(rs.next()){
				Warehouse ware = new Warehouse();
				
				//cat.setCategoryId(rs.getInt("categoriesid"));
				ware.setName(rs.getString("warehousename"));
				ware.setAddress(rs.getString("address"));
				ware.setDescription(rs.getString("description"));
				return ware;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public DefaultTableModel getWarehouse1() {
		String sql = "SELECT * FROM WareHouse";

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			return buildTableModel(rs);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	
	public void insert(String name, String address, String description){
		String spl ="INSERT INTO WareHouse(warehousename, address, description)"
				+ "VALUES(?, ?, ?)";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(spl)){
			pstmt.setString(1, name);
			pstmt.setString(2, address);
			pstmt.setString(3, description);
			
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public void update(String name, String address, String description, int warehouseid){
		String sql = "UPDATE WareHouse SET warehousename = ?, address = ?, description = ? "
				+ "WHERE(id = ?)";
		try(Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, name);
			pstmt.setString(2, address);
			pstmt.setString(3, description);
			pstmt.setInt(4, warehouseid);
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
