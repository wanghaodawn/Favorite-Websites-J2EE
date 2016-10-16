/**
 * This is the HW4 of 08672
 * ListAction
 * @author Hao Wang (haow2)
 * 10/12/2016
 * */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import databean.FavoriteBean;
import databean.UserBean;
import formbean.UserForm;
import model.FavoriteDAO;
import model.Model;
import model.UserDAO;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class ListAction extends Action {
	private FormBeanFactory<UserForm> formBeanFactory = FormBeanFactory.getInstance(UserForm.class);
	private UserDAO userDAO;
	private FavoriteDAO favoriteDAO;

    public ListAction(Model model) {
    	userDAO = model.getUserDAO();
    	favoriteDAO = model.getFavoriteDAO();
    }

    public String getName() {
        return "list.do";
    }

    public String perform(HttpServletRequest request) {
    	System.out.println("[ListAction][Begin] perform");
    	
//        HttpSession session = request.getSession();

        // If user hasn't logged in, go back to login
//        if (session.getAttribute("user") == null) {
//        	System.out.println("[ListAction][End] perform USER NULL");
//            return "login.do";
//        }

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
        	UserForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);
            request.setAttribute("userList",userDAO.getUserList());
            
            System.out.println(11111);
            
            System.out.println(form.isPresent());
            
            // If clicked, add one
            String favoriteIdS = request.getParameter("favoriteId");
            String nextURL = null;
            if (favoriteIdS != null) {
            	int favoriteId = Integer.parseInt(favoriteIdS);
            	nextURL = favoriteDAO.read(favoriteId).getURL();
                favoriteDAO.incresaseClickCountByOne(favoriteId);
                return nextURL;
            }

            System.out.println(22222);
            
            // Get the user of the list according to email address
            String emailAddress = request.getParameter("emailAddress");
            UserBean user = userDAO.read(emailAddress);
            if (user != null) {
            	// Current user of the list when the first time it comes
                request.setAttribute("currUser", user);
            }

            System.out.println(33333);

            System.out.println(44444);
            
            // Now user is got from session
//            user = (UserBean) session.getAttribute("user");
            FavoriteBean[] favoriteList = favoriteDAO.getUserFavorites(user.getUserId());
	        request.setAttribute("favoriteList", favoriteList);

            System.out.println(55555);
            
            System.out.println("[ListAction][End] perform FINISHED");

            // If redirectTo is null, redirect to the "todolist" action
            return "list.jsp";
        } catch (Exception e) {
            errors.add(e.getMessage());
            e.printStackTrace();
            return "error.jsp";
        }
    }
}
