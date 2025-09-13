package crud_operations_sql;
import java.sql.*;
import java.util.*;

public class Select {
	
	public static void main(String [] args) throws Exception{
		
		Scanner sc=new Scanner(System.in);
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","root");
		
		if(conn!=null) {
			System.out.println("Database Connected");
			
			Statement stm = conn.createStatement();
			
			ResultSet rs = stm.executeQuery("select * from empdata");
			
			System.out.println("--------------------------------------------");
			System.out.printf("| %10s | %10s |\n","id","Name");
			System.out.println("--------------------------------------------");
			while(rs.next()) {
				System.out.printf("| %10s | %10s |\n", 
						rs.getString("id"),
						rs.getString("name"));
			}

			System.out.println("--------------------------------------------");
		}else System.out.println("DB Not Connected");
		
		sc.close();
		conn.close();
	}

}
