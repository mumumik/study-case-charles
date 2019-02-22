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
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;

@WebServlet("/test/")
public class TestServlet extends AbstractController
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
			PrintWriter out = resp.getWriter();
            UserDao userDao = UserDaoImpl.getInstance();
            List<User> users = userDao.findAll();
            for(User user : users) {
            	out.println(user.getId());
            	out.println(user.getUserName());
            	out.println(user.getPassword());
            }
    }
}
