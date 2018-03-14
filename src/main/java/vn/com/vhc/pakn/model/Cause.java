package vn.com.vhc.pakn.model;

public class Cause {
	private String ID;
	private String causeCode;
	private String causeName;
	private String isEnable;
	private String ordering;
	private String createdBy;
	private String isParent;
	private String idHas;
	private String depCode;
	private String isStatus;
	private String systemCode;
	
	public Cause() {
		super();
	}

	public Cause(String iD, String causeCode, String causeName, String isEnable, String ordering, String createdBy,
			String idHas, String depCode, String isStatus, String systemCode) {
		super();
		ID = iD;
		this.causeCode = causeCode;
		this.causeName = causeName;
		this.isEnable = isEnable;
		this.ordering = ordering;
		this.createdBy = createdBy;
		this.idHas = idHas;
		this.depCode = depCode;
		this.isStatus = isStatus;
		this.systemCode = systemCode;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(String causeCode) {
		this.causeCode = causeCode;
	}

	public String getCauseName() {
		return causeName;
	}

	public void setCauseName(String causeName) {
		this.causeName = causeName;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getOrdering() {
		return ordering;
	}

	public void setOrdering(String ordering) {
		this.ordering = ordering;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getIdHas() {
		return idHas;
	}

	public void setIdHas(String idHas) {
		this.idHas = idHas;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getIsStatus() {
		return isStatus;
	}

	public void setIsStatus(String isStatus) {
		this.isStatus = isStatus;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	
	
}
