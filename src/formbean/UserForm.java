/**
 * This is the HW4 of 08672
 * UserForm
 * @author Hao Wang (haow2)
 * 10/12/2016
 * */
package formbean;

import java.util.*;

import org.mybeans.form.FormBean;

public class UserForm extends FormBean {
	private String emailAddress = "";
	
	public String getEmailAddress()  {
		return this.emailAddress;
	}
	
	public void setEmailAddress(String s) {
		if (emailAddress == null) {
			emailAddress = "";
		}
		emailAddress = trimAndConvert(s,"<>>\"]");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (emailAddress == null || emailAddress.length() == 0) {
			errors.add("User Name is required");
			emailAddress = "";
		}
		
		return errors;
	}
}
