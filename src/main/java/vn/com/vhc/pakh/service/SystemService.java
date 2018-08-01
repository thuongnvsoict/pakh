package vn.com.vhc.pakh.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import vn.com.vhc.pakn.model.Processor;
import vn.com.vhc.pakn.model.SystemType;

@Service
public class SystemService extends MasterService{
	public List<SystemType> getSystemCode(String departmentCode) {
		ResultSet rs = null;
		String sql = "select * from dep_system_type "
				+ "join SYSTEM_TYPE on SYSTEM_TYPE.SYSTEM_CODE = dep_system_type.SYSTEM_CODE "
				+ "where dep_system_type.dep_code = ?";
		
		List<SystemType> list = new ArrayList<SystemType>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, departmentCode);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				SystemType sys = new SystemType();
				sys.setSystemCode(rs.getString("SYSTEM_CODE"));
				sys.setSystemName(rs.getString("SYSTEM_NAME"));
				list.add(sys);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	public Processor getProcessor(String system_code) {
		Processor pr = new Processor();
		ResultSet rs = null;
//		String sql = "select * from dep_req join lich_truc_ca_cntt "
//				+ "on dep_req.dep_code = lich_truc_ca_cntt.dep_code "
//				+ "where dep_req.req_id = ?";
		String sql = "select pd.dep_code,ltc.USERNAME from PRO_DEP_SYSTEM_TYPE pd \n" + 
				"join lich_truc_ca_cntt ltc\n" + 
				"on pd.dep_code = ltc.dep_code \n" + 
				"where pd.system_code = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, system_code);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				pr.setDepartmentCode(rs.getString("dep_code"));
				pr.setProUser(rs.getString("USERNAME")); 
			}
			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pr;

	}
		
}
