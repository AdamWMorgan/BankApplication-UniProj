package BankProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

public class currentAccount extends withdrawal{
	
	@Override	
	
	public void Withdraw(String anum, double withdrawl){ //override method if a current account goes over its overdraft limit
		
		try{
			
			Connection conn = null;
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = (Statement) conn.createStatement();
			
			//fetch the specific data from the database for the individual user
			ResultSet rs = st.executeQuery("SELECT Overdraft, Balance FROM tblaccount where AccountNum = '"+anum+"'");
			double bal = 0;
			double over = 0;
			
		while (rs.next()){
			 over = rs.getDouble("Overdraft");
			 bal = rs.getDouble("Balance");
		}
		
		double remainingBalance = bal - over;
		//ensures that the customer has enough money to make the withdrawal
		if (remainingBalance >= withdrawl){
			//update database with withdrawal information
			st.executeUpdate("update tblaccount set Balance = Balance - '"+withdrawl+"' where AccountNum = '"+anum+"'");
			st.executeUpdate("insert into tbltransaction(AccountNum, Withdrawal) Values('"+anum+"', '"+withdrawl+"')");
			//writes the transaction details to a text file
			FileWriter outFile = new FileWriter(anum +  "_transaction.txt", true);
	           BufferedWriter outStream = new BufferedWriter(outFile);
	           		outStream.write(String.valueOf("Withdrawal    " + " " + "£"+ withdrawl + "     " + date));
	           		//ensures that the next data will be written to a separate line
	           		outStream.newLine();
	           		//remove old data in the outStream
	           		outStream.flush();
	           		outStream.close();  
		}
		
			
		
			conn.close();
			st.close();
			
		}catch(Exception b){
			System.err.println("Error making withdrawal. Server could not complete this transaction.");
		}	
		
	}

}
