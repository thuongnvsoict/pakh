package vn.com.vhc.pakh.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;

import vn.com.vhc.pakn.LDAP;
import vn.com.vhc.pakn.model.User;

@Service
public class UserService extends MasterService{
	
	public User getUserInfo(String username) throws SQLException{
		ResultSet data = null;
		String sql = "select * from users where upper(username) = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, username.toUpperCase());
		data = ps.executeQuery();
		
		User user = null;
		if(data.next()){
			user = new User();
			user.setId(data.getString("ID"));
			user.setUsername(data.getString("username"));
			user.setPassword(data.getString("password"));
			user.setFullname(data.getString("fullname"));
			user.setPhone(data.getString("phone"));
			user.setEmail(data.getString("email"));
			user.setGender(data.getString("sex"));
			user.setPosition(data.getString("position"));
			user.setIsEnable(data.getString("is_enable"));
			user.setDepartmentCode(data.getString("MA_PHONG"));
		}
		
		data.close();
		ps.close();
		
		if (user == null) {
			return null;
		}else {
			return user;
		}
		
		
	}
	
	public List<User> getStaffDepartment(String dep_code) throws SQLException{
		ResultSet data = null;
		String sql = "select * from users where MA_PHONG = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, dep_code);
		data = ps.executeQuery();
		
		List<User> list = new ArrayList<User>();
		
		while(data.next()){
			User user = new User();
			user.setId(data.getString("ID"));
			user.setUsername(data.getString("username"));
			user.setPassword(data.getString("password"));
			user.setFullname(data.getString("fullname"));
			user.setPhone(data.getString("phone"));
			user.setEmail(data.getString("email"));
			user.setGender(data.getString("sex"));
			user.setPosition(data.getString("position"));
			user.setIsEnable(data.getString("is_enable"));
			user.setDepartmentCode(data.getString("MA_PHONG"));
			list.add(user);
		}
		
		data.close();
		ps.close();
		
		return list;
		
	}
	
	public User getUser(String username, String password) throws SQLException, NamingException, NoSuchAlgorithmException{
		ResultSet data = null;
		String sql = "select * from users where upper(username) = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, username.toUpperCase());
		
		data = ps.executeQuery();
		
		String passwordMD5 = generateMD5(password);
		User user = null;
		
		int flag = 0;
		if(data.next()){
			user = new User();
			user.setId(data.getString("ID"));
			user.setUsername(data.getString("username"));
			user.setPassword(data.getString("password"));
			user.setFullname(data.getString("fullname"));
			user.setPhone(data.getString("phone"));
			user.setEmail(data.getString("email"));
			user.setGender(data.getString("sex"));
			user.setPosition(data.getString("position"));
			user.setIsEnable(data.getString("is_enable"));
			user.setDepartmentCode(data.getString("MA_PHONG"));
			
			if (user.getPassword().equals(passwordMD5)) {

				flag = 1;
			}else {
				System.out.println("This is LDAP password");
				boolean existUser = LDAP.authentication(username+"@mobifone.vn", password);
				if (existUser == true) {
					flag = 1; 
				}else {
					flag = 0;
				}
			}
		}else {
			flag = 0;
		}
		
		data.close();
		ps.close();
		
		if (flag == 0) {
			return null;
		}else {
			return user;
		}

		
	}
	
	public String generateMD5(String initString) throws NoSuchAlgorithmException {	
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(initString.getBytes());
		byte byteData[] = md.digest();
		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		
		return sb.toString();
	}
}