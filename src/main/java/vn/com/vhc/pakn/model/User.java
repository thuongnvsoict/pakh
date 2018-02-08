package vn.com.vhc.pakn.model;

public class User {
	private String id;
	private String username;
	private String password;
	private String fullname;
	private String position;
	private String phone;
	private String gender;
	private String email;
	private String departmentCode;
	private String isEnable;
	
	public User() {
		super();
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String roomId) {
		this.departmentCode = roomId;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public User(String id, String username, String password, String fullname, String position, String phone,
			String gender, String email, String departmentCode, String isEnable) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.position = position;
		this.phone = phone;
		this.gender = gender;
		this.email = email;
		this.departmentCode = departmentCode;
		this.isEnable = isEnable;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
