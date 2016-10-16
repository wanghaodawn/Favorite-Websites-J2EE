/**
 * This is the HW4 of 08672
 * FavoriteForm
 * @author Hao Wang (haow2)
 * 10/12/2016
 * */

package formbean;

import org.mybeans.form.FormBean;

import java.util.*;

public class FavoriteForm extends FormBean {
	private String URL;
	private String comment;
	private String action;
    
    /**
     * Get fields
     * */
    public String getURL() {
        return URL;
    }
    
    public String getComment() {
    	return comment;
    }
    
    public String getAction() {
    	return action;
    }
    
    /**
     * Set fields
     * */
    public void setURL(String s) {
    	if (s == null) {
    		s = "";
    	}
    	this.URL = s.trim();
    }
    
    public void setComment(String s) {
    	if (s == null) {
    		s = "";
    	}
    	this.comment = s.trim();
    }
    
    public void setAction(String s) {
    	if (s == null) {
    		s = "";
    	}
    	this.action = s.trim();
    }
    
    /**
     * Validataion
     * */
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        
        System.out.println("URL: " + URL);
        if (URL == null || URL.length() == 0) {
            errors.add("URL is required");
            URL = "";
        }
        if (comment == null) {
        	comment = "";
        }
        if (action == null) {
        	action = "";
        }
        
        if (errors.size() > 0) {
        	return errors;
        }
        
        if (URL.matches(".*[<>\"].*")) {
        	errors.add("URL may not contain angle brackets or quotes");
        }
        if (comment.matches(".*[<>\"].*")) {
        	errors.add("Comment may not contain angle brackets or quotes");
        }
        return errors;
    }
}
