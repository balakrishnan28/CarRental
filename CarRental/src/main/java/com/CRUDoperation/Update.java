package com.CRUDoperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.User.Guest;
import com.Validation.InputValidation;
import com.main.LIstOfMenus;
import com.main.Layout;
import com.main.dbConnection;

public class Update {
	public static void showMenu() {
		try {
			final String ALL = "SELECT * FROM CAR.CARS ORDER BY CARID";
			
			Connection con = dbConnection.doDBConnection();
		    PreparedStatement stat = con.prepareStatement(ALL);
		    ResultSet result = stat.executeQuery();
		    Layout.carLayout();
		    while (result.next()) {
		        int carID = result.getInt("CarID");
		        String brand = result.getString("Brand");
		        String model = result.getString("Model");
		        String regNumber = result.getString("RegNumber");
		        int seatCount = result.getInt("SeatCount");
		        int mileage = result.getInt("Mileage");
		        int parkingID = result.getInt("ParkingID");
		        int year = result.getInt("year");
		        double rentalRate = result.getDouble("RentalRate");
		        String availability = result.getString("Availability");

		        System.out.println(carID + "\t" + brand + "\t" + model + "\t" + regNumber + "\t     " + seatCount
		                + "\t         " + mileage + "\t             " + parkingID + "\t        " + year + "\t  "
		                + rentalRate + "\t    " + availability);
		    }
		   
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void doUpdate() {
		try {
			
			Scanner sc=new Scanner(System.in);
			 
			final String FETCH_CAR_DETAILS_QUERY = "SELECT * FROM CAR.Cars WHERE CarID = ? ";
			String All = "SELECT * FROM CAR.CARS ORDER BY CARID";
			Connection con = dbConnection.doDBConnection();
			PreparedStatement Statement = con.prepareStatement(All);
			ResultSet Result = Statement.executeQuery();
			 Layout.carLayout();
			while (Result.next()) {
			     // Assuming this method displays the car details layout

			    int carsID = Result.getInt("CarID");
			    String brand = Result.getString("Brand");
			    String model = Result.getString("Model");
			    String regNumber = Result.getString("RegNumber");
			    int seatCount = Result.getInt("SeatCount");
			    int mileage = Result.getInt("Mileage");
			    int year = Result.getInt("Year");
			    int parkingID = Result.getInt("ParkingID");
			    double rentalRate = Result.getDouble("RentalRate");
			    String availability = Result.getString("Availability");

			    // Display existing car details
			    System.out.println(carsID + "\t" + brand + "\t" + model + "\t" + regNumber + "\t     " + seatCount
			            + "\t         " + mileage + "\t             " + parkingID + "\t        " + year + "\t  "
			            + rentalRate + "\t    " + availability);
			}
			Layout.tableBottomLayout();
			System.out.print("Enter CarID To Update : ");
			int carID= InputValidation.checkForInteger(sc);
			PreparedStatement fetchStatement = con.prepareStatement(FETCH_CAR_DETAILS_QUERY);
			fetchStatement.setInt(1, carID);
			ResultSet carResult = fetchStatement.executeQuery();

			if (carResult.next()) {
			    Layout.carLayout();  // Assuming this method displays the car details layout

			    int carsID = carResult.getInt("CarID");
			    String brand = carResult.getString("Brand");
			    String model = carResult.getString("Model");
			    String regNumber = carResult.getString("RegNumber");
			    int seatCount = carResult.getInt("SeatCount");
			    int mileage = carResult.getInt("Mileage");
			    int year = carResult.getInt("Year");
			    int parkingID = carResult.getInt("ParkingID");
			    double rentalRate = carResult.getDouble("RentalRate");
			    String availability = carResult.getString("Availability");

			    // Display existing car details
			    System.out.println(carsID + "\t" + brand + "\t" + model + "\t" + regNumber + "\t     " + seatCount
			            + "\t         " + mileage + "\t             " + parkingID + "\t        " + year + "\t  "
			            + rentalRate + "\t    " + availability);
			    Layout.tableBottomLayout();
			    
			    final String UPDATE_CAR_DETAILS_QUERY = "UPDATE CAR.Cars SET SeatCount = ?, Mileage = ?, ParkingID = ?, RentalRate = ?, Availability = ? WHERE CarID = ?";
			    PreparedStatement updateStatement = con.prepareStatement(UPDATE_CAR_DETAILS_QUERY);

			    System.out.println("");

			    
			    
			    System.out.print("Enter new SeatCount OR (Enter '0' to keep existing): ");
			    int newSeatCount =  InputValidation.checkForInteger(sc); 
			    if (newSeatCount > 0) {
			        updateStatement.setInt(1, newSeatCount);
			    }
			    else {
			    	updateStatement.setInt(1, seatCount);
			    }

			    System.out.print("Enter new Mileage OR (Enter '0' to keep existing): ");
			    int newMileage =  InputValidation.checkForInteger(sc); 
			    if (newMileage > 0) {
			        updateStatement.setInt(2, newMileage);
			    }
			    else {
			    	updateStatement.setInt(2, mileage);
			    }

			    
			    System.out.print("Enter new ParkingID OR (Enter '0' to keep existing): ");
			    int newParkingID =  InputValidation.checkForInteger(sc); 
			    if (newParkingID > 0) {
			        updateStatement.setInt(3, newParkingID);
			    }
			    else {
			    	updateStatement.setInt(3, parkingID);
			    }
			    
			    
			    System.out.print("Enter new RentalRate OR (Enter '0' to keep existing): ");
			    double newRentalRate =  InputValidation.checkForDouble(sc); 
			    if (newRentalRate > 0) {
			        updateStatement.setDouble(4, newRentalRate);
			    }
			    else {
			    	updateStatement.setDouble(4, rentalRate);
			    }
			    
			    System.out.print("Enter new Availability OR (Enter 'skip' to keep existing): ");
			    String NewAvailability =  sc.next();
			    if (!NewAvailability.equals("skip")) {
			        updateStatement.setString(5, availability);
			    }
			    else {
			    	updateStatement.setString(5, NewAvailability);
			    }
			    
			    updateStatement.setInt(6, carID);
			    int updateResult = updateStatement.executeUpdate();

			    if (updateResult > 0) {
			        System.out.println("Car details updated successfully...");
			    } else {
			        System.out.println("Failed to update car details...");
			    }

			    // Close the statements and connection
			    updateStatement.close();
			} else {
			    System.out.println("Car with ID " + carID + " not found.");
			}

			System.out.println("Enter 1 To Go Previous");
            int choice =  InputValidation.checkForInteger(sc); 
            if(choice==1) {
            	LIstOfMenus.showMenu2();
            }
            else {
            	System.out.println("Invalid Choice");
            }

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void updateCustomerDetails() {
	    try {
	    	Connection con = dbConnection.doDBConnection();
	        Scanner sc = new Scanner(System.in);
	        System.out.print("Enter Password To View / Update: ");
	        String password = sc.next();
	        final String FETCH_CUSTOMER_DETAILS_QUERY = "SELECT customerid AS customerID FROM CAR.customers WHERE accountid = ( SELECT accountid FROM CAR.accounts WHERE passwords = ?)";
	        
	        PreparedStatement fetchStatement = con.prepareStatement(FETCH_CUSTOMER_DETAILS_QUERY);
	        fetchStatement.setString(1, password);
	        ResultSet customerResult = fetchStatement.executeQuery();
	        int customerID = 0;
	        if (customerResult.next()) {
	        	customerID = customerResult.getInt("customerID");
	        }
	        customerResult.close();
	        fetchStatement.close();
	        
	        final String FETCH_CUS_DETAILS_QUERY = "SELECT * FROM CAR.Customers WHERE CustomerID = ?";
	        PreparedStatement showStatement = con.prepareStatement(FETCH_CUS_DETAILS_QUERY);
	        showStatement.setInt(1, customerID);
	        ResultSet showResult = showStatement.executeQuery();
	        
	        if (showResult.next()) {
	            Layout.customerLayout();  // Assuming this method displays the customer details layout

	            String firstName = showResult.getString("FirstName");
	            String lastName = showResult.getString("LastName");
	            String phoneNumber = showResult.getString("PhoneNumber");
	            String mailAddress = showResult.getString("MailAddress");
	            String drivingLicense = showResult.getString("DrivingLicense");
	            String Gender = showResult.getString("Gender");
	            int Age = showResult.getInt("Age");

	            // Display existing customer details
	            System.out.println(
	                customerID + "\t          " + firstName + "\t       " + lastName + "\t        " +
	                phoneNumber + "\t       " + mailAddress + "\t       " + drivingLicense+"\t   "+Gender+"\t         "+Age);
	            Layout.customerBottomLayout();
	            System.out.println();
	            System.out.println("Wish to update profile :");
	            System.out.println("1. yes");
	            System.out.println("2. Previous");
	            System.out.println();
	            System.out.print("Enter Yout choice : ");
	            int choice =  InputValidation.checkForInteger(sc); 
	            switch(choice) {
	            	case 1:
	            		final String UPDATE_CUSTOMER_DETAILS_QUERY ="UPDATE CAR.Customers SET FirstName = ?, LastName = ?, PhoneNumber = ?, MailAddress = ?, DrivingLicense = ? WHERE CustomerID = ?";
	    	            PreparedStatement updateStatement = con.prepareStatement(UPDATE_CUSTOMER_DETAILS_QUERY);

	    	            System.out.println("");

	    	            System.out.print("Enter new First Name OR (Enter 'skip' to keep existing): ");
	    	            String newFirstName = sc.next();
	    	            if (!newFirstName.equalsIgnoreCase("skip")) {
	    	                updateStatement.setString(1, newFirstName);
	    	            } else {
	    	                updateStatement.setString(1, firstName);
	    	            }

	    	            System.out.print("Enter new Last Name OR (Enter 'skip' to keep existing): ");
	    	            String newLastName = sc.next();
	    	            if (!newLastName.equalsIgnoreCase("skip")) {
	    	                updateStatement.setString(2, newLastName);
	    	            } else {
	    	                updateStatement.setString(2, lastName);
	    	            }

	    	            System.out.print("Enter new Phone Number OR (Enter 'skip' to keep existing): ");
	    	            String newPhoneNumber = sc.next();
	    	            if (!newPhoneNumber.equalsIgnoreCase("skip")) {
	    	                updateStatement.setString(3, newPhoneNumber);
	    	            } else {
	    	                updateStatement.setString(3, phoneNumber);
	    	            }

	    	            System.out.print("Enter new Mail Address OR (Enter 'skip' to keep existing): ");
	    	            String newMailAddress = sc.next();
	    	            if (!newMailAddress.equalsIgnoreCase("skip")) {
	    	                updateStatement.setString(4, newMailAddress);
	    	            } else {
	    	                updateStatement.setString(4, mailAddress);
	    	            }

	    	            System.out.print("Enter new Driving License OR (Enter 'skip' to keep existing): ");
	    	            String newDrivingLicense = sc.next();
	    	            if (!newDrivingLicense.equalsIgnoreCase("skip")) {
	    	                updateStatement.setString(5, newDrivingLicense);
	    	            } else {
	    	                updateStatement.setString(5, drivingLicense);
	    	            }

	    	            updateStatement.setInt(6, customerID);
	    	            int updateResult = updateStatement.executeUpdate();

	    	            if (updateResult > 0) {
	    	                System.out.println("Customer details updated successfully...");
	    	            } else {
	    	                System.out.println("Failed to update customer details...");
	    	            }
	    	            updateStatement.close();
	    	            break;
	            	case 2:
	            		LIstOfMenus.showMenu3();
	            		break;
	            	default:
	            		System.out.println("invalid choice");
	            		break;
	            		
	            	
	            }
	            
	        } else {
	            System.out.println("Customer with ID " + customerID + " not found.");
	        }
	        System.out.println("Enter 1 To Go Previous");
            int choice =  InputValidation.checkForInteger(sc); 
            if(choice==1) {
            	LIstOfMenus.showMenu3();
            }
            else {
            	System.out.println("Invalid Choice");
            }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}

}
