package crud_operations_sql;

import java.sql.*;
import java.util.*;

public class Insert {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","root");
		if(conn!=null) {
			System.out.println("SQL Connected .....");
			Statement statement = conn.createStatement();
			String choice="";
			String query="insert into empdata (name) values('";
			 
			 do {
				 System.out.println("Enter Employee Name");
				 query=query+sc.nextLine();
				 System.out.println("Press Y to Add More y/n");
				 choice=sc.nextLine();
				 if(choice.equals("y")) query=query+"'),('";
				 
			 }while(choice.equals("y"));
			 
			 query=query+"')";
			 
			 System.out.println(query);			 
			 
			int op=statement.executeUpdate(query);
			
			if(op!=0) {
				System.out.println("Data Inserted Successfully");
				op=statement.executeUpdate("insert into empdata (name) values('vaibhav'),('kadu')");
			}
			else {System.out.println("Error in Data Insertion");}
		}
		sc.close();
		conn.close();

	}

}
