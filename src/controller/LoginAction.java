/**
 * This is the HW4 of 08672
 * LoginAction
 * @author Hao Wang (haow2)
 * 10/12/2016
 * */
package controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import databean.FavoriteBean;
import databean.UserBean;
import formbean.LoginForm;
import model.FavoriteDAO;
import model.Model;
import model.UserDAO;

public class LoginAction extends Action {
    private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);

    private UserDAO userDAO;
    private FavoriteDAO favoriteDAO;

    public LoginAction(Model model) {
    	System.out.println("[LoginAction][Begin] Constructor");
        userDAO = model.getUserDAO();
        favoriteDAO = model.getFavoriteDAO();
        System.out.println("[LoginAction][End] Constructor");
    }

    public String getName() {
    	System.out.println("[LoginAction][Begin] getName");
        System.out.println("[LoginAction][End] getName");
        return "login.do";
    }

    public String perform(HttpServletRequest request) {
    	System.out.println("[LoginAction][Begin] perform");
    	
        HttpSession session = request.getSession();

        // If user is already logged in, redirect to todolist.do
        if (session.getAttribute("user") != null) {
        	System.out.println("[LoginAction][End] perform USER NOT NULL");
            return "manage.do";
        }

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            LoginForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);
            request.setAttribute("userList", userDAO.getUserList());
            
            System.out.println(11111);
            
            System.out.println(form.getAction());
            System.out.println(form.isPresent());

            // If no params were passed, return with no errors so that the form
            // will be
            // presented (we assume for the first time).
            if (!form.isPresent()) {
            	System.out.println("[LoginAction][End] perform form NOT PRESENT");
                return "login.jsp";
            }
            System.out.println(22222);

            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
            	System.out.println("[LoginAction][End] perform contain errors");
                return "login.jsp";
            }
            System.out.println(33333);

            // Look up the user
            UserBean user = null;
            
            // If need to register
            if (form.getAction() != null && form.getAction().equals("Register")) {
            	System.out.println("[LoginAction][End] perform Register");
            	return "register.do";
            }
            System.out.println(44444);
            
            user = userDAO.read(form.getEmailAddress());

            if (user == null) {
                errors.add("No such user");
                System.out.println("[LoginAction][End] perform No such user");
                return "login.jsp";
            }
            System.out.println(55555);

            // Check the password
            if (!user.getPassword().equals(form.getPassword())) {
                errors.add("Incorrect password");
                System.out.println("[LoginAction][End] perform WRONG PASSWORD");
                return "login.jsp";
            }
            System.out.println(66666);

            // Attach (this copy of) the user bean to the session
            session.setAttribute("user", user);
            
            FavoriteBean[] favoriteList = favoriteDAO.getUserFavorites(user.getUserId());
            request.setAttribute("favoriteList",favoriteList);
            
            System.out.println("[LoginAction][End] perform FINISHED");

            // If redirectTo is null, redirect to the "todolist" action
            return "manage.do";
        } catch (Exception e) {
            errors.add(e.getMessage());
            e.printStackTrace();
            return "error.jsp";
        }
    }
}
