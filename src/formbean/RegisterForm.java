/**
 * This is the HW4 of 08672
 * RegisterForm
 * @author Hao Wang (haow2)
 * 10/12/2016
 * */

package formbean;

import java.util.*;

/**
 * Inherited some instance variables and methods from LoginForm
 * */
public class RegisterForm extends LoginForm {
	/**
	 * Instance variables
	 * */
    private String firstName;
    private String lastName;
    private String confirmPassword;
    
    /**
     * Get fields
     * */
    public String getFirstName() {
    	return firstName;
    }
    
    public String getLastName() {
    	return lastName;
    }
    
    public String getConfirmPassword() {
    	return confirmPassword;
    }
    
    /**
     * Set fields
     * */    
    public void setFirstName(String s) {
    	if (s == null) {
    		s = "";
    	}
    	this.firstName = s.trim();
    }
    
    public void setLastName(String s) {
    	if (s == null) {
    		s = "";
    	}
    	this.lastName = s.trim();
    }
    
    public void setConfirmPassword(String s) {
    	if (s == null) {
    		s = "";
    	}
    	this.confirmPassword = s.trim();
    }
    
    /**
     * Validataion
     * */
    @Override
    public List<String> getValidationErrors() {
        List<String> errors = super.getValidationErrors();
        
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(super.getEmailAddress());
        System.out.println(super.getPassword());
        System.out.println(super.getAction());
        
        // Validate fields null or zero length
        if (firstName == null || firstName.length() == 0) {
        	errors.add("First name is required.");
        	firstName = "";
        }
        if (lastName == null || lastName.length() == 0) {
        	errors.add("Last name is required.");
        	lastName = "";
        }
        if (confirmPassword == null || confirmPassword.length() == 0) {
        	errors.add("Please confirm password");
        	confirmPassword = "";
        }
        
        if (firstName.matches(".*[<>\"].*")) {
        	errors.add("First name may not contain angle brackets or quotes");
        }
        if (lastName.matches(".*[<>\"].*")) {
        	errors.add("Last anem may not contain angle brackets or quotes");
        }
        if (confirmPassword.matches(".*[<>\"].*")) {
        	errors.add("Last anem may not contain angle brackets or quotes");
        }
        
        if (!super.getPassword().equals(confirmPassword)) {
        	errors.add("Passwords are not the same");
        }
        return errors;
    }
}
