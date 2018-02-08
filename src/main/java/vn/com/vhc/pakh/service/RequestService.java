package vn.com.vhc.pakh.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import vn.com.vhc.pakn.model.Request;
import vn.com.vhc.pakn.model.RequestType;

@Service
public class RequestService extends MasterService{

	public List<RequestType> getRequestTypes(String departmentCode,
						String systemCode, String isHas) throws SQLException{
		ResultSet data = null;
		String sql = "select * from request_type where NVL(DEP_CODE,'null') = ? and SYSTEM_CODE = ? and NVL(IS_HAS,0) = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		// Set parameter

		ps.setString(1, departmentCode);
		ps.setString(2, systemCode);
		if (isHas.equals("null"))
			ps.setInt(3, 0);
		else 
			ps.setString(3, isHas);

		
		data = ps.executeQuery();
		List<RequestType> list = new ArrayList<RequestType>();
		while(data.next()){
			RequestType req = new RequestType();
			req.setId(data.getString("ID"));
			req.setRequestCode(data.getString("REQ_CODE"));
			req.setRequestName(data.getString("REQ_NAME"));
			req.setIsEnable(data.getString("IS_ENABLE"));
			req.setIsStatus(data.getString("IS_STATUS"));
			req.setDepartmentCode(data.getString("DEP_CODE"));
			list.add(req);
		}
		return list;
		
	}
	
	public String postRequest(String req_title, String pro_dep_code, 
				String req_content, String receiving_sms, 
				String receiving_email, String fileDir, String req_status)
			throws SQLException {
		// Get ticketID
		String ticketID="";
		String sql = "select REQUEST_SEQ.nextval from dual";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			ticketID = rs.getString("NEXTVAL");
		}
		int data;
		sql = "insert into request(ticketid, req_title, pro_dep_code,"
				+ " req_content, receiving_sms, receiving_email, "
				+ "file_Dir, req_status) values (?,?,?,?,?,?,?,?)";
		ps = connection.prepareStatement(sql);
		
		// Set parameter
		ps.setString(1, ticketID);
		ps.setString(2, req_title);
		ps.setString(3, pro_dep_code);
		ps.setString(4, req_content);
		ps.setString(5, receiving_sms);
		ps.setString(6, receiving_email);
		if (fileDir.equals("null")) {
			String temp = null;
			ps.setString(7, temp);
		}else {
			ps.setString(7, fileDir);
		}
		ps.setString(8, req_status);
		
		data = ps.executeUpdate();
		if (data >= 1)
			return "true";
		else 
			return "false";
		
	}
	
	public List<Request> getRequest(String req_title, String req_system_code,
			String req_dep_code, String req_user, String pro_dep_code,
			String pro_user, String ticketid, String req_status)
						throws SQLException {
		ResultSet data = null;
		String sql = "select * from request where req_title like ? and req_system_code like ? and "
				+ "req_dep_code like ? and req_user like ? and pro_dep_code like ? and "
				+ "pro_user like ? and ticketid like ? and req_status like ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		// Set parameter

		ps.setString(1, req_title);
		ps.setString(2, req_system_code);
		ps.setString(3, req_dep_code);
		ps.setString(4, req_user);
		ps.setString(5, pro_dep_code);
		ps.setString(6, pro_user);
		ps.setString(7, ticketid);
		ps.setString(8, req_status);
		
		data = ps.executeQuery();
		List<Request> list = new ArrayList<Request>();
		while (data.next()) {
			Request req = new Request();
			req.setId(data.getString("ID"));
			req.setTicket_id(data.getString("ticketID"));
			req.setReq_dep_code(data.getString("req_dep_code"));
			req.setReq_user(data.getString("req_user"));
			req.setReq_system_code(data.getString("req_system_code"));
			req.setReq_type_id(data.getString("req_type_id"));
			req.setReq_level(data.getString("req_level"));
			req.setReq_status(data.getString("req_status"));
			
//			req.setReq_date(data.getString("req_date"));
			
			req.setPro_dep_code(data.getString("pro_dep_code"));
			req.setPro_user(data.getString("pro_user"));
			req.setPro_content(data.getString("pro_content"));
			
//			req.setPro_assginment_date(data.getString("pro_assginment_date"));
//			req.setPro_plan(data.getString("pro_plan"));
//			req.setPro_actua(data.getString("pro_actua"));
			
			req.setReceiving_email(data.getString("receiving_email"));
			req.setReceiving_sms(data.getString("receiving_sms"));
			req.setReq_content(data.getString("req_content"));
			req.setPro_assignment_user(data.getString("pro_assignment_user"));
			req.setDic_cause_id(data.getString("pro_assignment_user"));
			req.setFile_dir(data.getString("file_dir"));
			
			list.add(req);
		}
		return list;

	}
	
}
