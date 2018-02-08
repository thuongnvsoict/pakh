package vn.com.vhc.pakn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vn.com.vhc.pakh.service.MasterService;
import vn.com.vhc.pakh.service.UserService;
import vn.com.vhc.pakn.model.DataSource;

@SpringBootApplication
public class PhanAnhKhachHang implements CommandLineRunner{
	
	@Autowired
	private DataSource ds;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(PhanAnhKhachHang.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		ds = new DataSource(ds.getAddress(), 
					ds.getPort(), ds.getSid(),  ds.getUserdb(), ds.getPassword());
//		System.out.println("Address = " + ds.getAddress());
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	System.out.println("Where is your Oracle JDBC Driver?"); 
        }
		MasterService.connection = null;
        try {
        	UserService.connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@"+ds.getAddress()+":"+ds.getPort()+":"+ds.getSid()+"", ds.getUserdb(), ds.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed! Check output console");
        }
	}

}
