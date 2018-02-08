package vn.com.vhc.pakn.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.com.vhc.pakh.service.DepartmentService;
import vn.com.vhc.pakn.model.Department;

@RestController
@RequestMapping("/depart")
public class DepartmentController {
	
	DepartmentService service = new DepartmentService();
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Department> getDepartment() throws SQLException{
		return service.getDepartment();
	}
}
