/**
 * This is the HW4 of 08672
 * ManageAction
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
import formbean.FavoriteForm;
import model.FavoriteDAO;
import model.Model;
import model.UserDAO;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class ManageAction extends Action {
	private FormBeanFactory<FavoriteForm> formBeanFactory = FormBeanFactory.getInstance(FavoriteForm.class);
	private UserDAO userDAO;
	private FavoriteDAO favoriteDAO;

    public ManageAction(Model model) {
    	userDAO = model.getUserDAO();
    	favoriteDAO = model.getFavoriteDAO();
    }

    public String getName() {
        return "manage.do";
    }

    public String perform(HttpServletRequest request) {
    	System.out.println("[ManageAction][Begin] perform");
    	
        HttpSession session = request.getSession();

        // If user hasn't logged in, go back to login
        if (session.getAttribute("user") == null) {
        	System.out.println("[ManageAction][End] perform USER NULL");
            return "login.do";
        }

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            FavoriteForm form = formBeanFactory.create(request);
            String URL = request.getParameter("URL");
            System.out.println(URL);
            form.setURL(URL);
            request.setAttribute("form", form);
            request.setAttribute("userList", userDAO.getUserList());
            
            String action = request.getParameter("action");
            
            // Get the user from session
            UserBean user = (UserBean) request.getSession().getAttribute("user");
            request.setAttribute("favoriteList", favoriteDAO.getUserFavorites(user.getUserId()));
            
            System.out.println(11111);
            
            // If clicked, add one
            String favoriteIdS = request.getParameter("favoriteId");
            String nextURL = null;
            if (favoriteIdS != null) {
            	int favoriteId = Integer.parseInt(favoriteIdS);
            	nextURL = favoriteDAO.read(favoriteId).getURL();
                favoriteDAO.incresaseClickCountByOne(favoriteId);
                return nextURL;
            }
            
            System.out.println(form.isPresent());

            // If no params were passed, return with no errors so that the form
            // will be presented (we assume for the first time).
            if (!form.isPresent()) {
            	System.out.println("[ManageAction][End] perform form NOT PRESENT");
                return "manage.jsp";
            }
            System.out.println(22222);

            // Any validation errors?
//            form.setURL((String) request.getAttribute("URL"));
            System.out.println(request.getAttribute("URL"));
            System.out.println(form.getURL());
            System.out.println(form.getComment());
            System.out.println(form.getAction());
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
            	System.out.println("[ManageAction][End] perform contain errors");
                return "manage.jsp";
            }
            System.out.println(33333);

            System.out.println(44444);
            
            FavoriteBean favoriteBean = new FavoriteBean();
            favoriteBean.setUserId(user.getUserId());
            favoriteBean.setURL(form.getURL());
            favoriteBean.setComment(form.getComment());
            favoriteBean.setClickCount(0);
            
            if (action.equals("Add")) {
            	favoriteDAO.create(favoriteBean);
            }
            
            System.out.println(55555);
            
            request.setAttribute("favoriteList", favoriteDAO.getUserFavorites(user.getUserId()));

            System.out.println(66666);
            
            System.out.println("[ManageAction][End] perform FINISHED");

            // If redirectTo is null, redirect to the "todolist" action
            return "manage.jsp";
        } catch (Exception e) {
            errors.add(e.getMessage());
            e.printStackTrace();
            return "error.jsp";
        }
    }
}
