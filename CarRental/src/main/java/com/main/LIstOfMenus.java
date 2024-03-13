package com.main;
import java.util.Scanner;
import com.Validation.Login;
import com.booking.Refund;
import com.booking.booking;
import com.car.car;
import com.payment.Notification;
import  com.CRUDoperation.*;
import com.User.Customer;
import com.User.Guest;
public class LIstOfMenus {
    public static void showDashboard(){        
        int choice=0;
        while (true) {
        	Scanner sc = new Scanner(System.in);
        	System.out.println("------------------------------\n" +
        	                   "           DashBoard          \n" +
        	                   "------------------------------\n" +
        	                   "Choice    Action\n" +
        	                   "                              \n" +
        	                   "  1.      Login\n" +
        	                   "  2.      Register\n" +
        	                   "  3.      Login as Guest\n" +
        	                   "  4.      About\n" +
        	                   "  5.      Exit\n" +
        	                   "------------------------------\n" +
        	                   "\n" +
        	                   "Enter your choice: ");

            try {
            	choice = sc.nextInt(); 
            }
            catch(Exception e) {
            	System.out.println("Your Provided Charecter which is not allowed please Re-Enter");
            	showDashboard();
            }
            switch (choice) {
                case 1:
                	String role=Login.getRole();
                	if(Login.isAdmin(role)) {
                		if(Login.authentication(role)) {
                		showMenu2();
                		}
                	}
                	if(Login.isCustomer(role)) {
                		if(Login.authentication(role)) {
                		showMenu3();
                		}
                	}
                	break;
                case 2:
                	
                	add.addCustomer();
                    break;
                case 3:
                	System.out.println("Note: Guest can only view available cars if you wish to booking choose 2 for new register");
                	System.out.println();
                	Guest.showMenu();
                	break;
                case 4:
                	Layout.about();
                case 5:
                    System.out.println("Thank You ! Come Again :)");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
    
    public static void showMenu2() {  		   		
    		int choice1=0;
    	
    		while(true) {
    			Scanner sc = new Scanner(System.in);
    			System.out.println("\n------------------------------" +
    			                   "\n    Welcome To Admin Menu     " +
    			                   "\n------------------------------" +
    			                   "\nChoice  Action"+
    			                   "\n 1.    Add Customer" +
    			                   "\n 2.    Remove Customer" +
    			                   "\n 3.    Add Car" +
    			                   "\n 4.    Remove Car" +
    			                   "\n 5.    Update Car" +
    			                   "\n 6.    View All Bookings" +
    			                   "\n 7.    Previous" +
    			                   "\n 8.    Exit" +
    			                   "\n------------------------------" +
    			                   "\n" +
    			                   "Enter your choice: ");

        		try {
                	choice1 = sc.nextInt(); 
                }
                catch(Exception e) {
                	System.out.println("Your Provided Charecter which is not allowed please Re-Enter");
                	showMenu2();
                }			
    			switch(choice1) {
    			case 1:
    				showMenu5();
    				break;
    			case 2:
    				remove.removeCustomer();
    				break;
    			case 3:
    				add.addCar();
    				break;
    			case 4:
    				remove.removeCar();
    				break;
    			case 5:
    				Update.doUpdate();
    				break;
    			case 6:
    				booking.showAllBooking();
    				break;
    			case 7:
    				showDashboard();
    				break;
    			case 8:
    				System.out.println("Thank You ! Come Again :)");
    				System.exit(0);
    				break;
    			default:
                    System.out.println("Invalid choice. Please choose again.");
    			}
    		}
    }
    public static void showMenu3() {
    	int choice2=0;
    	while(true) {
    		Scanner sc = new Scanner(System.in);
    		System.out.println("\n------------------------------" +
    		                   "\n   Welcome To Customer Menu   " +
    		                   "\n------------------------------" +
    		                   "\n Choice  Action" +
    		                   "\n 1.    Profile" +
    		                   "\n 2.    Search" +
    		                   "\n 3.    Inbox (Notification)" +
    		                   "\n 4.    History" +
    		                   "\n 5.    Cancellation" +
    		                   "\n 6.    Previous" +
    		                   "\n 7.    Exit" +
    		                   "\n------------------------------" +
    		                   "\n" +
    		                   "Enter your choice: ");

    		try {
            	choice2 = sc.nextInt(); 
            }
            catch(Exception e) {
            	System.out.println("Your Provided Charecter which is not allowed please Re-Enter");
            	showMenu3();
            }
    		switch(choice2) {
    		case 1:
    			Update.updateCustomerDetails();
    			break;
    		case 2:
    			showMenu4();
    			break;
    		case 3:
    			Notification.showNotifications();
    			break;
    		case 4:
    			booking.showBooking();
    			break;
    		case 5:
    			Refund.doRefund();
    			break;
    		case 6:
    			showDashboard();
    			break;
    		case 7:
    			System.out.println("Thank You ! Come Again :)");
    			System.exit(0);
    		default:
                System.out.println("Invalid choice. Please choose again.");
    		}
    	}
    }
    Search search =new Search();
    public static void showMenu4() {
    	while(true) {
    		Scanner sc = new Scanner(System.in);
    		System.out.println("-----------------------" +
    		                   "\nSearch based on ???" +
    		                   "\n-----------------------" +
    		                   "\n Choice  Action" +
    		                   "\n 1.    All" +
    		                   "\n 2.    Model" +
    		                   "\n 3.    Location" +
    		                   "\n 4.    Year" +
    		                   "\n 5.    Previous" +
    		                   "\n 6.    Exit" +
    		                   "\n-----------------------" +
    		                   "\n" +
    		                   "Enter Your Choice : ");

        	
        	int choice3=0;
        	try {
            	choice3 = sc.nextInt(); 
            }
            catch(Exception e) {
            	System.out.println("Your Provided Charecter which is not allowed please Re-Enter");
            	showMenu4();
            }
        	switch(choice3) {
        	case 1:
        		Search.showAll();
        		break;
        	case 2:
        		Search searchModel = new Search();
                searchModel.basedOnModel();
        		break;
        	case 3:
        		Search searchLocation = new Search();
                searchLocation.basedOnLocation();
                break;
        	case 4:
        		 Search searchYear = new Search();
                 searchYear.basedOnYear();
        		break;
        	case 5:
        		showMenu3();
        		break;
        	case 6:
        		System.out.println("Thank You ! Come Again :)");
        		System.exit(0);
        	default:
        		System.out.println("Invalid choice. Please choose again.");
        	}
    	}
    	
    	
    }
    public static void showMenu5(){
    	Customer customer = new Customer();
    	Scanner sc=new Scanner(System.in);
    	while(true) {
    		
    		System.out.println("------------------------------" +
    		                   "\n      Customer Management     " +
    		                   "\n------------------------------" +
    		                   "\n Choice  Action" +
    		                   "\n 1.    Add new Customer" +
    		                   "\n 2.    Activate Existing Customer" +
    		                   "\n 3.    Previous" +
    		                   "\n 4.    Exit" +
    		                   "\n------------------------------" +
    		                   "\n" +
    		                   "Enter your Choice : ");

    		int choice=0;
    		try {
            	choice = sc.nextInt(); 
            }
            catch(Exception e) {
            	System.out.println("Your Provided Charecter which is not allowed please Re-Enter");
            	showMenu5();
            }
    		switch(choice) {
    			case 1:
    				add.addCustomer();
    				break;
    			case 2:
    				
    				customer.activateCustomer();
    				break;
    			case 3:
    				showMenu2();
    				break;
    			case 4:
    				System.out.println("Thank You ! Come Again :)");
    				break;
    			default:
    				System.out.print("Invalid choice, re-enter  : ");
    				
    		}
    	}
    }
}
