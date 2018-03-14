package vn.com.vhc.pakh.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.Date;
import vn.com.vhc.pakn.model.Request;
import vn.com.vhc.pakn.model.RequestDetail;
import vn.com.vhc.pakn.model.RequestType;

@Service
public class RequestService extends MasterService {

	public List<RequestType> getRequestTypes(String departmentCode, 
			String systemCode, String isHas, String username)
			throws SQLException {
		ResultSet data = null;
		String sql = "select * from request_type join users_req on request_type.id = users_req.req_id where "
				+ " request_type.DEP_CODE = ? and request_type.SYSTEM_CODE = ? and users_req.username = ? ";
		if (isHas.equals("null"))
			sql += " and request_type.IS_HAS IS NULL";
		else
			sql += " and request_type.IS_HAS = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		// Set parameter
		String temp = null;
		if (departmentCode.equals("null"))
			ps.setString(1, temp);
		else
			ps.setString(1, departmentCode);
		//---------------------------------------
		if (systemCode.equals("null"))
			ps.setString(2, temp);
		else
			ps.setString(2, systemCode);
		//---------------------------------------
		if (username.equals("null"))
			ps.setString(3, temp);
		else
			ps.setString(3, username);
		// ---------------------------------------
		if (!isHas.equals("null"))
			ps.setInt(4, Integer.parseInt(isHas));
		// ---------------------------------------
		data = ps.executeQuery();
		List<RequestType> list = new ArrayList<RequestType>();
		while (data.next()) {
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

	public String postRequest(String req_dep_code, String req_user, String req_system_code, String req_title,
			String pro_dep_code, String req_content, String receiving_sms, String receiving_email, String fileDir,
			String req_status) throws SQLException {
		// Get ticketID
		String ticketID = "";
		String sql = "select REQUEST_SEQ.nextval from dual";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			ticketID = rs.getString("NEXTVAL");
		}
		int data;
		sql = "insert into request(ticketid,req_date,req_dep_code, req_user, req_system_code, "
				+ "req_title, pro_dep_code," + " req_content, receiving_sms, receiving_email, "
				+ "file_Dir, req_status) values (?,sysdate,?,?,?,?,?,?,?,?,?,?)";
		ps = connection.prepareStatement(sql);

		// Set parameter
		ps.setString(1, ticketID);
		// set value nullable
		String temp = null;
		if (req_dep_code.equals("null"))
			ps.setString(2, temp);
		else
			ps.setString(2, req_dep_code);

		if (req_user.equals("null"))
			ps.setString(3, temp);
		else
			ps.setString(3, req_user);

		if (req_system_code.equals("null"))
			ps.setString(4, temp);
		else
			ps.setString(4, req_system_code);
		// 11
		ps.setString(5, req_title);
		ps.setString(6, pro_dep_code);
		ps.setString(7, req_content);
		ps.setString(8, receiving_sms);
		ps.setString(9, receiving_email);
		if (fileDir.equals("null")) {
			ps.setString(10, temp);
		} else {
			ps.setString(10, fileDir);
		}
		ps.setString(11, req_status);

		data = ps.executeUpdate();
		if (data >= 1)
			return "true";
		else
			return "false";

	}

	public String responseRequest(String ticketid, String fw_dep_code, String fw_user, String fw_date,
			String fw_content, String receiving_date, String receiving_dep_code, String receiving_user, String dateline,
			String actualy_finish, String return_content, String return_content_private, String dic_cause_id,
			String dic_cause_id_private, String file_id) throws SQLException {
		// Get ticketID
		String ticketID = "";
		String sql = "select REQUEST_SEQ.nextval from dual";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			ticketID = rs.getString("NEXTVAL");
		}
		int data;
		sql = "insert into request_detail(ticketid, fw_dep_code, fw_user, fw_date, "
				+ "fw_content, receiving_date, receiving_dep_code, "
				+ "receiving_user, dateline, actualy_finish,return_content,"
				+ "return_content_private,dic_cause_id,dic_cause_id_private,"
				+ "file_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		ps = connection.prepareStatement(sql);
		// Set parameter
		ps.setString(1, ticketID);
		// set value nullable
		String temp = null;
		if (fw_dep_code.equals("null"))
			ps.setString(2, temp);
		else
			ps.setString(2, fw_dep_code);
		// --------------------------------
		if (fw_user.equals("null"))
			ps.setString(3, temp);
		else
			ps.setString(3, fw_user);
		// --------------------------------DATE
		if (fw_date.equals("null"))
			ps.setString(4, temp);
		else
			ps.setDate(4, Date.valueOf(fw_date));
		// --------------------------------
		if (fw_content.equals("null"))
			ps.setString(5, temp);
		else
			ps.setString(5, fw_content);
		// --------------------------------DATE
		if (receiving_date.equals("null"))
			ps.setString(6, temp);
		else
			ps.setDate(6, Date.valueOf(receiving_date));
		// --------------------------------
		if (receiving_dep_code.equals("null"))
			ps.setString(7, temp);
		else
			ps.setString(7, receiving_dep_code);
		// --------------------------------
		if (receiving_user.equals("null"))
			ps.setString(8, temp);
		else
			ps.setString(8, receiving_user);
		// --------------------------------DATE
		if (dateline.equals("null"))
			ps.setString(9, temp);
		else
			ps.setDate(9, Date.valueOf(dateline));
		// --------------------------------DATE
		if (actualy_finish.equals("null"))
			ps.setString(10, temp);
		else
			ps.setDate(10, Date.valueOf(actualy_finish));
		// --------------------------------
		if (return_content.equals("null"))
			ps.setString(11, temp);
		else
			ps.setString(11, return_content);
		// --------------------------------
		if (return_content_private.equals("null"))
			ps.setString(12, temp);
		else
			ps.setString(12, return_content_private);
		// --------------------------------
		if (dic_cause_id.equals("null"))
			ps.setString(13, temp);
		else
			ps.setString(13, dic_cause_id);
		// --------------------------------
		if (dic_cause_id_private.equals("null"))
			ps.setString(14, temp);
		else
			ps.setString(14, dic_cause_id_private);
		// --------------------------------
		if (file_id.equals("null"))
			ps.setString(15, temp);
		else
			ps.setString(15, file_id);

		// Execute Update
		data = ps.executeUpdate();
		if (data >= 1)
			return "true";
		else
			return "false";
	}

