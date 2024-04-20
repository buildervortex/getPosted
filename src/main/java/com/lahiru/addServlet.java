package com.lahiru;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.PrintWriter;
import java.io.IOException;

public class addServlet extends HttpServlet{

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
	
		int number1 = Integer.parseInt(request.getParameter("number1"));
		int number2 = Integer.parseInt(request.getParameter("number2"));

		// sum
		int sum = number1 + number2;

		// write to the response
		PrintWriter writer = response.getWriter();

		// write the result
		writer.println("The Result is = "+sum);
	}

}
