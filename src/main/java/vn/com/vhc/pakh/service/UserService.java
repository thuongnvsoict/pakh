package vn.com.vhc.pakh.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import vn.com.vhc.pakn.model.User;

@Service
public class UserService extends MasterService{
	
	public User getUserInfo(String username) throws SQLException{
		ResultSet data = null;
		String sql = "select * from users where upper(username) = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, username.toUpperCase());
		data = ps.executeQuery();
		
		if(data.next()){
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
			return user;
		}else {
			return null;
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
		return list;
		
	}
}
