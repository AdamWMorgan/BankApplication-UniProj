package BankProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

public class deposit extends Customer {

	public void Deposit(String anum, double deposit){
		try{
			double interest = 0;
			Connection conn = null;
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = (Statement) conn.createStatement();
			//fetch the specific data from the database for the individual user
			ResultSet rset = st.executeQuery("select InterestRate from tblaccount where AccountNum = '"+anum+"' ");
			
			while(rset.next()){//get value from the database
			interest = rset.getDouble("InterestRate");
			}
			
			Double AddedInt = deposit * (interest/100);
			
			deposit = deposit + AddedInt;
			//update the database with the new data
			st.executeUpdate("update tblaccount set Balance = '"+deposit+"' + Balance where AccountNum = '"+anum+"'");
			//update the transaction table to keep a log of this transaction
			st.executeUpdate("insert into tbltransaction(AccountNum, Deposit) Values('"+anum+"', '"+deposit+"')");

			conn.close();
			st.close();
			//write transaction to text file
			FileWriter outFile = new FileWriter(anum +  "_transaction.txt", true);
	           BufferedWriter outStream = new BufferedWriter(outFile);
	           		outStream.write(String.valueOf("Deposit    " + " " + "£" + deposit + "     " + date));
	           		outStream.newLine();
	           		//remove old data from the outStream
	           		outStream.flush();
	           		outStream.close();  
			
		}catch(Exception b){
			System.out.println("Error making Deposit. Server could not complete the process.");
		}	
	}

}