	public List<Request> getRequest(String req_title, String req_system_code, String req_dep_code, String req_user,
			String pro_dep_code, String pro_user, String ticketid, String req_status, String start_req_date,
			String end_req_date) throws SQLException {
		ResultSet data = null;
		String sql = "select * from request where nvl(req_title,'x') like ? and nvl(req_system_code,'x') like ? and "
				+ "nvl(req_dep_code,'x') like ? and nvl(req_user,'x') like ? and nvl(pro_dep_code,'x') like ? and "
				+ "nvl(pro_user,'x') like ? and nvl(ticketid,'x') like ? and nvl(req_status,'x') like ? "
				+ "and REQ_DATE >= to_date(?,'dd-MM-yyyy')" + "and REQ_DATE <= to_date(?,'dd-MM-yyyy')";
		PreparedStatement ps = connection.prepareStatement(sql);
		// Set parameter
		ps.setString(1, "%" + req_title + "%");
		ps.setString(2, "%" + req_system_code + "%");
		ps.setString(3, "%" + req_dep_code + "%");
		ps.setString(4, "%" + req_user + "%");
		ps.setString(5, "%" + pro_dep_code + "%");
		ps.setString(6, "%" + pro_user + "%");
		ps.setString(7, "%" + ticketid + "%");
		ps.setString(8, "%" + req_status + "%");
		ps.setString(9, start_req_date);
		ps.setString(10, end_req_date);
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
			req.setReq_title(data.getString("req_title"));
			req.setReq_status(data.getString("req_status"));

			req.setReq_date(data.getDate("req_date"));

			req.setPro_dep_code(data.getString("pro_dep_code"));
			req.setPro_user(data.getString("pro_user"));
			req.setPro_content(data.getString("pro_content"));

			req.setPro_plan(data.getDate("pro_plan"));
			req.setPro_actua(data.getDate("pro_actua"));
			//
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

	public int getNum(String status) throws SQLException {
		ResultSet data;
		String sql = "select * from request where req_status = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		// Set parameter
		ps.setString(1, status);

		data = ps.executeQuery();
		int count = 0;
		while (data.next()) {
			count++;
		}
		return count;

	}

	public RequestDetail getRecentRequestDetail(String ticketid) throws SQLException {
		ResultSet data = null;
		String sql = "select * from (select * from request_detail where ticketid = ? order by id desc) where rownum = 1";
		PreparedStatement ps = connection.prepareStatement(sql);
		// Set parameter
		ps.setString(1, ticketid);
		data = ps.executeQuery();
		RequestDetail req = new RequestDetail();

		if (data.next()) {
			req.setId(data.getString("ID"));
			req.setTicketid(data.getString("ticketid"));
			req.setFw_dep_code(data.getString("fw_dep_code"));
			req.setFw_user(data.getString("fw_user"));
			req.setFw_date(data.getString("fw_date"));
			req.setFw_content(data.getString("fw_content"));
			req.setReceiving_date(data.getString("receiving_date"));
			req.setReceiving_dep_code(data.getString("receiving_dep_code"));
			req.setReceiving_user(data.getString("receiving_user"));
			req.setDateline(data.getString("dateline"));
			req.setActualy_finish(data.getString("actualy_finish"));
			req.setReturn_content(data.getString("return_content"));
			req.setReturn_content_private(data.getString("return_content_private"));
			req.setCreated_date(data.getString("created_date"));
			req.setDic_cause_id(data.getString("dic_cause_id"));
			req.setDic_cause_id_private(data.getString("dic_cause_id_private"));
			req.setFile_id(data.getString("file_id"));
			return req;
		} else {
			return null;
		}
	}

	// API 14
	public String updateRequest(String ticketid, String pro_actua, String pro_content, String pro_user,
			String pro_dep_code) throws SQLException {
		// Get ticketID
		int data;
		String sql = "update request set " + "pro_actua = to_date(?, 'dd-MM-yyyy'), " + "pro_content = ?,"
				+ "pro_user = ?," + "pro_dep_code = ?" + " where ticketid = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		// Set parameter
		ps.setString(1, pro_actua);
		ps.setString(2, pro_content);
		ps.setString(3, pro_user);
		ps.setString(4, pro_dep_code);
		ps.setString(5, ticketid);

		// Execute Update
		data = ps.executeUpdate();

		if (data >= 1)
			return "true";
		else
			return "false";
	}

	// API 15
	public String updateRequestDetail(String id, String receiving_date, String receiving_dep_code, String receiving_user,
			String actualy_finish,String return_content,String return_content_private,String dic_cause_id,
			String dic_cause_id_private,String file_id) throws SQLException {
		
		int data;
		String sql = "update request_detail set " 
				+ "receiving_date = to_date(?, 'dd-MM-yyyy'), " 
				+ "receiving_dep_code = ?,"
				+ "receiving_user = ?," 
				+ "actualy_finish = to_date(?, 'dd-MM-yyyy'),"
				+ "return_content = ?,"
				+ "return_content_private = ?,"
				+ "dic_cause_id = ?,"
				+ "dic_cause_id_private = ?,"
				+ "file_id = ?" 
				+ " where id = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		// Set parameter
		String temp=null;
		ps.setString(1, receiving_date);
		ps.setString(2, receiving_dep_code);
		ps.setString(3, receiving_user);
		ps.setString(4, actualy_finish);
		if (return_content.equals("null"))
			ps.setString(5, temp);
		else
			ps.setString(5, return_content);
		//--------------------------------
		if (return_content_private.equals("null"))
			ps.setString(6, temp);
		else
			ps.setString(6, return_content_private);
		//--------------------------------
		if (dic_cause_id.equals("null"))
			ps.setString(7, temp);
		else
			ps.setString(7, dic_cause_id);
		//--------------------------------
		if (dic_cause_id_private.equals("null"))
			ps.setString(8, temp);
		else
			ps.setString(8, dic_cause_id_private);
		//--------------------------------
		if (file_id.equals("null"))
			ps.setString(9, temp);
		else
			ps.setString(9, file_id);

		ps.setString(10, id);
		// Execute Update
		data = ps.executeUpdate();
		if (data >= 1)
			return "true";
		else
			return "false";
	}
}
