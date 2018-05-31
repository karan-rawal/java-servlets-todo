package com.karan.rawal.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.karan.rawal.models.User;
import com.karan.rawal.services.UserService;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = -3523921306265069103L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String email = req.getParameter("email");
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String password = req.getParameter("password");
		
		PrintWriter p = res.getWriter();
		try{
			UserService.getInstance().createUser(new User(id, fname, lname, email, password));
			p.println(User.USER_CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			p.println(e.getMessage());
		}
		
		p.close();
	}
		
}
