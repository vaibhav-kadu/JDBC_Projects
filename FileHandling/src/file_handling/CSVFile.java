package file_handling;

import java.sql.*;
import java.io.*;

public class CSVFile {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","root");
		if(conn!=null) {
			System.out.println("DB Connected");	
			FileReader fr=new FileReader("C:\\Users\\vkdon\\Desktop\\Projects\\JDBC_Projects\\FileHandling\\src\\file_handling\\emp.csv");
			BufferedReader br=new BufferedReader(fr);
			String line;
			while((line=br.readLine())!=null) {
				String col[]=line.split(",");
								//"Name",	"Phone"
				System.out.println(col[0]+"\t"+col[1]);
				PreparedStatement pstmt = conn.prepareStatement("insert into empdata (name,phone) values(?,?)");
							//"Name",					"Phone"
				pstmt.setString(1,col[0]);pstmt.setString(2,col[1]);
				pstmt.executeUpdate();
			}
			
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("select * from empdata");
			System.out.println("--------------------------------------------");
			System.out.printf("| %10s | %10s | %12s |\n","Id","Name","Phone");
			System.out.println("--------------------------------------------");
			while(rs.next()) {
				System.out.printf("| %10s | %10s | %12s |\n", 
						rs.getString("id"),	rs.getString("name"),rs.getString("phone"));
			}
			System.out.println("--------------------------------------------");
			br.close();
		}else System.out.println("DB NOt Connected");		
		
		
		conn.close();

	}

}
