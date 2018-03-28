package vn.com.vhc.pakn.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.com.vhc.pakh.service.UserService;
import vn.com.vhc.pakn.model.User;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {
	
	UserService service = new UserService();
	
	@RequestMapping(value = "/user/{username:.+}", method = RequestMethod.GET)
	public User checkUser(@PathVariable("username") String username) throws SQLException {
		return service.getUserInfo(username);
	}
	
	@RequestMapping(value = "/staff/{dep_code}", method = RequestMethod.GET)
	public List<User> getStaffDepartment(@PathVariable("dep_code") String dep_code) throws SQLException  {
		return service.getStaffDepartment(dep_code);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public User checkUserPassword(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) throws SQLException, NamingException, NoSuchAlgorithmException {
		return service.getUser(username, password);
	}
}
