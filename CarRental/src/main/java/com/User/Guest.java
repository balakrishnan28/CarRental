package com.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.main.Layout;
import com.main.dbConnection;

public class Guest {
	public static void showMenu() {
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
		    
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
