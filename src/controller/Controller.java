/**
 * This is the HW4 of 08672
 * Controller
 * @author Hao Wang (haow2)
 * 10/12/2016
 * */

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databean.UserBean;
import model.Model;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Init stage
	 * */
	public void init() throws ServletException {
		System.out.println("[Controller][Begin] Init");
		
		Model model = new Model(getServletConfig());
		Action.add(new LoginAction(model));
        Action.add(new LogoutAction(model));
        Action.add(new RegisterAction(model));
        Action.add(new DeleteAction(model));
        Action.add(new ManageAction(model));
        Action.add(new ListAction(model));
        Action.add(new ChangePasswordAction(model));
        
        System.out.println("[Controller][End] Init\n");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		System.out.println("[Controller][Begin] doPost");
        doGet(request, response);
        System.out.println("[Controller][End] doPost");
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		System.out.println("[Controller][Begin] doGet");
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage, request, response);
        System.out.println("[Controller][End] doGet\n");
    }
	
	/*
     * Extracts the requested action and (depending on whether the user is
     * logged in) perform it (or make the user login).
     * @param request
     * @return the next page (the view)
     */
    private String performTheAction(HttpServletRequest request) {
    	System.out.println("[Controller][Begin] performTheAction");
    	
        HttpSession session = request.getSession(true);
        String servletPath = request.getServletPath();
        UserBean user = (UserBean) session.getAttribute("user");
        String action = getActionName(servletPath);
        
        System.out.println("Action: " + action);
        
        if (action.equals("list.do")) {
        	System.out.println("[Controller][End] performTheAction list.do");
        	return Action.perform(action, request);
        } else if (user == null && action.equals("register.do")) {
        	System.out.println("[Controller][End] performTheAction user NULL REGISTER");
        	return Action.perform(action, request);
        } else if (user == null) { 
        	System.out.println("[Controller][End] performTheAction user NULL LOGIN");
        	return Action.perform("login.do", request);
        } else {
        	System.out.println("[Controller][End] performTheAction user NOT NULL");
            // If the user hasn't logged in, so login is the only option
            return Action.perform(action, request);
        }
        
//        System.out.println("[Controller][End] performTheAction user NOT NULL");
//        // Let the logged in user run his chosen action
//        return Action.perform(action, request);
    }

    /*
     * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
     * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
     * page (the view) This is the common case
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
    	System.out.println("[Controller][Begin] sendToNextPage");
    	
    	System.out.println("nextPage: " + nextPage);
    	
        if (nextPage == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                    request.getServletPath());
            System.out.println("[Controller][End] sendToNextPage nextPage NULL");
            return;
        }

        if (nextPage.endsWith(".do")) {
            response.sendRedirect(nextPage);
            System.out.println("[Controller][End] nextPage endsWith .do");
            return;
        }

        if (nextPage.endsWith(".jsp")) {
            RequestDispatcher d = request.getRequestDispatcher("WEB-INF/"
                    + nextPage);
            d.forward(request, response);
            System.out.println("[Controller][End] sendToNextPage nextPage endsWith .jsp");
            return;
        }
        
        if (nextPage.startsWith("http") || nextPage.startsWith("HTTP")) {
            response.sendRedirect(nextPage);
            return;
        }
        
        if (!(nextPage.startsWith("http") && nextPage.startsWith("HTTP"))) {
            response.sendRedirect("http://" + nextPage);
            return;
        }

        throw new ServletException(Controller.class.getName()
                + ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }

    /*
     * Returns the path component after the last slash removing any "extension"
     * if present.
     */
    private String getActionName(String path) {
    	System.out.println("[Controller][Begin] getActionName");
    	System.out.println("path: " + path);
        // We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        System.out.println("[Controller][End] getActionName");
        return path.substring(slash + 1);
    }
}
