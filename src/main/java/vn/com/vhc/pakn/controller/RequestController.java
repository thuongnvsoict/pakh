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
import vn.com.vhc.pakn.model.RequestType;

@RestController
@RequestMapping("/request")
public class RequestController {
	RequestService service = new RequestService();
	
	@RequestMapping(value = "/{departmentCode}/{systemCode}/{isHas}", method = RequestMethod.GET)
	public List<RequestType> getRequestTypes(@PathVariable("departmentCode") String departmentCode,
				@PathVariable("systemCode") String systemCode,
				@PathVariable("isHas") String isHas
			) throws Exception {
		return service.getRequestTypes(departmentCode, systemCode, isHas);
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String postRequest(
			@RequestParam("req_dep_code") String req_dep_code,
			@RequestParam("req_user") String req_user,
			@RequestParam("req_system_code") String req_system_code,
			@RequestParam("req_title") String req_title,
			@RequestParam("pro_dep_code") String pro_dep_code,
			@RequestParam("req_content") String req_content,
			@RequestParam("receiving_sms") String receiving_sms,
			@RequestParam("receiving_email") String receiving_email,
			@RequestParam("file_dir") String fileDir,
			@RequestParam("req_status") String req_status
			) throws Exception {
		return service.postRequest(req_dep_code, req_user, req_system_code, req_title, pro_dep_code, req_content, receiving_sms, receiving_email, fileDir, req_status);
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<Request> getRequest(@RequestParam(value = "req_title", required = false, defaultValue="%%") String req_title,
			@RequestParam(value = "req_system_code", required = false, defaultValue="%%") String req_system_code,
			@RequestParam(value = "req_dep_code", required = false, defaultValue="%%") String req_dep_code,
			@RequestParam(value = "req_user", required = false, defaultValue="%%") String req_user,
			@RequestParam(value = "pro_dep_code", required = false, defaultValue="%%") String pro_dep_code,
			@RequestParam(value = "pro_user", required = false, defaultValue="%%") String pro_user,
			//Ngay gui yeu cau
			@RequestParam(value = "ticketid", required = false, defaultValue="%%") String ticketid,
			@RequestParam(value = "req_status", required = false, defaultValue="%%") String req_status
			) throws Exception {
		System.out.println("helo"+ pro_dep_code);
		return service.getRequest(req_title, req_system_code, req_dep_code, req_user, pro_dep_code, pro_user, ticketid, req_status);
	}
	
	@RequestMapping(value = "/num/{req_status}", method = RequestMethod.GET)
	public int getNum(@PathVariable("req_status") String req_status) throws SQLException {
		return service.getNum(req_status);
	}
}
