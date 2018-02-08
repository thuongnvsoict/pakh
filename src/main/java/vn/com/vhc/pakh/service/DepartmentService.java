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
		String sql = "select * from department";
		PreparedStatement ps = connection.prepareStatement(sql);
		// Set parameter

		data = ps.executeQuery();
		List<Department> list = new ArrayList<Department>();
		while (data.next()) {
			Department dep = new Department();
			dep.setId(data.getString("ID"));
			dep.setDepartmentCode(data.getString("DEP_CODE"));
			dep.setDepartmentName(data.getString("DEP_NAME"));
			list.add(dep);
		}
		return list;

	}
}
