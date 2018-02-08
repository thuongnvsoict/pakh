package vn.com.vhc.pakh.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import vn.com.vhc.pakn.model.SystemType;

@Service
public class SystemService extends MasterService{
	public List<SystemType> getSystemCode(String departmentCode) {
		ResultSet rs = null;
		String sql = "select * from dep_system_type where dep_code = ?";
		PreparedStatement ps;
		List<SystemType> list = new ArrayList<SystemType>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, departmentCode);
			rs = ps.executeQuery();
			while (rs.next()) {
				SystemType sys = new SystemType();
				sys.setSystemCode(rs.getString("SYSTEM_CODE"));
				list.add(sys);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public String getProcessor(String requestID) {
		ResultSet rs = null;
		String sql = "select * from dep_req where req_id = ?";
		PreparedStatement ps;
		String departmentCode = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, requestID);
			rs = ps.executeQuery();
			if (rs.next()) {
				departmentCode = rs.getString("DEP_CODE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return departmentCode;
		
	}
	
	
}
