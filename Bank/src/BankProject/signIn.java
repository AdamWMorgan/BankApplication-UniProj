package BankProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

public class signIn extends Customer {
	
	public void signInName(String anum){
	try{
		
		Connection conn = null;
		conn = DriverManager.getConnection(url, user, pass);
		Statement st = (Statement) conn.createStatement();
		ResultSet rset = st.executeQuery("select firstName from tblcustomer, tblaccount where tblcustomer.CustomerID = tblaccount.CustomerID and tblaccount.AccountNum = '"+anum+"'");
		while(rset.next()){//get data from the database
		String name = rset.getString("firstName");
		System.out.format("Welcome, " + name );//output the specific data in a greeting message
		}
		conn.close();
		st.close();
	
	}catch(Exception b){
	System.out.println("Error making Deposit. Server could not complete the process.");
	}	
}
}



