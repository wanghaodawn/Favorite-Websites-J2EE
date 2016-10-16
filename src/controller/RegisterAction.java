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
import formbean.RegisterForm;
import model.FavoriteDAO;
import model.Model;
import model.UserDAO;

public class RegisterAction extends Action {
    private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);

    private UserDAO userDAO;
    private FavoriteDAO favoriteDAO;

    public RegisterAction(Model model) {
    	System.out.println("[RegisterAction][Begin] Constructor");
        userDAO = model.getUserDAO();
        favoriteDAO = model.getFavoriteDAO();
        System.out.println("[RegisterAction][End] Constructor");
    }

    public String getName() {
    	System.out.println("[RegisterAction][Begin] getName");
    	System.out.println("[RegisterAction][End] getName");
        return "register.do";
    }

    public String perform(HttpServletRequest request) {
    	System.out.println("[RegisterAction][Begin] perform");
    	
    	HttpSession session = request.getSession();

        // If user is already logged in, redirect to todolist.do
        if (session.getAttribute("user") != null) {
        	System.out.println("[RegisterAction][End] perform USER NOT NULL");
            return "manage.do";
        }

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
//        	System.out.println(11111);
            RegisterForm form = formBeanFactory.create(request);
            System.out.println(form.isPresent());
            request.setAttribute("form", form);
            request.setAttribute("userList", userDAO.getUserList());
            
            System.out.println(userDAO.getUserList().get(0).getEmailAddress());
            
//            System.out.println(form);
//            System.out.println(22222);
//            System.out.println(form.getAction());
//            System.out.println(form.isPresent());
        
//            System.out.println(33333);
            
            // If no params were passed, return with no errors so that the form
            // will be presented (we assume for the first time).
            if (!form.isPresent()) {
            	System.out.println("[RegisterAction][End] perform NOT PRESENT");
                return "register.jsp";
            }
            System.out.println(44444);
            // Any validation errors?
            
            errors.addAll(form.getValidationErrors());
            System.out.println("Errors" + errors);

            if (errors.size() != 0) {
            	System.out.println("[RegisterAction][End] perform errors NOT NULL");
                return "register.jsp";
            }
            System.out.println(55555);
            // Look up the user
            UserBean newUser = null;
            
            // If need to login
            if (form.getAction() != null && form.getAction().equals("Login")) {
            	System.out.println("[RegisterAction][End] perform Login");
            	return "login.do";
            }
            System.out.println(66666);
            
            newUser = new UserBean();
            newUser.setEmailAddress(form.getEmailAddress());
            newUser.setFirstName(form.getFirstName());
            newUser.setLastName(form.getLastName());
            newUser.setPassword(form.getPassword());
            
            System.out.println(77777);
            try {
                userDAO.create(newUser);
                
                // Prevent invalid userId
                newUser = userDAO.read(form.getEmailAddress());
                
                session.setAttribute("user", newUser);
                
                FavoriteBean[] favoriteList = favoriteDAO.getUserFavorites(newUser.getUserId());
                request.setAttribute("favoriteList",favoriteList);
                
                System.out.println("[RegisterAction][Begin] perform FINISHED");
                return ("manage.do");
            } catch (Exception e) {
                errors.add("A user with this name already exists");
                System.out.println("[RegisterAction][Begin] perform USER ALREADY EXISTS");
                e.printStackTrace();
                return "manage.jsp";
            }
        } catch (Exception e) {
            errors.add(e.getMessage());
            System.out.println("[RegisterAction][Begin] perform EXCEPTION ERROR");
            e.printStackTrace();
            return "error.jsp";
        }
    }
}