package BankProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;




import com.mysql.jdbc.Statement;

public class withdrawal extends Customer {
	
	public void Withdraw(String anum, double withdrawl){
		
		try{
			int AccType = 0;
			double bal = 0;
			double over = 0;
			
			Connection conn = null;
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = (Statement) conn.createStatement();
			ResultSet rset = st.executeQuery("select AccountTypeID, Overdraft, Balance from tblaccount where AccountNum = '"+anum+"' ");
			//get the values from the database
			while(rset.next()){
			AccType = rset.getInt("AccountTypeID");
			 over = rset.getDouble("Overdraft");
			 bal = rset.getDouble("Balance");
			
			}
			double remainingBalance = bal - over;
			
			//if the account type is a current account and the withdrawal has exceeded the planned overdraft
			if(AccType == 1 && remainingBalance < withdrawl){
				
				System.out.println("Sorry, you do not have enough funds to make this withdrawal!");
				System.out.println("");
				System.out.println("You will now be charged 15% on anything you withdraw over your current overdraft plan.");
				//15% charge on anything withdrawn over the overdraft limit
				Double OverDDiff = withdrawl * 0.15;
				withdrawl = withdrawl + OverDDiff;
				//writes the withdrawal to the database
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
		System.out.println("Error making Deposit. Server could not complete the process.");
		}	
	       
	            }

	}


