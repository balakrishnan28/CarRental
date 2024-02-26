package com.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

import com.Validation.InputValidation;
import com.main.dbConnection;

interface Paymentable {
	public void cashPayment(int BookingID, int TotalAmount);
	public void cardPayment(int BookingID, int TotalAmount);
}

public class Payment implements Paymentable{
	@Override
	public void cashPayment(int BookingID, int TotalAmount) {
		
        LocalDate currentDateAsDate = LocalDate.now();
        String PaymentDate = currentDateAsDate.toString();               
		String paymentType = "cash";
		
		
		Scanner sc=new Scanner(System.in);		
		Double userAmount = 0.0;
	
		while(true) {
			System.out.println("Enter the Amount : ");
			userAmount=  InputValidation.checkForDouble(sc); 
			if(userAmount == TotalAmount) {
				System.out.println("payment sucessfull ...");
				System.out.println("thankyou ! come again :)");
				String PaymentStatus="sucess";
				storeInDB(BookingID, paymentType, TotalAmount, PaymentDate, PaymentStatus);
								
			}
			if(userAmount > TotalAmount) {
				
				Double refund =userAmount - TotalAmount;
				System.out.println("OOPS ! Your are provided exccess amount accidently. Amount "+refund+" refunded");
				System.out.println("payment sucessfull ...");
				System.out.println("thankyou ! come again :)");
				String PaymentStatus = "sucess";
				storeInDB(BookingID, paymentType, TotalAmount, PaymentDate, PaymentStatus);
				
			}
			if(userAmount < TotalAmount) {
				System.out.println("your amount is insufficient");
				
			}
		}
	}

	@Override
	public void cardPayment(int BookingID, int TotalAmount) {
        Scanner sc = new Scanner(System.in);
        String paymentType = "card";
        LocalDate currentDateAsDate = LocalDate.now();
        String PaymentDate = currentDateAsDate.toString(); 

        System.out.println("Enter Card Number: ");
        String cardNumber = sc.next();
        while(InputValidation.notCard(cardNumber)) {
        	System.out.print("Re-Enter : ");
        	cardNumber = sc.next();
        }

        System.out.println("Enter Expiry Month (MM): ");
        int expiryMonth = InputValidation.checkForInteger(sc);
        while(expiryMonth>12 || expiryMonth <0) {
        	System.out.print("Your Provided Invalid Month please Re-Enter Valid Month : ");
        	expiryMonth = InputValidation.checkForInteger(sc);
        }

        System.out.println("Enter Expiry Year (YYYY): ");
        int expiryYear = InputValidation.checkForInteger(sc);
        while(expiryYear<=2023) {
        	System.out.print("Your Provided Expired year please Re-Enter Valid Year : ");
        	expiryYear = InputValidation.checkForInteger(sc);
        }

        System.out.println("Enter CVV: ");
        int cvv = InputValidation.checkForInteger(sc); 
        String cvvString = Integer.toString(cvv);
        int length = cvvString.length();
        while(length!=3) {
        	System.out.println("Your Provided Invalid cvv that should be in 3 numbers,Re-Enter : ");
        	cvv = InputValidation.checkForInteger(sc);
        	cvvString = Integer.toString(cvv);
            length = cvvString.length();
        }

        if (validateCard(cardNumber, expiryMonth, expiryYear, cvv)) {
        	
            System.out.println("Card verification successful. Processing payment...");
            System.out.println("Payment successful. Thank you!");
            String PaymentStatus = "sucess";
            storeInDB(BookingID, paymentType, TotalAmount, PaymentDate, PaymentStatus);
            
            
            
        } else {
            System.out.println("Card verification failed. Payment unsuccessful.");
        }
    }

    public static boolean validateCard(String cardNumber, int expiryMonth, int expiryYear, int cvv) {
       
        YearMonth currentYearMonth = YearMonth.now();
        YearMonth cardExpiry = YearMonth.of(expiryYear, expiryMonth);

        return cardExpiry.isAfter(currentYearMonth);
    }
    public void storeInDB(int BookingID, String paymentType,int PaymentAmount, String PaymentDate, String PaymentStatus) {
    	try {
    		Connection con = dbConnection.doDBConnection();
            final String MAX_QUERY = "SELECT MAX(PAYMENTID) AS PAYMENTID FROM CAR.PAYMENT";
            final String INSERT_QUERY = "INSERT INTO CAR.Payment (PaymentID, BookingID, paymentType, PaymentAmount, PaymentDate, payment_status) VALUES (?, ?, ?, ?, TO_DATE(?,'YYYY-MM-DD'), ?)";            		
            PreparedStatement preparedStatement = con.prepareStatement(INSERT_QUERY);            
            PreparedStatement accIdStatement = con.prepareStatement(MAX_QUERY);
            ResultSet accIdResult = accIdStatement.executeQuery();
            int PAYMENTID = 0;
            if (accIdResult.next()) {
            	PAYMENTID = accIdResult.getInt("PAYMENTID");
            }
            accIdResult.close();
            accIdStatement.close();
            
            preparedStatement.setInt(1, PAYMENTID+1);
            preparedStatement.setInt(2, BookingID);
            preparedStatement.setString(3, paymentType);
            preparedStatement.setInt(4, PaymentAmount);
            preparedStatement.setString(5, PaymentDate);
            preparedStatement.setString(6, PaymentStatus);

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
            	Notification.makeNotification(PAYMENTID+1);
                System.exit(0);
            } else {
                System.out.println("Failed to store in DB");
            }

            preparedStatement.close();
            con.close();
    		
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
	
}