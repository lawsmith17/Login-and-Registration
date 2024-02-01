package com.mphande.registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//retrieving user in from jsp page
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String upwd = request.getParameter("pass");
		String umobile = request.getParameter("contact");
		String usurname = request.getParameter("surname");
		String uaddress = request.getParameter("address");
		String usertype = "2";
		RequestDispatcher dispatcher = null;
		Connection con = null;
		
		try
		{
			
			// registering mySQl driver and creating a connection to the database 
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinebookstore?useSSL=false","root","1998");
			PreparedStatement pst = con.prepareStatement("insert into users(username,password,firstname,lastname,address,phone,mailid,usertype) values(?,?,?,?,?,?,?,?)");
			pst.setString(1, uname);
			pst.setString(2, upwd);
			pst.setString(3, uname);
			pst.setString(4, usurname);
			pst.setString(5, uaddress);
			pst.setString(6, umobile);
			pst.setString(7, uemail);
			pst.setString(8, usertype);
			
			
			int rowCount = pst.executeUpdate();
			dispatcher = request.getRequestDispatcher("registration.jsp");
			
			if(rowCount > 0)
			{
			request.setAttribute("status", "success");
			
			}else
			{
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request, response);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}


/*String uname = request.getParameter("name");
String uemail = request.getParameter("email");
String upwd = request.getParameter("pass");
String umobile = request.getParameter("contact");

RequestDispatcher dispatcher = null;
Connection con = null;

try
{
	Class.forName("com.mysql.cj.jdbc.Driver");
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinebookstore?useSSL=false","root","1998");
	PreparedStatement pst = con.prepareStatement("insert into users(uname,upwd,uemail,umobile) values(?,?,?,?)");
	pst.setString(1, uname);
	pst.setString(2, upwd);
	pst.setString(3, uemail);
	pst.setString(4, umobile);
	
	int rowCount = pst.executeUpdate();
	dispatcher = request.getRequestDispatcher("registration.jsp");
	
	if(rowCount > 0)
	{
	request.setAttribute("status", "success");
	
	}else
	{
		request.setAttribute("status", "failed");
	}
	dispatcher.forward(request, response);
}catch(Exception e)
{
	e.printStackTrace();
}finally
{
	try {
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}*/
