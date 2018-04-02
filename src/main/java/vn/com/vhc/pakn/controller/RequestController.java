package vn.com.vhc.pakn.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.vhc.pakh.service.RequestService;
import vn.com.vhc.pakn.model.Request;
import vn.com.vhc.pakn.model.RequestDetail;
import vn.com.vhc.pakn.model.RequestType;

@RestController
@RequestMapping("/request")
public class RequestController {
	
	RequestService service = new RequestService();
	
	@RequestMapping(value = "/type", method = RequestMethod.GET)
	public List<RequestType> getRequestTypes(
			@RequestParam(value = "departmentCode", required = false, defaultValue="null") String departmentCode,
			@RequestParam(value = "systemCode", required = false, defaultValue="null") String systemCode,
			@RequestParam(value = "isHas", required = false, defaultValue="null") String isHas,
			@RequestParam(value = "username", required = false, defaultValue="null") String username
			) throws Exception {
		return service.getRequestTypes(departmentCode, systemCode, isHas, username);
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String postRequest(
			@RequestParam(value = "req_dep_code", required = false, defaultValue="null") String req_dep_code,
			@RequestParam(value = "req_user", required = false, defaultValue="null") String req_user,
			@RequestParam(value = "req_system_code", required = false, defaultValue="null") String req_system_code,
			@RequestParam("req_title") String req_title,
			@RequestParam("pro_dep_code") String pro_dep_code,
			@RequestParam("pro_user") String pro_user,
			@RequestParam("req_content") String req_content,
			@RequestParam("receiving_sms") String receiving_sms,
			@RequestParam("receiving_email") String receiving_email,
			@RequestParam("file_dir") String fileDir,
			@RequestParam("req_status") String req_status
			) throws Exception {
		return service.postRequest(req_dep_code, req_user, req_system_code, req_title, pro_dep_code, pro_user, req_content, receiving_sms, receiving_email, fileDir, req_status);
	}
	
	@RequestMapping(value = "/response", method = RequestMethod.POST)
	public String responseRequest(
			@RequestParam(value = "ticketid", required = false, defaultValue="null") String ticketid,
			@RequestParam(value = "fw_dep_code", required = false, defaultValue="null") String fw_dep_code,
			@RequestParam(value = "fw_user", required = false, defaultValue="null") String fw_user,
			@RequestParam(value = "fw_content", required = false, defaultValue="null") String fw_content,
			@RequestParam(value = "receiving_date", required = false, defaultValue="null") String receiving_date,
			@RequestParam(value = "receiving_dep_code", required = false, defaultValue="null") String receiving_dep_code,
			@RequestParam(value = "receiving_user", required = false, defaultValue="null") String receiving_user,
			@RequestParam(value = "actualy_finish", required = false, defaultValue="null") String actualy_finish,
			@RequestParam(value = "return_content", required = false, defaultValue="null") String return_content,
			@RequestParam(value = "return_content_private", required = false, defaultValue="null") String return_content_private,
			@RequestParam(value = "dic_cause_id", required = false, defaultValue="null") String dic_cause_id,
			@RequestParam(value = "dic_cause_id_private", required = false, defaultValue="null") String dic_cause_id_private,
			@RequestParam(value = "file_id", required = false, defaultValue="null") String file_id) throws Exception {
		return service.responseRequest(ticketid, fw_dep_code, fw_user, fw_content, receiving_date, receiving_dep_code, receiving_user, actualy_finish,return_content,return_content_private,dic_cause_id,dic_cause_id_private,file_id);
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<Request> getRequest(@RequestParam(value = "req_title", required = false, defaultValue="%%") String req_title,
			@RequestParam(value = "req_system_code", required = false, defaultValue="%%") String req_system_code,
			@RequestParam(value = "req_dep_code", required = false, defaultValue="%%") String req_dep_code,
			@RequestParam(value = "req_user", required = false, defaultValue="%%") String req_user,
			@RequestParam(value = "pro_dep_code", required = false, defaultValue="%%") String pro_dep_code,
			@RequestParam(value = "pro_user", required = false, defaultValue="%%") String pro_user,
			
			@RequestParam(value = "ticketid", required = false, defaultValue="%%") String ticketid,
			@RequestParam(value = "req_status", required = false, defaultValue="%%") String req_status,
			//Ngay gui yeu cau
			@RequestParam(value = "start_req_date", required = false, defaultValue="%%") String start_req_date,
			@RequestParam(value = "end_req_date", required = false, defaultValue="%%") String end_req_date
			) throws Exception {
		return service.getRequest(req_title, req_system_code, req_dep_code, req_user, pro_dep_code, pro_user, ticketid, req_status, start_req_date, end_req_date);
	}
	
	@RequestMapping(value = "/num/{req_status}", method = RequestMethod.GET)
	public int getNum(@PathVariable("req_status") String req_status) throws SQLException {
		return service.getNum(req_status);
	}
	
	@RequestMapping(value = "/recent/{ticketid}", method = RequestMethod.GET)
	public RequestDetail getRecentRequestDetail(@PathVariable("ticketid") String ticketid) throws SQLException {
		return service.getRecentRequestDetail(ticketid);
	}
	
	// API 14
	@RequestMapping(value = "/updateRequest/{ticketid}", method = RequestMethod.PUT)
	public String updateRequest(@PathVariable("ticketid") String ticketid,
			@RequestParam(value = "pro_actua", required = true) String pro_actua,
			@RequestParam(value = "pro_content", required = true) String pro_content,
			@RequestParam(value = "pro_user", required = true) String pro_user,
			@RequestParam(value = "pro_dep_code", required = true) String pro_dep_code
			) throws SQLException {
		return service.updateRequest(ticketid,pro_actua,pro_content,pro_user,pro_dep_code);
		//return temp;
	}
	
	// API 15
	@RequestMapping(value = "/updateRequestDetail/{id}", method = RequestMethod.PUT)
	public String updateRequestDetail(@PathVariable("id") String id,
			@RequestParam(value = "receiving_date", required = false, defaultValue="null") String receiving_date,
			@RequestParam(value = "receiving_dep_code", required = false, defaultValue="null") String receiving_dep_code,
			@RequestParam(value = "receiving_user", required = false, defaultValue="null") String receiving_user,
			@RequestParam(value = "actualy_finish", required = false, defaultValue="null") String actualy_finish,
			@RequestParam(value = "return_content", required = false, defaultValue="null") String return_content,
			@RequestParam(value = "return_content_private", required = false, defaultValue="null") String return_content_private,
			@RequestParam(value = "dic_cause_id", required = false, defaultValue="null") String dic_cause_id,
			@RequestParam(value = "dic_cause_id_private", required = false, defaultValue="null") String dic_cause_id_private,
			@RequestParam(value = "file_id", required = false, defaultValue="null") String file_id) throws SQLException {
		return service.updateRequestDetail(id, receiving_date, receiving_dep_code, receiving_user, actualy_finish, return_content, return_content_private, dic_cause_id, dic_cause_id_private, file_id);
	}
}
