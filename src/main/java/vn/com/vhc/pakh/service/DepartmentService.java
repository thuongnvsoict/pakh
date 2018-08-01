package vn.com.vhc.pakh.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import vn.com.vhc.pakn.model.Department;

@Service
public class DepartmentService extends MasterService{
	public List<Department> getDepartment() throws SQLException{
		ResultSet data = null;
		String sql = "select * from phong_ban";
		PreparedStatement ps = connection.prepareStatement(sql);
		// Set parameter
		data = ps.executeQuery();
		
		List<Department> list = new ArrayList<Department>();
		while (data.next()) {
			Department dep = new Department();
			dep.setId(data.getString("ID"));
			dep.setDepartmentCode(data.getString("MA_PHONG"));
			dep.setDepartmentName(data.getString("TEN_PHONG"));
			list.add(dep);
		}
		
		data.close();
		ps.close();
		
		return list;

	}
}
