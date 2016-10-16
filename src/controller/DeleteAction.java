/**
 * This is the HW4 of 08672
 * DeleteAction
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
import formbean.IdForm;
import model.FavoriteDAO;
import model.Model;
import model.UserDAO;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class DeleteAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);
	private UserDAO userDAO;
	private FavoriteDAO favoriteDAO;

    public DeleteAction(Model model) {
    	userDAO = model.getUserDAO();
    	favoriteDAO = model.getFavoriteDAO();
    }

    public String getName() {
        return "delete.do";
    }

    public String perform(HttpServletRequest request) {
    	System.out.println("[DeleteAction][Begin] perform");
    	
        HttpSession session = request.getSession();

        // If user hasn't logged in, go back to login
        if (session.getAttribute("user") == null) {
        	System.out.println("[DeleteAction][End] perform USER NULL");
            return "login.do";
        }

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            IdForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);
            request.setAttribute("userList",userDAO.getUserList());
            
            System.out.println(11111);
            
            System.out.println(form.isPresent());
            System.out.println(22222);

            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
            	System.out.println("[DeleteAction][End] perform contain errors");
                return "error.jsp";
            }
            System.out.println(33333);

            // Get the user from session
            UserBean user = (UserBean) request.getSession().getAttribute("user");
            int id = form.getIdAsInt();
    		favoriteDAO.delete(id, user.getUserId());

            System.out.println(44444);
            
            FavoriteBean[] favoriteList = favoriteDAO.getUserFavorites(user.getUserId());
	        request.setAttribute("favoriteList", favoriteList);

            System.out.println(55555);
            
            System.out.println("[DeleteAction][End] perform FINISHED");

            // If redirectTo is null, redirect to the "todolist" action
            return "manage.do";
        } catch (Exception e) {
            errors.add(e.getMessage());
            e.printStackTrace();
            return "error.jsp";
        }
    }
}
