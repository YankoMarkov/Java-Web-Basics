package org.softuni.fdmc.servlets;

import org.softuni.fdmc.data.Cat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cats/create")
public class CreateCat extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("create.jsp").forward(req, resp);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String breed = req.getParameter("breed");
		String color = req.getParameter("color");
		Integer numberOfLegs = Integer.valueOf(req.getParameter("numberOfLegs"));
		
		Cat cat = new Cat(name, breed, color, numberOfLegs);
		
		((List<Cat>) this.getServletContext().getAttribute("cats")).add(cat);
		
		resp.sendRedirect("/cats/profile?name=" + cat.getName());
	}
}
