package BankProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

public class balance extends Customer {

	public void Balance(String anum){
			
		try{
			
			Connection conn = null;
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = (Statement) conn.createStatement();
			
			//query to fetch the value of balance from the database for the specific account number
			ResultSet rs = st.executeQuery("SELECT Balance FROM tblaccount where AccountNum = '"+anum+"'");
		while (rs.next()){//get the value of balance and then output in a message
			double bal = rs.getDouble("Balance");
			System.out.format("Your Balance is: £" + "%s\n", bal);
		}
			conn.close();
			st.close();
			
		}catch(Exception b){//error with the query
			System.err.println("Error fetching Balance.");
		}	
		
	}

}
