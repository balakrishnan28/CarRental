package com.Validation;
import java.util.Scanner;
import java.util.regex.Pattern;

//user defined Exception
class InValidException extends Exception{
	public InValidException(String s) {
		super(s);
	}
}

public class InputValidation {
	
	public static int checkForInteger(Scanner sc) {
	    while(true) {
	    	try {
	        	int integerValue = sc.nextInt();
	        	return integerValue;
	        	
	        }
	        catch(Exception e) {
	        	System.out.print("Your Provided Charecter which is not allowed please Re-Enter :");
	        	sc.nextLine();	        	
	        }
	    }
	}
	
	public static Double checkForDouble(Scanner sc) {
	    while(true) {
	    	try {
	        	Double integerValue = sc.nextDouble();
	        	return integerValue;
	        	
	        }
	        catch(Exception e) {
	        	System.out.print("Your Provided Charecter which is not allowed please Re-Enter :");
	        	sc.nextLine();	        	
	        }
	    }
	}
    
	public static boolean notValidEmail(String email) {
	    boolean result = false;

	    try {
	        boolean p = Pattern.matches("[a-zA-Z]+[a-zA-Z0-9]*@(kiot|gmail|yahoo|outlook).(ac.in|com|co.in)", email);
	        if (!p) {
	            throw new InValidException("Invalid email format.");
	        }
	    } catch (InValidException e) {
	        System.out.println(e.getMessage());
	        result = true;
	    }

	    return result;
	}

	
	public static boolean notValidUsername(String username) {
	    boolean result = false;

	    try {
	        boolean p = Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", username);
	        if (!p) {
	            throw new InValidException("Invalid username format. It should contain at least 8 characters, including uppercase, lowercase, and digit.");
	        }
	    } catch (InValidException e) {
	        System.out.println(e.getMessage());
	        result = true;
	    }

	    return result;
	}

	public static boolean notValidPassword(String username) {
	    boolean result = false;

	    try {
	        boolean p = Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", username);
	        if (!p) {
	            throw new InValidException("Invalid password format. It should contain at least 8 characters, including uppercase, lowercase, and digit.");
	        }
	    } catch (InValidException e) {
	        System.out.println(e.getMessage());
	        result = true;
	    }

	    return result;
	}
	
	public static boolean notValidRegistration(String registration) {
	    boolean result = false;

	    try {
	        boolean p = Pattern.matches("^TN[0-9]{2}[A-Za-z]{1,2}[0-9]{4}$", registration);
	        if (!p) {
	            throw new InValidException("Invalid Licence Number, should be in the format of \'TN01A1234\'.");
	        }
	    } catch (InValidException e) {
	        System.out.println(e.getMessage());
	        result = true;
	    }

	    return result;
	}
	
	public static boolean notValidatePhoneNumber(String phoneNumber) {
	    boolean result = false;
	    try {
	        if (phoneNumber.length() != 10) {
	            throw new InValidException("Invalid phone number. It should be exactly 10 digits. and starts with 6/7/8/9");
	        }
	    } catch (InValidException e) {
	        System.out.println(e.getMessage());
	        result = true;
	    }
	    return result;
	}


	
	public static boolean notValidName(String name) {
	    boolean result = false;
	    try {
	        boolean p = Pattern.matches("[a-zA-Z]{3,}", name);
	        if (!p) {
	            throw new InValidException("Invalid name. It should contain only letters and have a minimum length of 3 characters.");
	        }
	    } catch (InValidException e) {
	        System.out.println(e.getMessage());
	        result = true;
	    }
	    return result;
	}
	
	public static boolean notValidDrivingLicense(String drivingLicense) {
	    boolean result = false;
	    try {
	        if (drivingLicense.length() != 16) {
	            throw new InValidException("Invalid driving license. It should be a 16-digit number.");
	        }
	    } catch (InValidException e) {
	        System.out.println(e.getMessage());
	        result = true;
	    }
	    return result;
	}
	
	public static boolean notValidDate(String Date) {
		boolean result = false;
		try {
			boolean p = Pattern.matches("\\d{4}-\\d{2}-\\d{2}",Date);
			if(!p) {
				throw new InValidException("Date should be in the form of YYYY-MM-DD ");
			
			}
		}
		catch(InValidException e) {
			System.out.println(e.getMessage());
			result=true;
		}
		return result;
	}
	
	public static boolean notCard(String CardNumer) {
		boolean result = false;
		try {
			boolean p = Pattern.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}",CardNumer);
			if(!p) {
				throw new InValidException("Card Number should be in 0000-0000-0000-0000 ");			
			}
		}
		catch(InValidException e) {
			System.out.println(e.getMessage());
			result=true;
		}
		return result;
	}
	

}
