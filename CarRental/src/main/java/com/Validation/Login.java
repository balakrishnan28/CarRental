package com.Validation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;
import com.main.LIstOfMenus;
import com.main.dbConnection;

public class Login {

    private static String username;
    private static String password;
    private static String role;

    public static String getRole() {
    	int choice=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("------------------");
        System.out.println("     Login as     ");
        System.out.println("------------------");
        System.out.println("Choice   Action   ");
        System.out.println();
        System.out.println("  1.     Admin");
        System.out.println("  2.     Customer");
        System.out.println("  3.     Exit");
        System.out.println("------------------");
        System.out.println("");
        System.out.print("Enter your Choice : ");
        try {
        	choice = sc.nextInt();
        	if(choice==1) {
            	role="admin";
            }
            else if(choice==2) {
            	role="customer";
            }
            else {
            	LIstOfMenus.showDashboard();
            }
        }
        catch(Exception e) {
        	System.out.println("Your Provided Charecter which is not allowed please Re-Enter");
        	getRole();
        }
        
        return role;
    }

    public static boolean authentication(String role) {
    	Scanner sc=new Scanner(System.in);
        boolean result = false;
        if (role.equals("admin") || role.equals("customer")) {
        	System.out.println();
        	System.out.println("----------------------------------");
        	System.out.println("       Verification Process       ");
        	System.out.println("----------------------------------");
            System.out.print("Enter your username : ");
            username = sc.next();
            System.out.print("Enter your password : ");
            password = sc.next();
            System.out.println("----------------------------------");

            if (patternValidate(username, password)) {
                if (loginValidation(username, password, role)) {
                    System.out.println("Login successful...");
                    result = true;
                } else {
                    System.out.println("Invalid Username or Password, Please login again");
                }
            } else {
                System.out.println("Invalid pattern, Please login again");
            }
        } else {
            System.out.println("Invalid User, Please login again");
        }
        
        return result;
    }

    public static boolean isAdmin(String role) {
        return role.equals("admin");
    }

    public static boolean isCustomer(String role) {
        return role.equals("customer");
    }

    public static boolean patternValidate(String username, String password) {
        boolean p1 = Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", username);
        boolean p2 = Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", password);
        return p1 && p2;
    }

    public static boolean loginValidation(String username, String password, String role) {
        String dbusername = null;
        String dbpassword = null;
        String dbstatus = null;
        String status = "active";

        try {
            Connection con = dbConnection.doDBConnection();
            Statement stat = con.createStatement();

            final String QUERY = "SELECT Username, Passwords , status FROM CAR.Accounts WHERE roles='" + role + "'";

            ResultSet rs = stat.executeQuery(QUERY);
            boolean exit=true;
            while (rs.next() && exit) {
                dbusername = rs.getString("Username");
                dbpassword = rs.getString("Passwords");
                dbstatus = rs.getString("Status");
                if(username.equals(dbusername) && password.equals(dbpassword)) {
                	
                	exit=false;
                }
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
		return ((username.equals(dbusername) && password.equals(dbpassword))&&status.equals(dbstatus));

    }
   
}
