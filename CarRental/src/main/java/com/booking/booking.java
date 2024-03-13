package com.booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.Validation.InputValidation;
import com.main.LIstOfMenus;
import com.main.Layout;
import com.main.dbConnection;

public class booking {
	public static void dobooking(int carID) {
		Scanner sc=new Scanner(System.in);
		
		try {
			final String QUERY = "SELECT * FROM CAR.CARS WHERE CARS.CARID = ? ORDER BY CARID";
			Connection con=dbConnection.doDBConnection();
			int result4=0;
			PreparedStatement stat = con.prepareStatement(QUERY);
			stat.setInt(1, carID);
            ResultSet result1 = stat.executeQuery();
            Layout.carLayout();
            while (result1.next()) {
                int carsID = result1.getInt("CarID");
                String brand = result1.getString("Brand");
                String model = result1.getString("Model");
                String regNumber = result1.getString("RegNumber");
                int seatCount = result1.getInt("SeatCount");
                int mileage = result1.getInt("Mileage");
                int year = result1.getInt("Year");
                int parkingID = result1.getInt("ParkingID");
                double rentalRate = result1.getDouble("RentalRate");
                boolean availability = result1.getBoolean("Availability");
                              
                System.out.println(carsID+"\t"+ brand+"\t"+ model+"\t"+regNumber+"\t     "+seatCount+"\t         "+mileage+"\t             "+parkingID+"\t        "+year+"\t  "+rentalRate+"\t    "+availability);
                Layout.tableBottomLayout();
                System.out.println("confirm booking (y/n) : ");
                String choice=sc.next().toLowerCase();
                if(choice.equals("y") || choice.equals("n")) {
                	if(choice.equals("y")) {
                		try {
                			Connection con1=dbConnection.doDBConnection();
                			
                			//change availability
                			final String QUERY1 = "UPDATE CAR.Cars SET Availability = 'booked' WHERE CarID = ?";                			
                			PreparedStatement stat1 = con1.prepareStatement(QUERY1);
                			stat1.setInt(1, carID);
                            stat1.executeQuery();
                			LocalDate booking = LocalDate.now(); 
                			String bookingDate = booking.toString();
                			System.out.print("Enter your password for booking : ");
                			String password = sc.next();
                			final String CUSTOMERID = "SELECT customerid AS customerID FROM CAR.customers WHERE accountid = ( SELECT accountid FROM CAR.accounts WHERE passwords = ?)";
                			
                            PreparedStatement custID = con.prepareStatement(CUSTOMERID);
                            custID.setString(1, password);
                            ResultSet IdResult = custID.executeQuery();
                            int customerID = 0;
                            if (IdResult.next()) {
                            	customerID = IdResult.getInt("customerID");
                            }
                            IdResult.close();
                            custID.close();
                			
                            int AdminId1=1;
                			final String BOOKING_INSERT = "INSERT INTO CAR.BOOKING (BOOKINGID, BOOKINGDATE, CUSTOMERID, ADMINID, CARID, DELIVERDATE, RETURNDATE, BOOKINGSTATUS) VALUES (?, TO_DATE(?,'YYYY-MM-DD'), ?, ?, ?, TO_DATE(?,'YYYY-MM-DD'), TO_DATE(?,'YYYY-MM-DD'), 'success')";                         
                            final String BOOKINGID = "SELECT MAX(BOOKINGID) AS MAXID FROM CAR.BOOKING";
                            
                             //for acc           
                            PreparedStatement accIdStatement = con.prepareStatement(BOOKINGID);
                            ResultSet accIdResult = accIdStatement.executeQuery();
                            int maxId = 0;
                            if (accIdResult.next()) {
                                maxId = accIdResult.getInt("MAXID");
                            }
                            accIdResult.close();
                            accIdStatement.close();
                            LocalDate DeliverDateAsDate=null;
                            boolean loop=true;
                            String DeliverDate=null;
                            System.out.print("Enter your expected delivery date (YYYY-MM-DD): ");
                            while(loop) {
                            	
                            	DeliverDate = sc.next();
                                while(InputValidation.notValidDate(DeliverDate)) {
                                	System.out.print("Re-Enter : ");
                                	DeliverDate = sc.next();
                                }
                               
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                DeliverDateAsDate = LocalDate.parse(DeliverDate, formatter);
                                 if(DeliverDateAsDate.isAfter(booking)) {
                                	 loop = false;
                                 }
                                 else {
                                	 System.out.println("Deliver date should be in future");
                                	 System.out.print("Re-Enter");
                                 }
                            }
                            System.out.print("Enter Number of Days need To Rent : ");
                            int days =  InputValidation.checkForInteger(sc); 
                            LocalDate ReturnDateAsDate = DeliverDateAsDate.plusDays(days);
                            String ReturnDate = ReturnDateAsDate.toString();
                            
                            //insert                           
                            PreparedStatement preparedStatement = con.prepareStatement(BOOKING_INSERT);
                            preparedStatement.setInt(1, maxId+1);
                            preparedStatement.setString(2,bookingDate);
                            preparedStatement.setInt(3, customerID);
                            preparedStatement.setInt(4, AdminId1);
                            preparedStatement.setInt(5, carID);                            
                            preparedStatement.setString(6, DeliverDate);
                            preparedStatement.setString(7, ReturnDate);
                            result4 = preparedStatement.executeUpdate();                            
                            
                            if(result4>0) {
                    			System.out.println("Reservation success ... ");
                    			Invoice.makeInvoice(customerID, carID, maxId+1,days,DeliverDate,ReturnDate);
                    		}
                            else {
                            	System.out.println("Reservation Cancelled ...");
                            }
                            
                            
                		}
                		catch(Exception e) {
                			System.out.println(e.getMessage());
                		}
                		
                	}
                	if(choice.equals("n")) {
                		System.out.println("booking cancelled ");
                	}
                }
                else {
                	System.out.println("Invalid choice please enter 'y' for yes or 'n' for no");
                }
            }
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
				
	}
	
	public static void showBooking(){
		
		try {
			Connection con = dbConnection.doDBConnection();
			Scanner sc=new Scanner(System.in);

			System.out.print("Enter your password for booking : ");
			String password = sc.next();
			
			final String CUSTOMERID = "SELECT customerid AS customerID FROM CAR.customers WHERE accountid = ( SELECT accountid FROM CAR.accounts WHERE passwords = ?)";
			
	        PreparedStatement custID = con.prepareStatement(CUSTOMERID);
	        custID.setString(1, password);
	        ResultSet IdResult = custID.executeQuery();
	        int customerID = 0;
	        if (IdResult.next()) {
	        	customerID = IdResult.getInt("customerID");
	        }
	        IdResult.close();
	        custID.close();
			
			final String showAll = "SELECT * FROM CAR.Booking WHERE CustomerID = ? ORDER BY BOOKINGDATE DESC";
			PreparedStatement showAllstat = con.prepareStatement(showAll);
			showAllstat.setInt(1, customerID);
			ResultSet result = showAllstat.executeQuery();
            Layout.BookingLayout();
            while (result.next()) {
            	int bookingID = result.getInt("BookingID");
            	int carID = result.getInt("CarID");  // Assuming there's a CarID column in the Booking table
            	int adminID = result.getInt("AdminID");
            	String fullbookingDate = result.getString("BookingDate");
            	String fulldeliverDate = result.getString("DeliverDate");
            	String fullreturnDate = result.getString("ReturnDate");
            	String bookingStatus = result.getString("BookingStatus");
            	
            	//date without time
            	String BookingDate = fullbookingDate.substring(0, 10);
                String DeliverdDate = fulldeliverDate.substring(0, 10);
                String ReturnDate = fullreturnDate.substring(0, 10);

                System.out.println(bookingID + "\t         " + BookingDate + "\t    " + customerID + "\t          " + adminID + "\t         " + carID + "\t  " + DeliverdDate + "\t" + ReturnDate +"\t   "+ bookingStatus);
            }
            Layout.tableBottomLayout();
            System.out.println("Enter 1 To Go Previous");
            int choice =  InputValidation.checkForInteger(sc); 
            if(choice==1) {
            	LIstOfMenus.showMenu3();
            }
            else {
            	System.out.println("Invalid Choice");
            }
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
        
		
		
	}
	
public static void showAllBooking(){
		
		try {
			Connection con = dbConnection.doDBConnection();
			Scanner sc=new Scanner(System.in);

			final String showAll = "SELECT BOOKING.*, CONCAT(CONCAT(CUSTOMERS.FIRSTNAME, ' '), CUSTOMERS.LASTNAME) AS customerName FROM CAR.BOOKING LEFT JOIN CAR.CUSTOMERS ON BOOKING.CUSTOMERID = CUSTOMERS.CUSTOMERID";
			PreparedStatement showAllstat = con.prepareStatement(showAll);
			
			ResultSet result = showAllstat.executeQuery();
            Layout.BookingLayout();
            while (result.next()) {
            	int bookingID = result.getInt("BookingID");
            	String customerName = result.getString("customerName");
            	int carID = result.getInt("CarID");  // Assuming there's a CarID column in the Booking table
            	String fullbookingDate = result.getString("BookingDate");
            	String fulldeliverDate = result.getString("DeliverDate");
            	String fullreturnDate = result.getString("ReturnDate");
            	String bookingStatus = result.getString("BookingStatus");
            	
            	//date without time
            	String BookingDate = fullbookingDate.substring(0, 10);
                String DeliverdDate = fulldeliverDate.substring(0, 10);
                String ReturnDate = fullreturnDate.substring(0, 10);

                System.out.println(bookingID + "\t         " + BookingDate + "\t    " + customerName + "\t         " + carID + "\t  " + DeliverdDate + "\t" + ReturnDate +"\t   "+ bookingStatus);
            }
            Layout.tableBottomLayout();
            System.out.println();
            System.out.print("enter 1 to go menu:");
            int choice =  InputValidation.checkForInteger(sc); 
            if(choice == 1) {
            	LIstOfMenus.showMenu2();
            }
            else {
            	System.out.println("invalid responce forced to Main Page");
            	LIstOfMenus.showMenu2();
            }
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
        
		
		
	}
}
