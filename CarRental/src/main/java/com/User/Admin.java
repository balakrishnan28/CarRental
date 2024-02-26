package com.User;
import com.User.UserDetails;

public class Admin extends UserDetails {
	public Admin(int id,String roles,String username, String password, String firstName, String lastName, String phoneNumber, String email, String Gender,int Age) {
        super(id,roles,username, password, firstName, lastName, phoneNumber, email, Gender, Age);
    }
	
}