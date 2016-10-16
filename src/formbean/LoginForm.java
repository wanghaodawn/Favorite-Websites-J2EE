/**
 * This is the HW4 of 08672
 * LoginForm
 * @author Hao Wang (haow2)
 * 10/12/2016
 * */

package formbean;

import org.mybeans.form.FormBean;

import java.util.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;

public class LoginForm extends FormBean {
	/**
	 * Instance variables
	 * */
    private String emailAddress;
    private String password;
    private String action;
    
    /**
     * Get fields
     * */
    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getAction() {
        return action;
    }
    
    /**
     * Set fields
     * */
    public void setEmailAddress(String s) {
    	if (s == null) {
    		s = "";
    	}
        this.emailAddress = s.trim();
    }

    public void setPassword(String s) {
    	if (s == null) {
    		s = "";
    	}
        this.password = s.trim();
    }

    public void setAction(String s) {
    	if (s == null) {
    		s = "";
    	}
        this.action = s.trim();
    }
    
    /**
     * Validate email address
     * */
    public boolean isValidEmailAddress(String email) {
    	boolean result = true;
    	try {
    		InternetAddress emailAddr = new InternetAddress(email);
    		emailAddr.validate();
    	} catch (AddressException ex) {
		   result = false;
    	}
    	return result;
	}
    
    /**
     * Validataion
     * */
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        
        // Validate fields null or zero length
        if (emailAddress == null || emailAddress.length() == 0) {
        	errors.add("Email address is required");
        	emailAddress = "";
        } else {
        	// Validate email address
            if (!isValidEmailAddress(emailAddress)) {
            	errors.add("Invalid email address.");
            }
        }
        if (password == null || password.length() == 0) {
        	errors.add("Password is required");
        	password = "";
        }
        
        if (emailAddress.matches(".*[<>\"].*")) {
        	errors.add("Email address may not contain angle brackets or quotes");
        }
        if (password.matches(".*[<>\"].*")) {
        	errors.add("Password may not contain angle brackets or quotes");
        }
            
        return errors;
    }
}
