package com.mitrais.rsmcharles.controller;

import com.mitrais.rmscharles.dao.UserDao;
import com.mitrais.rmscharles.dao.impl.UserDaoImpl;
import com.mitrais.rmscharles.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/users/*")
public class UserServlet extends AbstractController
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	try {
            switch(request.getPathInfo()) {
            case "/list":
            	listUsers(request, response);
                break;
            case "/save":
            	saveUser(request, response);
                break;
            case "/form":
            	addUser(request, response);
            	break;
            case "/delete":
            	deleteUser(request, response);
            	break;
            case "/load":
            	loadUser(request, response);
            	break;
            case "/update":
            	updateUser(request, response);
            	break;
            default:
            	listUsers(request, response);
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private void updateUser(HttpServletRequest request, HttpServletResponse response) 
    throws Exception{
    	
    	//read user info from form data
    	long id = Long.parseLong(request.getParameter("userId"));
    	String username = request.getParameter("username");
    	String userpass = request.getParameter("userpass");
    	
    	//create a new user object
    	User theUser = new User(id, username, userpass);
    		
    	//perform update on database
    	UserDao userDao = UserDaoImpl.getInstance();
    	userDao.update(theUser);
    			
    	//send back to main page (user list)
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/users/list");
    	dispatcher.forward(request, response);
		
	}

	private void loadUser(HttpServletRequest request, HttpServletResponse response) 
    	throws Exception{
    	
    	String path = getTemplatePath(request.getServletPath()+request.getPathInfo());
    	
    	// read user info from form data
    	long userId = Long.parseLong(request.getParameter("userId"));
    	
    	//find user from database based on id
    	UserDao userDao = UserDaoImpl.getInstance();
    	Optional<User> user = userDao.find(userId);
    	User theUser = user.get();
		
    	request.setAttribute("the_user", theUser);
    	
    	// send jsp page: load.jsp
    	RequestDispatcher dispatcher = 
    						request.getRequestDispatcher(path);
    	dispatcher.forward(request,response);
    	
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
    	throws Exception{
    	
    	//read user info from form data
    	long userId = Long.parseLong(request.getParameter("userId"));
    	
    	//delete user from database
    	UserDao userDao = UserDaoImpl.getInstance();
    	userDao.delete(userId);
    	
    	// send to JSP Page (view)
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/users/list");
    	dispatcher.forward(request, response);
    	
	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception{
    	String path = getTemplatePath(request.getServletPath()+request.getPathInfo());
    	
    	UserDao userDao = UserDaoImpl.getInstance();
        List<User> users = userDao.findAll();
        request.setAttribute("users", users);
    		
    	// send to JSP Page (view)
    	RequestDispatcher dispatcher = request.getRequestDispatcher(path);
    	dispatcher.forward(request, response);
    }
    
    private void saveUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read user info from form data
		String username = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		
		//create a new user object
		User theUser = new User(username,userpass);
		
		//add the user to the database
		UserDao userDao = UserDaoImpl.getInstance();
		userDao.save(theUser);
		
		//send back to main page (user list)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/users/list");
		dispatcher.forward(request, response);
		
	}
    
    private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String path = getTemplatePath(request.getServletPath()+request.getPathInfo());
    	RequestDispatcher dispatcher = request.getRequestDispatcher(path);
    	dispatcher.forward(request, response);
	}

}
