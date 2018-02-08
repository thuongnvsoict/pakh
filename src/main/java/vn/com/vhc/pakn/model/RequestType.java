package vn.com.vhc.pakn.model;

public class RequestType {
	private String id;
	private String requestCode;
	private String requestName;
	private String departmentCode;
	private String isEnable;
	private String isStatus;
	public RequestType() {
		super();
	}
	
	public RequestType(String id, String requestCode, String requestName, String departmentCode, String isEnable,
			String isStatus) {
		super();
		this.id = id;
		this.requestCode = requestCode;
		this.requestName = requestName;
		this.departmentCode = departmentCode;
		this.isEnable = isEnable;
		this.isStatus = isStatus;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getIsStatus() {
		return isStatus;
	}

	public void setIsStatus(String isStatus) {
		this.isStatus = isStatus;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRequestCode() {
		return requestCode;
	}
	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}
	public String getRequestName() {
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	
}
