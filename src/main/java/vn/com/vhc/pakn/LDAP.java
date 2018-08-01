package vn.com.vhc.pakn;

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

public class LDAP {

	public static String authMethod 	= "simple";
	public static String ldapVersion 	= "3";
	//public static String ldapHost 		= "10.151.6.248";
	public static String ldapHost 		= "10.3.12.57";
	public static String ldapPort 		= "389";

	private static Logger logger = Logger.getLogger(LDAP.class);
	
	private static String ATTRIBUTE_FOR_USER = "sAMAccountName";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Attributes authenticateUser(String username, String password)
    {
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
        try
        {	
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
        catch (NamingException e)
        {
        	logger.error("LDAP authentication failed: " + e.toString());
        	System.out.println("LDAP authentication failed: " + e.toString());
        	System.out.println("LDAP authentication failed: " + e.getMessage());
            return null;
        }
        return null;
    }
	
	public static boolean authentication(String strUser, String strPass) throws NamingException
    {
        LDAP ldap = new LDAP();
        Attributes att = ldap.authenticateUser(strUser, strPass);
        if (att != null)
        {
            return true;
        }
        return false;
    }
	/*
	public static void main(String arg[]) throws NamingException {
		System.out.println(authentication("pakh.tctk", "ktdh12345"));
		//System.out.println(authentication("HONG.NT", "mobifone@2017"));
	}
	*/
}
