package BankProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.mysql.jdbc.Statement;




public class Customer{
	public String url = "jdbc:mysql://localhost:3306/bankdb";
	public String user = "root";
	public String pass = "";
	
	Date date = new Date();
	 //customer sign in
	public boolean SignIn(String anum, String pcode){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = (Statement) conn.createStatement();
			//if the user has inputed some data then this condition is met
			if (anum != null && pcode != null) {
				//query the database with login credentials
			String loginQuery = "Select * from tblAccount where AccountNum = '"+anum+"' and Pin = '"+pcode+"'";
			ResultSet rs = st.executeQuery(loginQuery);
			
			
				if (rs.next()){//if the credentials are correct then it will return true
					return true;
					}
			} 
			conn.close();
			
		} catch (SQLException e) {
			System.err.println("Error signing in! Please try again.");
		}
		System.out.println("Please Try Again.");
		return false;
		
	}

}
	    