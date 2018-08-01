package vn.com.vhc.pakn.model;

public class RequestType {
	private String id;
	private String request_code;
	private String request_name;
	private String department_code;
	private String is_enable;
	private String is_status;
	public RequestType() {
		super();
	}
	
	public RequestType(String id, String requestCode, String requestName, String departmentCode, String isEnable,
			String isStatus) {
		super();
		this.id = id;
		this.request_code = requestCode;
		this.request_name = requestName;
		this.department_code = departmentCode;
		this.is_enable = isEnable;
		this.is_status = isStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRequest_code() {
		return request_code;
	}

	public void setRequest_code(String request_code) {
		this.request_code = request_code;
	}

	public String getRequest_name() {
		return request_name;
	}

	public void setRequest_name(String request_name) {
		this.request_name = request_name;
	}

	public String getDepartment_code() {
		return department_code;
	}

	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}

	public String getIs_enable() {
		return is_enable;
	}

	public void setIs_enable(String is_enable) {
		this.is_enable = is_enable;
	}

	public String getIs_status() {
		return is_status;
	}

	public void setIs_status(String is_status) {
		this.is_status = is_status;
	}

	
	
}
