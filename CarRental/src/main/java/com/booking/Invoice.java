package com.booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;
import com.payment.*;
import com.Validation.InputValidation;
import com.main.Layout;
import com.main.dbConnection;

public class Invoice {
	public static void makeInvoice(int CustomerID, int CarID, int BookingID, int days, String DeliverDate, String ReturnDate) {
		Scanner sc= new Scanner(System.in);
		final String FINDMAXID = "SELECT MAX(InvoiceID) AS MAXID FROM CAR.Invoice";
		final String INSERT = "INSERT INTO CAR.Invoice (InvoiceID, BookingID , CustomerID , AdminID, InvoiceDate, DeliveryDate, ReturnDate, TotalAmount ) VALUES (?, ?, ?, ?, TO_DATE(?,'YYYY-MM-DD'), TO_DATE(?,'YYYY-MM-DD'), TO_DATE(?,'YYYY-MM-DD'), ?)";
		try {
            Connection con = dbConnection.doDBConnection();

            // Get the maximum AccountID
            PreparedStatement accIdStatement = con.prepareStatement(FINDMAXID);
            ResultSet accIdResult = accIdStatement.executeQuery();
            int InvoiceID=0;
            if (accIdResult.next()) {
            	InvoiceID = accIdResult.getInt("MAXID");
            }
            accIdResult.close();
            accIdStatement.close();
            
            final String RATE = "SELECT RentalRate FROM CAR.Cars WHERE CarID = "+CarID+"";
            PreparedStatement rateStatement = con.prepareStatement(RATE);
            ResultSet Result = rateStatement.executeQuery();
            int rate=0;
            if (Result.next()) {
            	rate = Result.getInt("RentalRate");
            }
            int TotalAmount = rate*days;
            System.out.println("insurance(500/day) needed ?");
            System.out.println("1. yes");
            System.out.println("2. no");
            System.out.print("Enter Your Choice : ");
            int choice =  InputValidation.checkForInteger(sc); 
            switch(choice) {
            	case 1:
            		int insuranceAmount=500*days;
            		TotalAmount+=insuranceAmount;
            		break;        	
            	case 2:
            		break;
            	default:
            		break;
            }
            int AdminID=1;
            LocalDate Invoice = LocalDate.now(); 
			String InvoiceDate = Invoice.toString();
            PreparedStatement stat = con.prepareStatement(INSERT);
            stat.setInt(1, InvoiceID+1);
            stat.setInt(2,BookingID);
            stat.setInt(3, CustomerID);
            stat.setInt(4, AdminID);
            stat.setString(5, InvoiceDate);
            stat.setString(6,DeliverDate);
            stat.setString(7, ReturnDate);
            stat.setInt(8, TotalAmount);
            int result = stat.executeUpdate();
            if(result>0) {
            	Layout.invoiceBottomLayout();
    			System.out.println("           INVOICE");
    			Layout.invoiceBottomLayout();
    			System.out.println("InvoiceID : "+InvoiceID+1);
    			System.out.println("BookingID : "+BookingID);
    			System.out.println("CustomerID : "+CustomerID);
    			System.out.println("AdminID : "+AdminID);
    			System.out.println("InvoiceDate : "+InvoiceDate);
    			System.out.println("DeliveryDate : "+DeliverDate);
    			System.out.println("ReturnDate : "+ReturnDate);
    			System.out.println("TotalAmount : "+TotalAmount);
    			Layout.invoiceBottomLayout();
    		   			
    		}
            else {
            	System.out.println("not generated");
            }
            System.out.println("Enter your choice for Payment : ");
            System.out.println("1. cash");
            System.out.println("2. card");
            System.out.println("");
            System.out.println("Enter Your Choice : ");
            int choice1 = sc.nextInt();
            Payment payment = new Payment();
            switch(choice1) {
            case 1:            	
    			payment.cashPayment(BookingID,TotalAmount);
    			break;
            
            case 2:
    			payment.cardPayment(BookingID,TotalAmount);
    			break;
            }
            
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
