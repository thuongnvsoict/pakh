package vn.com.vhc.pakn.model;

public class Department {
	private String id;
	private String departmentCode;
	private String departmentName;
	
	public Department() {
		super();
	}
	public Department(String id, String departmentCode, String departmentName) {
		super();
		this.id = id;
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
}
