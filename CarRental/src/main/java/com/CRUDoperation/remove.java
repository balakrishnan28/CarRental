package com.CRUDoperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.User.Guest;
import com.Validation.InputValidation;
import com.main.LIstOfMenus;
import com.main.Layout;
import com.main.dbConnection;

public class remove {
	

	public static void removeCustomer() {
		
	    Scanner sc = new Scanner(System.in);
	    
	    final String DELETE_ACCOUNT_QUERY = "UPDATE CAR.Accounts SET status = 'inactive' WHERE AccountID = ?";
	    final String allActiveAccounts = "SELECT * FROM CAR.ACCOUNTS WHERE STATUS = 'active' AND ROLES = 'customer' ORDER BY ACCOUNTID";
	    try {
	    	Connection con = dbConnection.doDBConnection();
	        PreparedStatement Activestat = con.prepareStatement(allActiveAccounts);
	        ResultSet result = Activestat.executeQuery();
	        
	        Layout.accountLayout();
	        while (result.next()) {
	        	int Accountid = result.getInt("AccountID");
	            String username = result.getString("username");
	            String password = result.getString("passwords");
	            String role = result.getString("roles");
	            String status = result.getString("status");
	            
	            System.out.println(Accountid + "\t" + username + "\t" + password + "\t" + role + "\t" + status);
	                   
	        }

	        Layout.bottomaccountLayout();
	        System.out.println("Enter the AccountID to  remove:");
		    int accountIdToRemove =  InputValidation.checkForInteger(sc); 
	       
	        PreparedStatement deleteAccountStatement = con.prepareStatement(DELETE_ACCOUNT_QUERY);
	        deleteAccountStatement.setInt(1, accountIdToRemove);
	        int result2 = deleteAccountStatement.executeUpdate();
	        
	        if (result2 > 0) {
	            System.out.println("Customer removed successfully...");
	        } else {
	            System.out.println("Failed to remove customer. CustomerID not found...");
	        }

	        deleteAccountStatement.close();
	        con.close();
	        System.out.println("Enter 1 To Go Previous");
	        int choice =  InputValidation.checkForInteger(sc); 
	        if(choice==1) {
	        	LIstOfMenus.showMenu2();
	        }
	        else {
	        	System.out.println("Invalid Choice");
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    }
	
	public static void removeCar(){
		Guest.showMenu();
	    Scanner sc = new Scanner(System.in);
	    
	    System.out.println("Enter the CarID of the car to be removed:");
	    int carIdToRemove =  InputValidation.checkForInteger(sc); 

	    // Assuming you have a Cars table in your database
	    final String DELETE_QUERY = "UPDATE CAR.Cars SET Availability = 'out of stock' WHERE CarID =  ?";

	    try {
	        Connection con = dbConnection.doDBConnection();
	        PreparedStatement preparedStatement = con.prepareStatement(DELETE_QUERY);	        
	        preparedStatement.setInt(1, carIdToRemove);
	        int result = preparedStatement.executeUpdate();

	        if (result > 0) {
	            System.out.println("Car removed successfully...");
	        } else {
	            System.out.println("Failed to remove car. CarID not found...");
	        }

	        preparedStatement.close();
	        con.close();
	        System.out.println("Enter 1 To Go Previous");
            int choice =  InputValidation.checkForInteger(sc); 
            if(choice==1) {
            	LIstOfMenus.showMenu2();
            }
            else {
            	System.out.println("Invalid Choice");
            }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}

}
