package crud_operations_sql;

import java.sql.*;
import java.util.Scanner;

public class CreateTable {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
	
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","root");
		
		if(conn==null) {
			System.out.println("SQL Server is Not Connected");
		}else {
			
			System.out.println("SQL Server Connected");
			
			Statement statement = conn.createStatement();
			
			System.out.println("Enter Table Name");
		
			Scanner scanner=new Scanner(System.in);
			
			String tbName=scanner.nextLine();
			
			boolean found=false;
			
			ResultSet resultSet = statement.executeQuery("show tables");
			
			while(resultSet.next()) {
				if(resultSet.getString(1).equals(tbName)) {
					System.out.println("Table is Already Exists");
					found=true;
					break;
				}
			}
			
			int operation = statement.executeUpdate("create table if not exists "+tbName+" (id int primary key auto_increment,name varchar(100))");
			
			if(operation==0) {
				if(!found) {
					System.out.println("Table is Created");					
				}
				
				resultSet=statement.executeQuery("desc "+tbName+"");
				
				System.out.println("-----------------------------------------------------------------------------------------------------------");
				System.out.printf(" | %15s | %15s | %8s | %8s | %15s | %15s | \n","Field","Type","Null","Key","Default","Extra"); 					
				System.out.println("-----------------------------------------------------------------------------------------------------------");
				while(resultSet.next()) {
					System.out.printf(" | %15s | %15s | %8s | %8s | %15s | %15s | \n",
						 	resultSet.getString("Field"),
						 	resultSet.getString("Type"),
						 	resultSet.getString("Null"),
						 	resultSet.getString("Key"),
						 	resultSet.getString("Default"),
						 	resultSet.getString("Extra"));
				}
				System.out.println("-----------------------------------------------------------------------------------------------------------");
					
			}else if(operation==1) {
				System.out.println("Table is Not Created ");	
			}
			
			
			
			scanner.close();
			statement.close();
			conn.close();
			
		}

	}

}
