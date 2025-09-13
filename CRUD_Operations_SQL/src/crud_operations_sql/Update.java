package crud_operations_sql;

import java.sql.*;
import java.util.*;

public class Update {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Scanner sc=new Scanner(System.in);
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","root");
		
		if(conn!=null) {
			System.out.println("DB Connected");
			
			Statement statement = conn.createStatement();
			
			System.out.println("Update Employee Name");
			System.out.print("\nEnter Old Name=>");
			String old=sc.nextLine();
			System.out.print("\nEnter New Name=>");
			String New=sc.nextLine();
			
			int op = statement.executeUpdate("update empdata set name='"+New+"' where name='"+old+"' ");
			
			if(op!=0) System.out.println("Name Changed");
			else System.out.println("Name Not Changed");
			
		}else System.out.println("DB Not Connected");
		
		sc.close();
		conn.close();
	}

}
