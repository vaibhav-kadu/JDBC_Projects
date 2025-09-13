package crud_operations_sql;
import java.sql.*;

public class ConnectToSqlServer {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String url="jdbc:mysql://localhost:3306";
		String user="root";
		String password="root";
		
		Connection conn= DriverManager.getConnection(url,user,password);
		
		if(conn==null) {
			System.out.println("SQL Server is Not Connected");
			return;
		}else {
			System.out.println("SQL Server Connected");
		}
		
		conn.close();

	}

}
