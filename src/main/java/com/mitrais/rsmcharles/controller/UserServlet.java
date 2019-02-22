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

@WebServlet("/users/*")
public class UserServlet extends AbstractController
{
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
            }
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
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
		
		//add the song to the database
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
