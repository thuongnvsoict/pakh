package vn.com.vhc.pakn.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.vhc.pakh.service.CauseService;
import vn.com.vhc.pakn.model.Cause;

@RestController
@RequestMapping("/cause")
public class CauseController {
	
	CauseService service = new CauseService();
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Cause> getDictionaryCause(
			@RequestParam(value = "level", required = true) String level,
			@RequestParam(value = "id_parent", required = false, defaultValue="nothing") String id_parent) 
					throws SQLException {
		return service.getDictionaryCause(level, id_parent);
	}
}
