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
	
	public User getUser(String username, String password) throws SQLException, NamingException, NoSuchAlgorithmException{
		ResultSet data = null;
		String sql = "select * from users where upper(username) = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, username.toUpperCase());
		data = ps.executeQuery();
		String passwordMD5 = generateMD5(password);
		
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
			
			if (user.getPassword().equals(passwordMD5)) {
				return user;
			}else {
				System.out.println("This is LDAP password");
				boolean existUser = LDAP.authentication(username+"@mobifone.vn", password);
				if (existUser == true) {
					return user;
				}else {
					return null;
				}
			}
		}else {
			return null;
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
/*
class LDAP {
	public static String authMethod 	= "simple";
	public static String ldapVersion 	= "3";
	//public static String ldapHost 		= "10.151.6.248";
	public static String ldapHost 		= "10.3.12.57";
	public static String ldapPort 		= "389";
	private static Logger logger = Logger.getLogger(LDAP.class);
	private static String ATTRIBUTE_FOR_USER = "sAMAccountName";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Attributes authenticateUser(String username, String password){
        if(username==null||"".equals(username.trim())||password==null||"".equals(password.trim()))
            return null;            

        String searchFilter = "(&(objectClass=user)(" + ATTRIBUTE_FOR_USER + "=" + username + "))";
        // Create the search controls

        SearchControls searchCtls = new SearchControls();
        // searchCtls.setReturningAttributes(returnedAtts);
        // Specify the search scope
        searchCtls.setSearchScope(SearchControls.OBJECT_SCOPE);
        String searchBase = "";
        Hashtable environment = new Hashtable();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        // Using starndard Port, check your instalation
        environment.put(Context.PROVIDER_URL, "ldap://" + ldapHost);
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");

        environment.put(Context.SECURITY_PRINCIPAL, username);
        environment.put(Context.SECURITY_CREDENTIALS, password);
        LdapContext ctxGC = null;
        try{	
        	logger.info("Connecting to host " + ldapHost + " at port " + ldapPort + "...");
            ctxGC = new InitialLdapContext(environment, null);
            // Search for objects in the GC using the filter
            NamingEnumeration answer = ctxGC.search(searchBase, searchFilter, searchCtls);
            while (answer.hasMoreElements())
            {
                SearchResult sr = (SearchResult) answer.next();
                Attributes attrs = sr.getAttributes();
                if (attrs != null)
                {
                    return attrs;
                }
            }
        }
        catch (NamingException e){
        	logger.error("LDAP authentication failed: " + e.toString());
        	System.out.println("LDAP authentication failed: " + e.toString());
        	System.out.println("LDAP authentication failed: " + e.getMessage());
            return null;
        }
        return null;
    }
	
	public static boolean authentication(String strUser, String strPass) throws NamingException{
        LDAP ldap = new LDAP();
        Attributes att = ldap.authenticateUser(strUser, strPass);
        if (att != null){
            return true;
        }
        return false;
    }
}
*/

