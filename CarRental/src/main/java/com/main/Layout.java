package com.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Layout {
	public static void IntroLayout() {
		String filePath = "C://Users/lenovo/git/repository//CarRental//src//main/java/com/main/IntroLayout"; // Replace with the actual path to your file

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public static void carLayout() {
    	System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
    	System.out.println("CarID\tbrand\tmodel\tregNumber\tseatCount\tmileage \t parkingID\tyear\trentalRate/Day\tavailability");
    	System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
    }
	public static void tableBottomLayout() {
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
	}
	public static void invoiceBottomLayout() {
		System.out.println("-----------------------------------");
	}
	public static void BookingLayout() {
    	System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    	System.out.println("BookingID\tBookingDate\t    CustomerName\tCarID\t DeliverDate\tReturnDate \t BookingStatus");
    	System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    }
	public static void customerLayout() {
    	System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
	    System.out.println("CustomerID\tFirstName\tLastName\tPhoneNumber\t           MailAddress\t       DrivingLicense\t   Gender\tAge");
    	System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
	}
	public static void customerBottomLayout() {
    	System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

	}
	public static void notificationLayout() {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.println("NotificationID \t PaymentID\t                                 Description");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
	}
	public static void accountLayout() {
		System.out.println("---------------------------------------------------------------------");
		System.out.println("AccountID   Username    Password\tRole\t        Status");
		System.out.println("---------------------------------------------------------------------");
	}
	public static void bottomaccountLayout() {
		System.out.println("---------------------------------------------------------------------");

	}
	public static void about() {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                                             ABOUT");
        System.out.println("--------------------------------------------------------------------------------------------");
        
        System.out.println();
        System.out.println("This application, \"Let's Drive,\" is designed to provide a platform for car rentals");
        System.out.println("with various features and functionalities.");
        System.out.println();
        System.out.println("Major Functionalities:");
        System.out.println();
        System.out.println("1. User Authentication:");
        System.out.println("   - Users can create accounts with unique usernames and passwords.");
        System.out.println("   - Different roles like customers and admins are supported.");
        System.out.println();
        System.out.println("2. Car Management:");
        System.out.println("   - Admins can manage the fleet of available cars.");
        System.out.println("   - Cars have details such as brand, model, registration number, etc.");
        System.out.println();
        System.out.println("3. Booking System:");
        System.out.println("   - Customers can browse available cars and make bookings.");
        System.out.println("   - Bookings include details like booking date, delivery date, return date, etc.");
        System.out.println();
        System.out.println("4. Payment Processing:");
        System.out.println("   - Payment can be made via various methods, including cash and card.");
        System.out.println("   - Payment details and status are recorded.");
        System.out.println();
        System.out.println("5. Notification System:");
        System.out.println("   - Users receive notifications regarding their bookings and payments.");
        System.out.println("   - Notifications include information about payment status, delivery, etc.");
        System.out.println();
        System.out.println("Feel free to explore and use the various features offered by \"Let's Drive\"!");
        System.out.println("--------------------------------------------------------------------------------------------");

        System.out.println();
        System.out.print("To Explore enter 1 :");
        Scanner sc=new Scanner(System.in);
        int choice = sc.nextInt();
        if(choice == 1) {
        	LIstOfMenus.showDashboard();
        }
        else {
        	System.out.println("invalid responce forced to Main Page");
        	LIstOfMenus.showDashboard();
        }
    }

}
