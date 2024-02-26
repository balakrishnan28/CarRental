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
    public static void showMenu1(){        
        int choice=0;
        while (true) {
        	Scanner sc = new Scanner(System.in);
        	System.out.println("------------------------------");
        	System.out.println("           DashBoard          ");
        	System.out.println("------------------------------");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Login as Guest");
            System.out.println("4. About");
            System.out.println("5. Exit");
            System.out.println("------------------------------");
            System.out.println("");
            System.out.print("Enter your choice: ");
            try {
            	choice = sc.nextInt(); 
            }
            catch(Exception e) {
            	System.out.println("Your Provided Charecter which is not allowed please Re-Enter");
            	showMenu1();
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
    			Scanner sc=new Scanner(System.in);
    			System.out.println();
    			System.out.println("------------------------------");
    			System.out.println("    Welcome To Admin Menu     ");
    			System.out.println("------------------------------");
        		System.out.println("1. Add Customer");
        		System.out.println("2. Remove Customer");
        		System.out.println("3. Add Car");
        		System.out.println("4. remove Car");
        		System.out.println("5. Update car");
        		System.out.println("6. view all bookings");
        		System.out.println("7. Previous");
        		System.out.println("8. exit");
        		System.out.println("------------------------------");
        		System.out.println("");
        		System.out.print("Enter your choice: ");
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
    				showMenu1();
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
    		Scanner sc=new Scanner(System.in);
    		System.out.println();
    		System.out.println("------------------------------");
    		System.out.println("   Welcome To Customer Menu   ");
    		System.out.println("------------------------------");
    		System.out.println("1. Profile");
    		System.out.println("2. Search");
    		System.out.println("3. Inbox (Notification)");
    		System.out.println("4. History");
    		System.out.println("5. cancellation");
    		System.out.println("6. Prevoius");
    		System.out.println("7. exit");
    		System.out.println("------------------------------");
    		System.out.println("");
    		System.out.print("Enter your choice: ");
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
    			showMenu1();
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
    		System.out.println("-----------------------");
    		System.out.println("Search based on ???");
    		System.out.println("-----------------------");
        	System.out.println("1. All");
        	System.out.println("2. Model");
        	System.out.println("3. Location");
        	System.out.println("4. Year");
        	System.out.println("5. Previous");
        	System.out.println("6. Exit");
    		System.out.println("-----------------------");
        	System.out.println("");
        	System.out.print("Enter Your Choice : ");
        	Scanner sc=new Scanner(System.in);
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
    		System.out.println("------------------------------");
    		System.out.println("      Customer Management     ");
    		System.out.println("------------------------------");
        	System.out.println("1. Add new Customer");
    		System.out.println("2. Activate Existing Customer ");
    		System.out.println("3. previous");
    		System.out.println("4. Exit");
    		System.out.println("------------------------------");
    		System.out.println();
    		System.out.print("Enter your Choice : ");
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
