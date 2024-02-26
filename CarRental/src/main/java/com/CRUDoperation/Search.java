package com.CRUDoperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedHashSet;
import java.util.Scanner;

import com.Validation.InputValidation;
import com.booking.booking;
import com.main.Layout;
import com.main.dbConnection;

interface Searchable{
	public void basedOnModel();
	public void basedOnLocation();
	public void basedOnYear();
}
public class Search implements Searchable{
	@Override
	public void basedOnModel() {
	    Scanner sc = new Scanner(System.in);
	    LinkedHashSet<String> hs = new LinkedHashSet<>();

	    try {
	        final String QUERY = "SELECT Model FROM CAR.Cars GROUP BY Model";

	        Connection con = dbConnection.doDBConnection();
	        PreparedStatement statement = con.prepareStatement(QUERY);
	        ResultSet result = statement.executeQuery();

	        System.out.println("Available Car Models:");
	        while (result.next()) {
	            String model = result.getString("Model");
	            hs.add(model);
	        }

	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }

	    int num = 1;
	    for (String model : hs) {
	        System.out.println(num + ". " + model);
	        num++;
	    }

	    System.out.println("Enter your choice");
	    int choice =  InputValidation.checkForInteger(sc); 

	    if (choice > 0 && choice <= hs.size()) {
	        String selectedModel = hs.stream().skip(choice - 1).findFirst().orElse(null);

	        if (selectedModel != null) {
	            final String SEARCH_QUERY = "SELECT * FROM CAR.Cars WHERE Model = ? AND Availability = 'yes'";
	            try {
	                Connection con = dbConnection.doDBConnection();
	                PreparedStatement stat = con.prepareStatement(SEARCH_QUERY);
	                stat.setString(1, selectedModel);
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
	                    int year = result.getInt("Year");
	                    double rentalRate = result.getDouble("RentalRate");
	                    String availability = result.getString("Availability");

	                    System.out.println(carID + "\t" + brand + "\t" + model + "\t" + regNumber + "\t     " + seatCount
	                            + "\t         " + mileage + "\t             " + parkingID + "\t        " + year + "\t  "
	                            + rentalRate + "\t    " + availability);
	                }

	                Layout.tableBottomLayout();
	                System.out.println("Enter carID for booking : ");
	                int choice1 =  InputValidation.checkForInteger(sc); 
	                booking.dobooking(choice1);

	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        } else {
	            System.out.println("Invalid choice. Please choose a valid option.");
	        }
	    } else {
	        System.out.println("Invalid choice. Please choose a valid option.");
	    }
	}

    
    @Override
    public void basedOnLocation() {
        Scanner sc = new Scanner(System.in);

        try {
            final String QUERY = "SELECT PARKINGID, Location FROM CAR.parking ORDER BY PARKINGID";
            Connection con = dbConnection.doDBConnection();
            PreparedStatement stat = con.prepareStatement(QUERY);
            ResultSet qresult = stat.executeQuery();

            System.out.println("Available Parking Locations:");
            while (qresult.next()) {
                int parkingID = qresult.getInt("PARKINGID");
                String location = qresult.getString("Location");
                System.out.println(parkingID + ". " + location);
            }

            System.out.println("Enter parking location:");
            int selectedLocation =  InputValidation.checkForInteger(sc); 

            final String SEARCH_QUERY = "SELECT * FROM CAR.Cars WHERE ParkingID = ? AND Availability='yes'";
            PreparedStatement searchStatement = con.prepareStatement(SEARCH_QUERY);
            searchStatement.setInt(1, selectedLocation);
            ResultSet result = searchStatement.executeQuery();

            Layout.carLayout();

            while (result.next()) {
                int carID = result.getInt("CarID");
                String brand = result.getString("Brand");
                String model = result.getString("Model");
                String regNumber = result.getString("RegNumber");
                int seatCount = result.getInt("SeatCount");
                int mileage = result.getInt("Mileage");
                int parkingID = result.getInt("ParkingID");
                int year = result.getInt("Year");
                double rentalRate = result.getDouble("RentalRate");
                String availability = result.getString("Availability");

                System.out.println(carID + "\t" + brand + "\t" + model + "\t" + regNumber + "\t     " + seatCount
                        + "\t         " + mileage + "\t             " + parkingID + "\t        " + year + "\t  "
                        + rentalRate + "\t    " + availability);
            }

            Layout.tableBottomLayout();
            System.out.println("Enter carID for booking : ");
            int choice1 =  InputValidation.checkForInteger(sc); 
            booking.dobooking(choice1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void basedOnYear() {
        Scanner sc = new Scanner(System.in);
        LinkedHashSet<Integer> years = new LinkedHashSet<>();

        try {
            final String QUERY = "SELECT Year FROM CAR.Cars GROUP BY Year";

            Connection con = dbConnection.doDBConnection();
            PreparedStatement statement = con.prepareStatement(QUERY);
            ResultSet result = statement.executeQuery();

            System.out.println("Available Car Years:");
            while (result.next()) {
                int year = result.getInt("Year");
                years.add(year);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        int num = 1;
        for (int year : years) {
            System.out.println(num + ". " + year);
            num++;
        }

        System.out.println("Enter your choice");
        int choice =  InputValidation.checkForInteger(sc); 

        int selectedYear = -1;

        if (choice > 0 && choice <= years.size()) {
            selectedYear = years.stream().skip(choice - 1).findFirst().orElse(-1);
        }

        if (selectedYear != -1) {
            final String SEARCH_QUERY = "SELECT * FROM CAR.Cars WHERE Year = ? AND Availability = 'yes'";
            try {
                Connection con = dbConnection.doDBConnection();
                PreparedStatement stat = con.prepareStatement(SEARCH_QUERY);
                stat.setInt(1, selectedYear);
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
                Layout.tableBottomLayout();

                System.out.println("Enter carID for booking : ");
                int choice1 =  InputValidation.checkForInteger(sc); 
                booking.dobooking(choice1);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Invalid choice. Please choose a valid option.");
        }
    }
    public static void showAll() {
    	Scanner sc=new Scanner(System.in);
    	
    	try {
    		final String ALL = "SELECT * FROM CAR.CARS WHERE Availability = 'yes' ORDER BY CARID";
    			
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
            Layout.tableBottomLayout();

            System.out.println("Enter carID for booking or 0 to go previous: ");
            int choice1 =  InputValidation.checkForInteger(sc); 
            booking.dobooking(choice1);
            
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
}
