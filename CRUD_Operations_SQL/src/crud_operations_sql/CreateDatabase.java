package crud_operations_sql;

import java.sql.*;
import java.util.Scanner;

public class CreateDatabase {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
	
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","root");
		
		if(conn==null) {
			System.out.println("SQL Server is Not Connected");
		}else {
			
			System.out.println("SQL Server Connected");
			
			Statement statement = conn.createStatement();
			
			System.out.println("Enter Database Name");
		
			Scanner scanner=new Scanner(System.in);
			
			String dbName=scanner.nextLine();
			
			boolean found=false;
			
			ResultSet resultSet = statement.executeQuery("show databases");
			
			while(resultSet.next()) {
				if(resultSet.getString(1).equals(dbName)) {
					System.out.println("Database is Already Exists");
					found=true;
					break;
				}
			}
			
			if(!found) {

				int operation = statement.executeUpdate("create database if not exists "+dbName+"");
				
				if(operation==0) {
					System.out.println("Database is Not Created");
				}else if(operation==1) {
					System.out.println("Database is Created ");	
				}
				
			}
			
			scanner.close();
			statement.close();
			conn.close();
			
		}

	}

}
