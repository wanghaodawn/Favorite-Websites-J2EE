/**
 * This is the HW4 of 08672
 * Action
 * @author Hao Wang (haow2)
 * 10/12/2016
 * */
package controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

public abstract class Action {
    /**
     * Returns the name of the action, used to match the request in the map
     * @return the name of the action
     */
    public abstract String getName();

    /**
     * Returns the name of the view (usually a jsp) used to render the output.
     * @param request the request
     * @return the name of the view
     */
    public abstract String perform(HttpServletRequest request);

    /**
     * Class methods to manage dispatching to Actions
     */
    private static Map<String, Action> hash = new HashMap<String, Action>();

    /**
     * Adds an action to the map.
     * @param a the action to be added to the map.
     */
    public static void add(Action a) {
    	System.out.println("[Action][Begin] add");
    	System.out.println("Action name: " + a.getName());
        synchronized (hash) {
            if (hash.get(a.getName()) != null) {
                throw new AssertionError("Two actions with the same name ("
                        + a.getName() + "): " + a.getClass().getName()
                        + " and " + hash.get(a.getName()).getClass().getName());
            }

            hash.put(a.getName(), a);
        }
        System.out.println("[Action][End] add");
    }

    /**
     * Looks the action up in the map and then executes it.
     * @param name the name of the action
     * @param request the request to process
     * @return the name of the view
     */
    public static String perform(String name, HttpServletRequest request) {
    	System.out.println("[Action][Begin] perform");
    	
        Action a;
        synchronized (hash) {
            a = hash.get(name);
        }

        if (a == null) {
            return null;
        }
        
        System.out.println("[Action][End] perform");
        return a.perform(request);
    }
}
