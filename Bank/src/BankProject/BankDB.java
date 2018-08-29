package BankProject;

import java.sql.Connection;
import java.sql.DriverManager;

public class BankDB {
	
	
	public static void BankData(){
		
		String url = "jdbc:mysql://localhost:3306/bankdb";
		String user = "root";
		String pass = "";
		
		try{
			//check to ensure the database is connected to the project
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		conn = DriverManager.getConnection(url, user, pass);
		System.out.println("Database Connected");
		conn.close();
		}catch(Exception a){
			System.err.println("Error connecting to the database!");//database is not connected
			System.exit(0);
			
		}
	}

}
