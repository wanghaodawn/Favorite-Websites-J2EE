/**
 * This is the HW4 of 08672
 * UserBean
 * @author Hao Wang (haow2)
 * 10/12/2016
 * */

package databean;

public class UserBean {
	/**
	 * Instance Variables
	 * */
	private int userId;
	private String emailAddress;
	private String firstName;
	private String lastName;
    private String password;

    /**
     * Get Methods
     * */
    public int getUserId() { 
    	return userId;       
    }
    
    public String getEmailAddress() { 
    	return emailAddress;
    }
    
    public String getFirstName() { 
    	return firstName;
    }
    
    public String getLastName() { 
    	return lastName;     
    }
    
    public String getPassword() { 
    	return password;
    }
    
    /**
     * Set Methods
     * */
    public void setUserId(int newUserId) {
    	userId = newUserId;
    }
    
    public void setEmailAddress(String newEmailAddress) { 
    	emailAddress = newEmailAddress;
    }
    
    public void setFirstName(String newFirstName) { 
    	firstName = newFirstName;
    }
    
    public void setLastName(String newLastName) { 
    	lastName = newLastName;
    }
    
    public void setPassword(String newPassword) { 
    	password = newPassword;
    }
}
