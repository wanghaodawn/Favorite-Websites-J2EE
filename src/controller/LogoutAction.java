/**
 * This is the HW4 of 08672
 * LogoutAction
 * @author Hao Wang (haow2)
 * 10/12/2016
 * */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.UserDAO;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogoutAction extends Action {
	private UserDAO userDAO;

    public LogoutAction(Model model) {
    	userDAO = model.getUserDAO();
    }

    public String getName() {
        return "logout.do";
    }

    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.setAttribute("user", null);
        
        request.setAttribute("userList",userDAO.getUserList());

        return "login.jsp";
    }
}
