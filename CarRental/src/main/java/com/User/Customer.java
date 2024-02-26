package com.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.User.UserDetails;
import com.Validation.InputValidation;
import com.Validation.Login;
import com.main.LIstOfMenus;
import com.main.Layout;
import com.main.dbConnection;

public class Customer extends UserDetails {
	private String drivingLicense;
	public Customer(int id,String roles,String username, String password, String firstName, String lastName, String phoneNumber, String email, String drivingLicense, String Gender, int age) {
        super(id,roles,username, password, firstName, lastName, phoneNumber, email,Gender,age);
        this.drivingLicense = drivingLicense;
    }
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
    public String getDrivingLicense() {
        return drivingLicense;
    }
    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }
    
    public void activateCustomer() {
	    Scanner sc = new Scanner(System.in);
	    
	    final String ShowAllInActiveAccounts = "SELECT * FROM CAR.ACCOUNTS WHERE STATUS='inactive' ORDER BY ACCOUNTID";
	    final String ACTIVATE_ACCOUNT_QUERY = "UPDATE CAR.accounts SET status = 'active' WHERE accountid = ?";

	    try {
	        Connection con = dbConnection.doDBConnection();
	        PreparedStatement inActivestat = con.prepareStatement(ShowAllInActiveAccounts);
	        ResultSet result = inActivestat.executeQuery();

            Layout.accountLayout();
	        while (result.next()) {
                int Accountid = result.getInt("Accountid");
                String username = result.getString("username");
                String password = result.getString("passwords");
                String role = result.getString("roles");
                String status = result.getString("status");
                
                System.out.println(Accountid + "\t" + username + "\t" + password + "\t" + role + "\t" + status);
                       
            }

            Layout.bottomaccountLayout();
            System.out.println("Enter the AccountID to Activate");
    	    int customerIdToRemove = sc.nextInt();
	        PreparedStatement deleteAccountStatement = con.prepareStatement(ACTIVATE_ACCOUNT_QUERY);
	        deleteAccountStatement.setInt(1, customerIdToRemove);
	        int result2 = deleteAccountStatement.executeUpdate();
	        
	        if (result2 > 0) {
	            System.out.println("Account Activated successfully...");
	        } else {
	            System.out.println("Failed to Activate Account. accountID not found...");
	        }

	        deleteAccountStatement.close();
	        con.close();
	        System.out.println("Enter 1 To Go Previous");
            int choice = sc.nextInt();
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
    

}