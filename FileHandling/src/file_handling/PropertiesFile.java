package file_handling;

import java.util.*;
import java.sql.*;
import java.io.*;

public class PropertiesFile {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File f=new File("");
		FileInputStream fs= new FileInputStream(f.getAbsoluteFile()+"\\src\\file_handling\\config.properties");
		
		Properties p = new Properties();
		
		p.load(fs);
		
		String user=p.getProperty("user");
		String password=p.getProperty("password");
		String url=p.getProperty("url");
		String db=p.getProperty("db");
		String driverClassname=p.getProperty("driverName");
		
		Class.forName(driverClassname);
		
		Connection conn = DriverManager.getConnection(url+""+db,user,password);
		
		if(conn!=null) {
			System.out.println("|------------------------------|");
			System.out.printf("| %20s |\n","DB Connected Through File");
			System.out.println("|------------------------------|");
			
			Statement stm=conn.createStatement();
			
			ResultSet rs = stm.executeQuery("show databases");
			while(rs.next()) {
				System.out.printf("| %20s |\n",rs.getString(1));
			}
			System.out.println("|------------------------------|");
		}else System.out.println("DB Not Connected Through File");
		

	}

}
