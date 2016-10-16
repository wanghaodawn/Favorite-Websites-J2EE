/**
 * This is the HW4 of 08672
 * FavoriteBean
 * @author Hao Wang (haow2)
 * 10/12/2016
 * */

package databean;

public class FavoriteBean {
	/**
	 * Instance Variables
	 * */
	private int favoriteId;
	private int userId;
	private String URL;
	private String comment;
    private int clickCount;
    
    /**
     * Get Methods
     * */
    public int getFavoriteId() { 
    	return favoriteId;
    }
    
    public int getUserId() {
    	return userId;       
    }
    
    public String getURL() {
    	return URL;
    }
    
    public String getComment() {
    	return comment;     
    }
    
    public int getClickCount() {
    	return clickCount;
    }
    
    /**
     * Set Methods
     * */
    public void setFavoriteId(int newFavoriteId) {
    	favoriteId = newFavoriteId;
    }
    
    public void setUserId(int newUserId) {
    	userId = newUserId;
    }
    
    public void setURL(String newURL) {
    	URL = newURL;
    }
    
    public void setComment(String newComment) {
    	comment = newComment;
    }
    
    public void setClickCount(int newClickCount) {
    	clickCount = newClickCount;
    }
}
