package vn.com.vhc.pakh.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import vn.com.vhc.pakn.model.Cause;

@Service
public class CauseService extends MasterService{
	
	public List<Cause> getDictionaryCause(String level, String id_parent) throws SQLException{
		ResultSet data = null;
		String sql = "";
		if (level.equals("1")) {
			sql = "select * from dictionary_cause where is_parent = 'Y'"; 
		}
		else if (level.equals("2")) {
			sql = "select * from dictionary_cause where is_parent = 'N' and id_has = "+id_parent;
		}
		else {
			return null;
		}
		PreparedStatement ps = connection.prepareStatement(sql);
		data = ps.executeQuery();
		List<Cause> list = new ArrayList<Cause>();
		while(data.next()){
			Cause cause = new Cause();
			cause.setID(data.getString("ID"));
			cause.setCauseCode(data.getString("CAUSE_CODE"));
			cause.setCauseName(data.getString("CAUSE_NAME"));
			cause.setIsEnable(data.getString("IS_ENABLE"));
			cause.setOrdering(data.getString("ORDERING"));
			cause.setCreatedBy(data.getString("CREATED_BY"));
			cause.setIsParent(data.getString("IS_PARENT"));
			cause.setIdHas(data.getString("ID_HAS"));
			cause.setDepCode(data.getString("DEP_CODE"));
			cause.setIsStatus(data.getString("IS_STATUS"));
			cause.setSystemCode(data.getString("SYSTEM_CODE"));
			list.add(cause);
		}
		return list;
	}
}
