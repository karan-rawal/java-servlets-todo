package com.karan.rawal.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = -3523921306265069103L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try(PrintWriter p = res.getWriter()) {
			p.println("Hello From User Controller");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
