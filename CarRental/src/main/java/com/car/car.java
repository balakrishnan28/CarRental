package com.car;

public class car {
    private String brand;
    private String model;
    private String regNumber;
    private int seatCount;
    private int mileage;
    private int parkingId;
    private int year;
    private double rentalRate;
    private String available;

    public car(String brand, String model, String regNumber, int seatCount, int mileage, int parkingId, int year, double rentalRate, String available) {
        this.brand = brand;
        this.model = model;
        this.regNumber = regNumber;
        this.seatCount = seatCount;
        this.mileage = mileage;
        this.parkingId = parkingId;
        this.year = year;
        this.rentalRate = rentalRate;
        this.available = available;
    }

    public car() {
		// TODO Auto-generated constructor stub
	}

	public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public String getAvailability() {
        return available;
    }

    public void setAvailability(String available) {
        this.available = available;
    }
}
