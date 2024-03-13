package com.CRUDoperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.User.Customer;
import com.Validation.InputValidation;
import com.Validation.Login;
import com.car.car;
import com.main.LIstOfMenus;
import com.main.Layout;
import com.main.dbConnection;

public class add {
public static void addCustomer() {
        
        Scanner sc = new Scanner(System.in);
		System.out.println("----------------------------------------------------");
		System.out.println("                   Registration Form                ");
		System.out.println("----------------------------------------------------");
        System.out.print("Enter the new username (Aaaa1234) :");
        String username = sc.next();
        while(InputValidation.notValidUsername(username)) {
        	 System.out.print("Re-enter: ");
        	username = sc.next();
        }
        
        System.out.print("Enter the new password (Aaaa1234) :");
        String password = sc.next();
        while(InputValidation.notValidPassword(password)) {
        	 System.out.print("Re-enter: ");
        	password = sc.next();
        	
        }
        System.out.println("Enter first name:");
        String firstName = sc.next();
        while(InputValidation.notValidName(firstName)) {
        	 System.out.print("Re-enter: ");
        	firstName = sc.next();
        }
        System.out.println("Enter last name:");
        String lastName = sc.next();
        while(InputValidation.notValidName(firstName)) {
        	 System.out.print("Re-enter: ");
        	firstName = sc.next();
        }
        System.out.println("Enter phone number:");
        String phoneNumber = sc.next();
        while(InputValidation.notValidatePhoneNumber(phoneNumber)) {
        	 System.out.print("Re-enter: ");
        	phoneNumber=sc.next();
        }
        System.out.println("Enter mail id:");
        String mailId = sc.next();
        while(InputValidation.notValidEmail(mailId)) {
        	 System.out.print("Re-enter: ");
        	mailId = sc.next();
        	
        }
        
        System.out.println("Enter Driving Licence :");
        String drivingLicense = sc.next();
        while (InputValidation.notValidDrivingLicense(drivingLicense)) {
        
            System.out.print("Re-enter: ");
            drivingLicense = sc.next();
        }
        
        System.out.println("Choose your Gender : ");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Trans");
        System.out.print("Enter your Choice : ");
        int choice = sc.nextInt();
        String gender;

        switch (choice) {
            case 1:
                gender = "Male";
                break;
            case 2:
                gender = "Female";
                break;
            case 3:
                gender = "Trans";
                break;
            default:
                System.out.println("Invalid choice. Defaulting to 'Other'.");
                gender = "Other";
        }
        System.out.println("Enter your age: ");
        int age = sc.nextInt();
        while (age < 18) {
            System.out.println("Invalid age. Minimum age required is 18.");
            System.out.print("Re-enter your age: ");
            age = sc.nextInt();
        }
		System.out.println("----------------------------------------------------");
       
        String role = "customer";
        final String AccountIDQuery = "SELECT MAX(AccountID) AS MAXID FROM CAR.Accounts";
        final String CustomerIDQuery = "SELECT MAX(CUSTOMERID) AS MAXID FROM CAR.Customers";
        final String QUERY1 = "INSERT INTO CAR.Accounts (AccountID, USERNAME, PASSWORDS, ROLES , STATUS) VALUES (?, ?, ?, 'customer', 'active')";
        final String QUERY2 = "INSERT INTO CAR.Customers (CUSTOMERID, ACCOUNTID, FIRSTNAME, LASTNAME, PHONENUMBER, MAILADDRESS, DRIVINGLICENSE, GENDER, AGE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        if (Login.patternValidate(username, password)) {
            try {
                Connection con = dbConnection.doDBConnection();

                // Get the maximum AccountID
                PreparedStatement accIdStatement = con.prepareStatement(AccountIDQuery);
                ResultSet accIdResult = accIdStatement.executeQuery();
                int maxAccId = 0;
                if (accIdResult.next()) {
                    maxAccId = accIdResult.getInt("MAXID");
                }
                accIdResult.close();
                accIdStatement.close();

                // Get the maximum CustomerID
                PreparedStatement cusIdStatement = con.prepareStatement(CustomerIDQuery);
                ResultSet cusIdResult = cusIdStatement.executeQuery();
                int maxCusId = 0;
                if (cusIdResult.next()) {
                    maxCusId = cusIdResult.getInt("MAXID");
                }
                cusIdResult.close();
                cusIdStatement.close();
                Customer cust = new Customer(maxCusId+1, role, username, password, firstName, lastName, phoneNumber,mailId, drivingLicense, gender, age);
                // Insert into Accounts table
                PreparedStatement preparedStatement1 = con.prepareStatement(QUERY1);
                preparedStatement1.setInt(1, maxAccId + 1);
                preparedStatement1.setString(2, cust.getUsername());
                preparedStatement1.setString(3, cust.getPassword());

                // Insert into Customers table
                PreparedStatement preparedStatement2 = con.prepareStatement(QUERY2);
                preparedStatement2.setInt(1, maxCusId + 1);
                preparedStatement2.setInt(2, maxAccId + 1);
                preparedStatement2.setString(3, cust.getFirstName());
                preparedStatement2.setString(4, cust.getLastName());
                preparedStatement2.setString(5, cust.getPhoneNumber());
                preparedStatement2.setString(6, cust.getMailAddress());
                preparedStatement2.setString(7, cust.getDrivingLicense());
                preparedStatement2.setString(8, cust.getGender());
                preparedStatement2.setInt(9, cust.getAge());

                // Execute the updates
                int result1 = preparedStatement1.executeUpdate();
                int result2 = preparedStatement2.executeUpdate();

                if (result1 > 0 && result2 > 0) {
                    System.out.println("Added successfully...");
                } else {
                    System.out.println("Not Added...");
                }
                

                // Close the statements and connection
                preparedStatement1.close();
                preparedStatement2.close();
                con.close();
                
                System.out.println("Enter 1 To Go Previous");
                int choice1 = sc.nextInt();
                if(choice1==1) {
                	LIstOfMenus.showDashboard();
                }
                else {
                	System.out.println("Invalid Choice");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

public static void addCar() {
	
    Scanner sc = new Scanner(System.in);
	System.out.println("-------------------------------------");
	System.out.println("    Registration Form for New Car    ");
	System.out.println("-------------------------------------");
    System.out.print("Enter brand:");
    String brand = sc.next();
    while(InputValidation.notValidName(brand)) {
    	System.out.print("Re-Enter : ");
    	brand=sc.next();
    }

    System.out.print("Enter model:");
    String model = sc.next();
    

    System.out.print("Enter registration number:");
    String regNumber = sc.next();
    while(InputValidation.notValidRegistration(regNumber)) {
    	System.out.print("Re-Enter : ");
    	regNumber=sc.next();
    }

    System.out.print("Enter seat count:");   
    int seatCount = InputValidation.checkForInteger(sc); 

    System.out.print("Enter mileage:");
    int mileage = InputValidation.checkForInteger(sc); 

    System.out.print("Enter parking ID:");
    int parkingId = InputValidation.checkForInteger(sc); 
    
    System.out.print("Enter year :");
    int year = InputValidation.checkForInteger(sc); 

    System.out.print("Enter rental rate:");
    double rentalRate = InputValidation.checkForDouble(sc); 

    
    String availability = "yes";
    
    car car1 =new car(brand, model, regNumber,  seatCount,  mileage,  parkingId,year,  rentalRate,  availability);

    // Assuming you have a Cars table in your database
    final String INSERT_QUERY = "INSERT INTO CAR.Cars (CarID,Brand, Model, RegNumber, SeatCount, Mileage, ParkingID, year, RentalRate, Availability) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        Connection con = dbConnection.doDBConnection();
        final String CAR_QUERY = "SELECT MAX(CARID) AS MAXID FROM CAR.CARS";
        PreparedStatement preparedStatement = con.prepareStatement(INSERT_QUERY);            
        PreparedStatement accIdStatement = con.prepareStatement(CAR_QUERY);
        ResultSet accIdResult = accIdStatement.executeQuery();
        int maxCarId = 0;
        if (accIdResult.next()) {
            maxCarId = accIdResult.getInt("MAXID");
        }
        accIdResult.close();
        accIdStatement.close();
        
        preparedStatement.setInt(1, maxCarId+1);
        preparedStatement.setString(2, car1.getBrand());
        preparedStatement.setString(3, car1.getModel());
        preparedStatement.setString(4, car1.getRegNumber());
        preparedStatement.setInt(5, car1.getSeatCount());
        preparedStatement.setInt(6, car1.getMileage());
        preparedStatement.setInt(7, car1.getParkingId());
        preparedStatement.setInt(8, car1.getYear());
        preparedStatement.setDouble(9, car1.getRentalRate());
        preparedStatement.setString(10, car1.getAvailability());

        int result = preparedStatement.executeUpdate();
        System.out.println("-------------------------------------");
        if (result > 0) {
            System.out.println("Car added successfully...");
        } else {
            System.out.println("Failed to add car...");
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
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
}

