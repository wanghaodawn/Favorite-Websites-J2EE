/**
 * This is the HW4 of 08672
 * Model
 * @author Hao Wang (haow2)
 * 10/12/2016
 * */
package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import databean.FavoriteBean;
import databean.UserBean;

public class Model {
    private FavoriteDAO favoriteDAO;
    private UserDAO userDAO;
    
    /**
	 * Constant vairables
	 * */
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_NAME = "test";
    private static final String JDBC_URL = "jdbc:mysql://localhost/" + DB_NAME + "?useSSL=false";
	
	private final String USER_TABLE_NAME = "haow2_user";
	private final String FAVORITE_TABLE_NAME = "haow2_favorite";

    public Model(ServletConfig config) throws ServletException {
    	System.out.println("[Model][Begin] Constructor");
		try {
			userDAO = new UserDAO(JDBC_DRIVER, JDBC_URL, USER_TABLE_NAME);
			favoriteDAO = new FavoriteDAO(JDBC_DRIVER, JDBC_URL, FAVORITE_TABLE_NAME, USER_TABLE_NAME);
			
			createInitItems();
			
		} catch (MyDAOException e) {
			throw new ServletException(e);
		}
		System.out.println("[Model][End] Constructor");
    }
    
    public void createInitItems() throws MyDAOException {
    	if (userDAO.size() == 0 && favoriteDAO.size() == 0) {
    		int userId1 = createUser("123@123.com", "Hao", "Wang", "123");
    		int userId2 = createUser("234@123.com", "hao", "wang", "234");
    		int userId3 = createUser("234@234.com", "David", "Beckham", "345");
    		
    		createFavorite(userId1, "www.baidu.com", "Chinese Search Engine", 0);
    		createFavorite(userId1, "www.google.com", "English Search Engine", 0);
    		createFavorite(userId1, "www.bing.com", "Microsoft Search Engine", 0);
    		createFavorite(userId1, "www.yahoo.com", "Yahoo Search Engine", 0);
    		
    		createFavorite(userId2, "www.microsoft.com", "Website of Microsoft", 0);
    		createFavorite(userId2, "www.citadel.com", "Website of Citadel", 0);
    		createFavorite(userId2, "www.twosigma.com", "Website of Two Sigma", 0);
    		createFavorite(userId2, "www.hudson-trading.com", "Website of Hudson River Trading", 0);
    		
    		createFavorite(userId3, "www.amazon.com", "Amazon Online Shooping", 0);
    		createFavorite(userId3, "www.ebay.com", "Ebay Online Shopping", 0);
    		createFavorite(userId3, "www.taobao.com", "Taobao Online Shopping", 0);
    		createFavorite(userId3, "www.jd.com", "Jingdong Online Shopping", 0);
		}
    }

    public FavoriteDAO getFavoriteDAO() {
        return favoriteDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
    
    public int createUser(String emailAddress, String firstName, String lastName, String password) throws MyDAOException {
    	UserBean user = new UserBean();
        user.setEmailAddress(emailAddress);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        userDAO.create(user);
        
        return userDAO.read(emailAddress).getUserId();
    }
    
    public void createFavorite(int userId, String URL, String comment, int clickCount) throws MyDAOException {
    	FavoriteBean favorite = new FavoriteBean();
    	favorite.setURL(URL);
    	favorite.setComment(comment);
    	favorite.setUserId(userId);
    	favorite.setClickCount(clickCount);
        favoriteDAO.create(favorite);
    }
}
