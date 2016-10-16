/**
 * This is the HW4 of 08672
 * FavoriteForm
 * @author Hao Wang (haow2)
 * 10/12/2016
 * */
package formbean;

import java.util.*;

import org.mybeans.form.FormBean;

public class ChangePasswordForm extends FormBean {
	/**
	 * Instance variables
	 * */
    private String password1;
    private String password2;
	
	/**
     * Get fields
     * */
    public String getPassword1() {
        return this.password1;
    }

    public String getPassword2() {
        return password2;
    }
    
    /**
     * Set fields
     * */
    public void setPassword1(String s) {
    	if (s == null) {
    		s = "";
    	}
        this.password1 = s.trim();
    }

    public void setPassword2(String s) {
    	if (s == null) {
    		s = "";
    	}
        this.password2 = s.trim();
    }
    
    /**
     * Validataion
     * */
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        
        // Validate fields null or zero length
        if (password1 == null || password1.length() == 0) {
        	errors.add("Password is required");
        	password1 = "";
        } 
        if (password2 == null || password2.length() == 0) {
        	errors.add("Confirmed password is required");
        	password2 = "";
        }
        
        if (errors.size() > 0) {
        	return errors;
        }
        
        if (password1.matches(".*[<>\"].*")) {
        	errors.add("Password may not contain angle brackets or quotes");
        }
        if (password2.matches(".*[<>\"].*")) {
        	errors.add("Confirmed password may not contain angle brackets or quotes");
        }
        if (!password1.equals(password2)) {
        	errors.add("Two passwords don't match");
        }
        
        return errors;
    }
}
