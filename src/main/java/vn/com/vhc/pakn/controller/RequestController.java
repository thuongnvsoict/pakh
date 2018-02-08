package vn.com.vhc.pakn.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.vhc.pakh.service.RequestService;
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

	@RequestMapping(value = "/{req_title}/{pro_dep_code}/{req_content}/{receiving_sms}/{receiving_email}/{fileDir}/{req_status}", method = RequestMethod.POST)
	public String postRequest(@PathVariable("req_title") String req_title,
				@PathVariable("pro_dep_code") String pro_dep_code,
				@PathVariable("req_content") String req_content,
				@PathVariable("receiving_sms") String receiving_sms,
				@PathVariable("receiving_email") String receiving_email,
				@PathVariable("fileDir") String fileDir,
				@PathVariable("req_status") String req_status
			) throws Exception {
		return service.postRequest(req_title, pro_dep_code, req_content, receiving_sms, receiving_email, fileDir, req_status);
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getRequest(@RequestParam(value = "req_title", required = false) String req_title,
			@RequestParam(value = "req_system_code", required = false) String req_system_code,
			@RequestParam(value = "req_dep_code", required = false) String req_dep_code,
			@RequestParam(value = "req_user", required = false) String req_user,
			@RequestParam(value = "pro_dep_code", required = false) String pro_dep_code,
			@RequestParam(value = "pro_user", required = false) String pro_user,
			//Ngay gui yeu cau
			@RequestParam(value = "ticketid", required = false) String ticketid,
			@RequestParam(value = "req_status", required = false) String req_status
			) throws Exception {
		return req_title;
	}
}
