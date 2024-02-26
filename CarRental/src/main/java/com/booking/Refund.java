package com.booking;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;

import com.Validation.InputValidation;
import com.main.LIstOfMenus;
import com.main.Layout;
import com.main.dbConnection;

public class Refund {
	public static void doRefund() {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter your Password : ");
		String password = sc.next();
		LocalDate Refund = LocalDate.now(); 
		String DeliverdDate = null;
		try {
			final String QUERY = "SELECT * FROM CAR.booking WHERE bookingstatus = 'success' AND customerid = (SELECT customerid FROM CAR.customers WHERE ACCOUNTID = (SELECT ACCOUNTID FROM CAR.ACCOUNTS WHERE passwords = ?)) ORDER BY CARID";
    			
    		Connection con = dbConnection.doDBConnection();
            PreparedStatement stat = con.prepareStatement(QUERY);
            stat.setString(1, password);
            ResultSet result = stat.executeQuery();
            Layout.BookingLayout();
            while (result.next()) {
                int BOOKINGID = result.getInt("BOOKINGID");
                String FULLBOOKINGDATE = result.getString("BOOKINGDATE");               
                int CUSTOMERID = result.getInt("CUSTOMERID");
                int ADMINID = result.getInt("ADMINID");
                int CARID = result.getInt("CARID");
                String FULLDELIVERDATE = result.getString("DELIVERDATE");
                String FULLRETURNDATE = result.getString("RETURNDATE");
                String BOOKINGSTATUS = result.getString("BOOKINGSTATUS");
                
                //show without time
                String BookingDate = FULLBOOKINGDATE.substring(0, 10);
                DeliverdDate = FULLDELIVERDATE.substring(0, 10);
                String ReturnDate = FULLRETURNDATE.substring(0, 10);
                

                System.out.println("    "+BOOKINGID + "\t        " + BookingDate + "\t    " + CUSTOMERID + "\t         " + ADMINID + "\t          " + CARID
                        + "\t  " + DeliverdDate + "\t" + ReturnDate + "\t     " + BOOKINGSTATUS );              
            }
           
            Layout.tableBottomLayout();

            System.out.print("Enter BookingID to Cancel : ");
            int choice =  InputValidation.checkForInteger(sc); 
            try {
            	LocalDate DeliverDateAsDate = LocalDate.parse(DeliverdDate);
            	if(Refund.isBefore(DeliverDateAsDate)) {
            		final String updateQuery = "UPDATE CAR.Booking SET BookingStatus = 'cancelled' WHERE BookingID = ?";
                    PreparedStatement cancelStat = con.prepareStatement(updateQuery);
                    cancelStat.setInt(1, choice);
                    cancelStat.executeQuery();
                    
                    final String getCarIDQuery = "SELECT CarID AS CARID FROM CAR.Booking WHERE BookingID = ?";
                    PreparedStatement getCarIDStat = con.prepareStatement(getCarIDQuery);
                    getCarIDStat.setInt(1,choice);
                    ResultSet CarIDResult=getCarIDStat.executeQuery();
                    
                    int CarID=0;
                    if (CarIDResult.next()) {
                    	CarID = CarIDResult.getInt("CARID");
                    }
                    final String setAvailability = "UPDATE CAR.Cars SET AVAILABILITY = 'yes' WHERE CarID = ?";
                    PreparedStatement setStat = con.prepareStatement(setAvailability);
                    setStat.setInt(1, CarID);
                    setStat.executeQuery();
                    System.out.println("Your Amount will be refunded within 3 wworking days");
                    
                    System.out.println("Enter 1 To Go Previous");
                    int choice1 =  InputValidation.checkForInteger(sc); 
                    if(choice1==1) {
                    	LIstOfMenus.showMenu3();
                    }
                    else {
                    	System.out.println("Invalid Choice");
                    }
            	}
            	else {
            		System.out.println("Car already Delivered Can't Process Refund");
            	}
                
            }catch(Exception e) {
            	System.out.println(e.getMessage());
            }
            
            
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    
	}
}
