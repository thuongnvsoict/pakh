package vn.com.vhc.pakn.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.com.vhc.pakn.model.Processor;
import vn.com.vhc.pakn.model.SystemType; 
import vn.com.vhc.pakh.service.SystemService;

@RestController
public class SystemController {
	
	SystemService service = new SystemService();
	@RequestMapping(value = "/sys/{departmentCode}", method = RequestMethod.GET)
	public List<SystemType> getSystemTypes(
				@PathVariable("departmentCode") String departmentCode) throws Exception {
		return service.getSystemCode(departmentCode);
	}
	
	@RequestMapping(value = "/process/{system_code}", method = RequestMethod.GET)
	public Processor getProcessor(
				@PathVariable("system_code") String system_code) throws Exception {
		return service.getProcessor(system_code);
	}
	
}
