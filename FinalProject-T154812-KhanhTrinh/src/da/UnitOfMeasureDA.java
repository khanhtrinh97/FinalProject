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
import dataobject.UnitOfMeasure;

public class UnitOfMeasureDA extends WHConnection {
	
	public Vector<UnitOfMeasure> getUnitOfMeasure() {
		String sql = "SELECT * FROM unitofmeasure";
		Vector<UnitOfMeasure> unitList = new Vector<>();
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				UnitOfMeasure unit = new UnitOfMeasure(rs.getInt("id"),
						rs.getString("unitname"));
				unitList.add(unit);
			}
			return unitList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return unitList;
	}
	
	public UnitOfMeasure getUnit(int id ){
		String sql = "SELECT u.unitname FROM unitofmeasure u WHERE u.id = " + id;
		try(Connection conn = connect(); 
				Statement stmt = conn.createStatement(); 
				ResultSet rs = stmt.executeQuery(sql)){
			if(rs.next()){
				UnitOfMeasure unit = new UnitOfMeasure();
				
				//cat.setCategoryId(rs.getInt("categoriesid"));
				unit.setName(rs.getString("unitname"));
				//bra.setDescription(rs.getString("description"));
				return unit;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public DefaultTableModel getUnits1() {
		String sql = "SELECT * FROM unitofmeasure";

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			return buildTableModel(rs);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	
	public void insert(String name){
		String spl ="INSERT INTO unitofmeasure(unitname)"
				+ "VALUES(?)";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(spl)){
			pstmt.setString(1, name);
			//pstmt.setString(2, description);
			
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public void update(String name, int unitid){
		String sql = "UPDATE unitofmeasure SET unitname = ? "
				+ "WHERE(id = ?)";
		try(Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, name);
			
			pstmt.setInt(2, unitid);
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
