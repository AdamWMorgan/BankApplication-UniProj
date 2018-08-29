package BankProject;

import java.util.*;

public class BankEnvironment {

	public static void main(String[] args) throws Exception{	
			  
		BankDB.BankData();

		Scanner in = new Scanner(System.in);
		Scanner text = new Scanner(System.in);
		text.useDelimiter("\\n"); // reads whitespace
		
		//import java classes
		Bank account = new Bank();
		Customer customer = new Customer();
		withdrawal with = new withdrawal();
		deposit dep = new deposit();
		signIn signName = new signIn();
		currentAccount CurA = new currentAccount();
		balance bal = new balance();
		
		
		int EnterOpt = 0;
		int CustOpt = 0;
		int BankOpt = 0;
		
		//entry 
			do{
					System.out.println("Please choose an option.");
					System.out.println("1.Customer");
					System.out.println("2.Bank");
					try{
					EnterOpt = in.nextInt();
					}catch (Exception a){
						System.out.println("Wrong input, please try again.");
						in.next();
						}
			}while( EnterOpt != 1 && EnterOpt != 2); //ensures that the values are correct
			
		//if the value is 1 then the customer interface appears
		if (EnterOpt == 1){
			String anum = "";
			String pcode = "";
		//sign in to unique account
			do{
				System.out.println("Please Enter your Account Number...");
				anum = in.next();
				System.out.println("Please Enter your Pin Code...");
				pcode = in.next();
				customer.SignIn(anum, pcode);
					}while(customer.SignIn(anum, pcode) != true);
			//print welcome message
			signName.signInName(anum);//values sent to corresponding method in another class
			
			do{ //customer options
			System.out.println();
			System.out.println("Please choose from the following options...");
			System.out.println();
			System.out.println("1. Check Balance");
			System.out.println("2. Withdraw Money");
			System.out.println("3. Deposit Money");
			System.out.println("4. Exit System");
			
			//ensures that the customer enters the correct input
				try{
				CustOpt = in.nextInt();
				} catch (Exception b){
				System.out.println("Please enter an Integer.");
				in.next();
				}
				//switch that controls the customer interface
			switch(CustOpt){
			
			case 1: //check account balance
			
				bal.Balance(anum);
			
				break;
				
			case 2: //withdraw money from account
				double withdrawl = 0;
				try{
				System.out.println("How much do you wish too withdraw?");
				withdrawl = in.nextDouble();
				}catch(Exception b){
					System.err.println("Sorry, you must enter a number value.");
					in.next();
				}
				with.Withdraw(anum, withdrawl);
				CurA.Withdraw(anum, withdrawl);
				
				break;
			
			case 3: //make deposit
				double deposit = 0;
				try{
				System.out.println("How Much do you wish to deposit: £");
				deposit = in.nextDouble();
				} catch(Exception b){
					System.err.println("Sorry, you must enter a number value.");
					in.next();
					
				}
				dep.Deposit(anum, deposit);
				break;
			
			case 4: //quit program 
				System.out.println("Thank you for using AdBank");
				System.exit(0);
				
				break;
					}
			
				}while(CustOpt < 4);
			
			}
		//if the value entered is 2 the bank interface appears
		else if (EnterOpt == 2){
			String Username = " ";
			String Password = " ";
		//sign in to ensure that it is a bank worker 
			do{
		System.out.println("Please Enter your User ID...");
		Username = in.next();
		System.out.println("Please Enter your Password...");
		Password = in.next();
		account.SignIn(Username, Password);//values sent to corresponding method in another class
			}while(account.SignIn(Username, Password) != true);
		
		System.out.println("Access Granted");
			
			do{//interface options
			System.out.println();
			System.out.println("1. Create Customer");
			System.out.println("2. Create Account");
			System.out.println("3. Edit Account");
			System.out.println("4. Delete Account");
			System.out.println("5. Exit System");
			
				try{//ensures that the correct value is entered
				BankOpt = in.nextInt();
				} catch (Exception c){
				System.out.println("Please enter an Integer!");
				in.next();
				}
				
			switch(BankOpt){
			case 1://create a new customer
				System.out.println("Customer Details:");
				System.out.println("Title...");
				String Title = "";
				Title = (text.next());
				System.out.println("First Name...");
				String firstName = "";
				firstName = (text.next());
				System.out.println("Last Name...");
				String lastName = "";
				lastName = (text.next());
				account.setCustomer(firstName, lastName, Title);	//values sent to corresponding method in another class
				break;
			case 2: //create a new bank account by using a customerid from the previous selection or an already saved customerid in the database
				
				int atype = 0;
				int OverD = 0;
				int CustomerID = 0;
				double balance = 0;
				double interestR = 0;
				String anumber = " ";	
				String SCode = "12-34-56";// set automatically to a specific sort code
				String Pin = "";
				
				
				System.out.println("Create new account:");	
				System.out.println("Customer ID...");
				//enter customerid to connect the customer with the account
				try{
				CustomerID = (in.nextInt());
				}catch(Exception b){
					System.err.println("Wrong input. This account will not be linked to a customer until the correct ID is etered through the edit menu.");
					in.next();
				}
				
				do{//set account type
				System.out.println("Set Account Type:");
				System.out.println("1. Current Account");
				System.out.println("2. Savings Account");
						
				try{//ensures the correct value is entered
					atype = in.nextInt();
					} catch (Exception c){
					System.out.println("Please enter an Integer!");
					in.next();
					}
				}while(atype != 1 && atype != 2);
				
				do{	//set the account number
				System.out.println("Set 8 digit Account Number...");		
				anumber = (text.next());
			}while(anumber.length() != 9);//ensures that only an 8 digit number is accepted
			
			do{	//set 4 digit account pin code
				System.out.println("Set 4 digit Account Pin Code...");
				Pin = (text.next());
			}while (Pin.length() != 5);//ensures that the value has a length of 4
				
				if (atype == 1) // only set an overdraft an a current account
				{
					do{
					try{//set account overdraft
					System.out.println("Set Overdraft Limit (Must be less or equal to 0)...");
					OverD = (in.nextInt());
					}catch(Exception z){//if input is not an integer the overdraft will be stored as 0
						System.out.println("Sorry, your input was incorrect. Overdraft will now be set to 0. Go to 3. Edit Account to change this.");
						in.next();
					}
				}while(OverD > 0);
					
				}
			try{
				do{	//set account balance
				System.out.println("Set Starting Account Balance (Must be equal to, or more than 0)...");
				balance = (in.nextDouble());
				}while(balance < 0);
			} catch(Exception z){//if input is not an integer the balance will equal 0
					System.out.println("Sorry, your input was incorrect. Balance will now be set to 0. Go to 3. Edit Account to change this.");
					in.next();
				}
				
				try{//set account interest
				System.out.println("Set Interest Rate...");
				interestR = (in.nextDouble());
				}catch(Exception z){//if input is not an integer it will be stored as 0
					System.out.println("Sorry, your input was incorrect. Interest Rate will now be set to 0. Go to 3. Edit Account to change this.");
					in.next();
				}
				
				account.updateDB(anumber, balance, SCode, interestR, Pin, OverD, CustomerID, atype);
					
				break;
				
			case 3: //edit a customers bank account
				
				System.out.println("Choose an option to edit...");
				System.out.println("1. Pin Code");
				System.out.println("2. Interest Rate");
				System.out.println("3. Balance");
				System.out.println("4. Overdraft");
				System.out.println("5. Back");
				
				int EditOpt = 0;
	
				try{//ensures input is of type integer
				EditOpt = in.nextInt();
				}catch(Exception f){
					System.out.println("Wrong Input! Please try again...");
					in.next();
				}
						
				if (EditOpt == 1){//edit pin code
				System.out.println("Please enter the account number of the account you wish to edit...");
				String AccNumber = " ";
				String PinCode = "";
				AccNumber = (text.next());
				do{
				System.out.println("Change 4 digit Pin Code to...");
				PinCode = (text.next());
				}while (PinCode.length() != 5);
				account.editPin(PinCode, AccNumber);//values sent to corresponding method in another class
				}
				if (EditOpt == 2){//edit interest rate
					System.out.println("Please enter the account number of the account you wish to edit...");
					String AccNumber = " ";
					double IntRate = 0;
					AccNumber = (text.next());
					try{
					System.out.println("Change Interest rate to...");
					IntRate = (in.nextDouble());}
						catch (Exception z){
						System.out.println("Sorry, you must enter a number value.");//ensures input is 0f type double
						in.next();
					}
					account.editIntRate(IntRate, AccNumber);//values sent to corresponding method in another class
					}
				if (EditOpt == 3){//edit balance
					System.out.println("Please enter the account number of the account you wish to edit...");
					String AccNumber = " ";
					double Balance = 0;
					AccNumber = (text.next());
					try{
					System.out.println("Change Balance to...");
					Balance = (in.nextDouble());}
					catch (Exception z){
						System.out.println("Sorry, you must enter a number value.");//ensures input is of type double
						in.next();
					}
					account.editBalance(Balance, AccNumber);//values sent to corresponding method in another class
					}
				if (EditOpt == 4){//edit overdraft
					System.out.println("Please enter the account number of the account you wish to edit...");
					String AccNumber = " ";
					int overd = 0;
					AccNumber = (text.next());
					
					try{
					System.out.println("Change Overdraft to...");
					overd = (in.nextInt());}
					catch (Exception z){
						System.out.println("Sorry, you must enter a number value.");//ensures input is of type integer
						in.next();
					}
			
					account.editOverdraft(overd, AccNumber);//values sent to corresponding method in another class
					}
				
				break;
						
			case 4: //Delete account
				String AccNum = " ";
				try{
				System.out.println("Account Number...");		
				AccNum = (text.next());
				}catch(Exception s){
					System.out.println("Sorry your input was incorrect, please try again.");//ensures that input is of type string
					in.next();
				}
				
				account.delAccount(AccNum);//values sent to corresponding method in another class
				
				break;
			
			case 5: //quit program 
				
				System.exit(0);//terminate program
						
				break;
			}		
				}while(BankOpt < 5);		
		}
		in.close();
		text.close();		
	}
}
