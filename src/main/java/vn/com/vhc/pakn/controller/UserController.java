package vn.com.vhc.pakn.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.com.vhc.pakh.service.UserService;
import vn.com.vhc.pakn.model.User;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class UserController {
	
	UserService service = new UserService();
	
	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
	public User checkUser(@PathVariable("username") String username) throws SQLException {
		return service.getUserInfo(username);
	}
	
	@RequestMapping(value = "/staff/{dep_code}", method = RequestMethod.GET)
	public List<User> getStaffDepartment(@PathVariable("dep_code") String dep_code) throws SQLException  {
		return service.getStaffDepartment(dep_code);
	}
	
}