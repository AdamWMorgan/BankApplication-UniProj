package BankProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;

public class Bank {
	
	private String url = "jdbc:mysql://localhost:3306/bankdb";
	private String user = "root";
	private String pass = "";
	
	//admin sign in
	public boolean SignIn(String Username, String Password){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = (Statement) conn.createStatement();
			//if the user enters an input
			if (Username != null && Password != null) {
			//query the database with login credentials
			String loginQuery = "Select * from admin where AdminUsername = '"+Username+"' and AdminPassword = '"+Password+"'";
			ResultSet rs = st.executeQuery(loginQuery);
			
				if (rs.next()){
					return true;//if the login credentials are a match
					}
			} 
			conn.close();
			
		} catch (SQLException e) {
			System.err.println("Error with account sign in. Server is currently offline.");
		}
		System.out.println("Please Try Again.");
		return false;
		
	}

	//create a new account in the database
	public void updateDB(String anumber, double balance, String SCode, double interestR,  String Pin, int OverD, int CustomerID, int atype){
		try{
			
			Connection conn = null;
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = (Statement) conn.createStatement();
			//insert data into the database
			st.executeUpdate("Insert Into tblaccount(AccountNum, Balance, SortCode, InterestRate, Pin, Overdraft, CustomerID, AccountTypeID)  Values('"+anumber+"', '"+balance+"', '"+SCode+"', '"+interestR+"', '"+Pin+"','"+OverD+"', '"+CustomerID+"', '"+atype+"')");
			conn.close();
			
		}catch(Exception b){	//error if account number already exists or the customerid is invalid
			System.err.println("Sorry, this Account was not written to the database. Please enter a unique Account Number.");
			System.out.println("");
			System.err.println("Alternatively, please ensure that you have entered a valid CustomerID.");
		}
		
		
		
	}
	//create a new customer
	public void setCustomer(String firstName, String lastName, String Title){
		try{
			
			Connection conn = null;
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = (Statement) conn.createStatement();
			//write new customer to the database
			st.executeUpdate("Insert Into tblcustomer(firstName, lastName, Title)  Values('"+firstName+"', '"+lastName+"', '"+Title+"')");
			ResultSet rs = st.executeQuery("SELECT * FROM tblcustomer ORDER BY CustomerID DESC LIMIT 1");
		while (rs.next()){//fetch the customers ID number and display it
			int id = rs.getInt("CustomerID");
			System.out.format("Your CustomerID number is: " + "%s\n", id);
		}
			conn.close();
			
			
		}catch(Exception b){
			System.err.println("Error creating customer. Please try again.");
		}
	}
	//delete an existing account in the database
	public void delAccount(String AccNum){
		
	try{
			
			Connection conn = null;
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = (Statement) conn.createStatement();
			//delete the row with the user input Account Number from the database
			st.executeUpdate("delete from tblaccount where AccountNum = "+AccNum+"");
			conn.close();
			
			
		}catch(Exception b){
			System.err.println("Error deleting Account.");
		}
	
		
	}
	//edit data in the database
	public void editPin(String PinCode, String AccNumber){
		try{
			
			Connection conn = null;
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = (Statement) conn.createStatement();
			//set the newly created pin in the database
			st.executeUpdate("update tblaccount set Pin = "+PinCode+" where AccountNum = "+AccNumber+"");
			conn.close();
			
		}catch(Exception b){
			System.err.println("Error editing Pin.");
		}
		
	}
	//edit data in the database
	public void editIntRate(double IntRate, String AccNumber){
		try{
			
			Connection conn = null;
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = (Statement) conn.createStatement();
			//update the interest rate
			st.executeUpdate("update tblaccount set InterestRate = '"+IntRate+"' where AccountNum = "+AccNumber+"");
			conn.close();
			
		}catch(Exception b){
			System.err.println("Error editing Interest Rate.");
		}
	
		
	}
	//edit data in the database
	public void editBalance(double Balance, String AccNumber){
		try{
			
			Connection conn = null;
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = (Statement) conn.createStatement();
			//update the value of balance
			st.executeUpdate("update tblaccount set Balance = '"+Balance+"' where AccountNum = "+AccNumber+"");
			conn.close();
			
		}catch(Exception b){
			System.err.println("Error editing Balance.");
		}
	
	}
	//edit data in the database
	public void editOverdraft(int overd, String AccNumber){
		try{
			
			Connection conn = null;
			conn = DriverManager.getConnection(url, user, pass);
			Statement st = (Statement) conn.createStatement();
			//update overdraft value
			st.executeUpdate("update tblaccount set Overdraft = '"+overd+"' where AccountNum = "+AccNumber+"");
			conn.close();
			
		}catch(Exception b){
			System.err.println("Error editing Overdraft.");
		}
	
	}

}
