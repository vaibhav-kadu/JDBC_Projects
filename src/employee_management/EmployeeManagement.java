package employee_management;

import java.sql.*;
import java.util.*;

public class EmployeeManagement {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String url="jdbc:mysql://localhost:3306/employee";
		String user="root";
		String password="root";
		String deptTable="Create table if not exists dept("+
							"deptid int (10) primary key auto_increment,"+
							" name varchar(100))";
		
		String empTable="create table if not exists employee("+
							"id int (10) primary key auto_increment,"+
							"name varchar(100),"+
							"email varchar(200),"+
							"contact varchar(13),"+
							"salary int (10),"+
							"deptid int (10),"+
							"foreign key (deptid) references dept(deptid) on delete cascade"+
						")";
		Scanner sc=new Scanner(System.in);
		Connection conn=DriverManager.getConnection(url, user, password);
		
		if(conn!=null) {
			System.out.println("Database Connected");
			Statement stm=conn.createStatement();
			ResultSet result;
			if(stm.executeUpdate(deptTable)==0){
				System.out.println("Department Table Created");				
				if(stm.executeUpdate(empTable)==0) {
					System.out.println("Empolyee Table Created");
					String choise="";
					String choice2="";
					String name,email,contact,salary,deptid;
					int op=-1;
					do {
						System.out.println("-------Select Option------- \n"+
								"1.Add Department\n"+
								"2.View All Departments\n"+
								"3.Add Employee\n"+
								"4.View All Employee\n"+
								"5.Employee Department Wise\n"+
								"6.Department wise employee Count\n"+
								"7.Delete Employee Using DeptName\n"+
								"8.Find Employee by Prefix\n"+
								"9.Salary is 10000,20000,30000 & deptid is > 1\n"+
								"0.Exit\n");
						choise=sc.nextLine();
						switch(choise) {
						case "1":
							String deptName="";
							do {
								op=-1;choice2="";
								System.out.println("Enter Department Name");
								deptName=sc.nextLine();
								if(deptName!="") {op=stm.executeUpdate("insert into dept (name) value('"+deptName+"')");}
								else {System.out.println("Empty Input Not Insterted");}
								if(op==0) {System.out.println("Error in Inserting Data");}
								else {
									System.out.println("Press 1 For Add More or 0 to Exit");
									choice2=sc.nextLine();
								}
							}while(choice2.equals("1"));							
							break;
							
						case "2":
							System.out.println("All Department");
							result=stm.executeQuery("select * from dept");
							while(result.next()) {
								System.out.println(result.getString("deptid")+"\t"+result.getString("name"));
							}
							break;
							
						case "3":
							do {
								op=-1;choice2="";
								System.out.println("Enter Employee Name , Email, contact, Salary, Salary, Deptid");
								name=sc.nextLine();
								email=sc.nextLine();
								contact=sc.nextLine();
								salary=sc.nextLine();
								deptid=sc.nextLine();
								if(name!="" && email!="" && contact!="" && salary!="" && deptid!=""){
									op=stm.executeUpdate("insert into employee value('"+0+"','"+name+"','"+email+"','"+contact+"','"+salary+"','"+deptid+"')");
								}else {System.out.println("Empty Input Not Insterted");}
								if(op==0) {System.out.println("Error in Inserting Data");}
								else if(op==1){
									System.out.println("Press 1 For Add More or 0 to Exit");
									choice2=sc.nextLine();
								}
							}while(choice2.equals("1"));
							break;
							
						case "4":
							System.out.println("All Employee");
							result=stm.executeQuery("select e.name,e.email,e.contact,e.salary,d.name from employee e inner join dept d on e.deptid=d.deptid");
							System.out.println("------------------------------------------------------------------------------------------");
							System.out.printf(" | %-15s | %-20s | %-12s | %-10s | %-10s |\n","NAME","EMAIL","CONTACT","SALARY","DEPARTMENT");
							System.out.println("------------------------------------------------------------------------------------------");
							while(result.next()) {
								System.out.printf(" | %-15s | %-20s | %-12s | %-10s | %-10s |\n",
										result.getString("e.name"),
										result.getString("e.email"),
										result.getString("e.contact"),
										result.getString("e.salary"),
										result.getString("d.name"));
							}
							System.out.println("------------------------------------------------------------------------------------------");
							break;
							
						case "5":
							System.out.println("Department Wise Employee");
							result=stm.executeQuery("select d.name, e.name from employee e inner join dept d on e.deptid=d.deptid order by d.name");
							System.out.println("DEPARTMENT \t NAME");
							while(result.next()) {
								System.out.println(result.getString("d.name")+" \t "+
													result.getString("e.name"));
							}
							break;
							
						case "6":
							System.out.println("Department wise count");
							result=stm.executeQuery("select d.name,count(e.id) as empcount from employee e join dept d on e.deptid=d.deptid group by e.deptid");
							while(result.next()) {
								System.out.println(result.getString("d.name")+" \t "+
										result.getString("empcount"));
							}
							break;
							
						case "7":
							op=-1;
							System.out.println("Enter Department Name to Delete Employee");
							name=sc.nextLine();
							op=stm.executeUpdate("delete from employee where deptid=(select deptid from dept where name='"+name+"')");
							if(op==0) {
								System.out.println("Employee Not Deleted");
							}else if(op==1)System.out.println("Employee Deleted");
							break;
							
						case "8":
							System.out.println("Find Employee by Prefix = ");
							name=sc.nextLine();
							result=stm.executeQuery("select * from employee where name like '"+name+"%'");
							while(result.next()) {
								System.out.println(result.getString("name")+" \t "+
										result.getString("email")+" \t "+
										result.getString("contact")+" \t "+
										result.getString("salary")+" \t "+
										result.getString("deptid"));
							}
							break;
							
						case "9":
							System.out.println("Salary in 10000,20000,30000 & deptid is > 1");
							result=stm.executeQuery("select * from employee where salary in(10000,20000,30000) and deptid > 1");
							while(result.next()) {
								System.out.println(result.getString("name")+" \t "+
										result.getString("salary")+" \t "+
										result.getString("deptid"));
							}
							break;
							
						case "0":
							System.out.println("System Exited");
							System.exit(0);
							break;
						default:
							System.out.println("Invalid Input");
						}
					}while(true);
					
				}else {
					System.out.println("Error In Creating Employee Table");
				}
			}else {
				System.out.println("Error In Creating Department Table");
			}
			
			
			sc.close();
			stm.close();
			conn.close();
		}else{
			System.out.println("Not Connected");
			}
		

	}

}
