package dataobject;

public class Department {
	private int id;
	private String departmentname;
	private String description;
	
	
	public Department(){}
	public Department(int id, String username, String password) {
		super();
		this.id = id;
		this.departmentname = departmentname;
		this.description = description;
	}
	@Override
	public String toString() {
		return departmentname + description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
