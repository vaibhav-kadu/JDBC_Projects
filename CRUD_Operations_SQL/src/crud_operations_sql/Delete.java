package crud_operations_sql;

import java.sql.*;
import java.util.*;

public class Delete {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Scanner sc= new Scanner(System.in);
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","root");
		
		if(conn!=null) {
			System.out.println("DB connected");
			Statement stm = conn.createStatement();
			System.out.println("Enter Name To delete");
			String name=sc.nextLine();
			int op=stm.executeUpdate("Delete from empdata where name='"+name+"' ");
			if(op!=0) {
				System.out.println("Data Delete Successfully");
			}
			else {System.out.println("Error in Data Deletation");}
		}else System.out.println("DB Not onnected");
		
		sc.close();
		conn.close();

	}

}
