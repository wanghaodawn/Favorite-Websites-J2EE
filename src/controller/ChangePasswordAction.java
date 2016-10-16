/**
 * This is the HW4 of 08672
 * ChangePasswordAction
 * @author Hao Wang (haow2)
 * 10/12/2016
 * */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import databean.UserBean;
import formbean.ChangePasswordForm;
import model.Model;
import model.UserDAO;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class ChangePasswordAction extends Action {
	private FormBeanFactory<ChangePasswordForm> formBeanFactory = FormBeanFactory.getInstance(ChangePasswordForm.class);
	private UserDAO userDAO;

    public ChangePasswordAction(Model model) {
    	userDAO = model.getUserDAO();
    }

    public String getName() {
        return "changepassword.do";
    }

    public String perform(HttpServletRequest request) {
    	System.out.println("[ChangePasswordAction][Begin] perform");
    	
        HttpSession session = request.getSession();

        // If user hasn't logged in, go back to login
        if (session.getAttribute("user") == null) {
        	System.out.println("[ChangePasswordAction][End] perform USER NULL");
            return "login.do";
        }

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            ChangePasswordForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);
            request.setAttribute("userList",userDAO.getUserList());
            
            System.out.println(11111);
            
            System.out.println(form.isPresent());

            // If no params were passed, return with no errors so that the form
            // will be presented (we assume for the first time).
            if (!form.isPresent()) {
            	System.out.println("[ChangePasswordAction][End] perform form NOT PRESENT");
                return "changepassword.jsp";
            }
            System.out.println(22222);

            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
            	System.out.println("[ChangePasswordAction][End] perform contain errors");
                return "changepassword.jsp";
            }
            System.out.println(33333);

            // Get the user from session
            UserBean user = (UserBean) request.getSession().getAttribute("user");

            System.out.println(44444);
            
            userDAO.changePassword(user.getEmailAddress(),form.getPassword1());

            System.out.println(55555);
            
            System.out.println("[ChangePasswordAction][End] perform FINISHED");

            // If redirectTo is null, redirect to the "todolist" action
            return "manage.do";
        } catch (Exception e) {
            errors.add(e.getMessage());
            e.printStackTrace();
            return "error.jsp";
        }
    }
}
