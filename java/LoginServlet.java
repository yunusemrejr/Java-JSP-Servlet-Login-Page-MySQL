 

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
			    PrintWriter out = response.getWriter();
				Class.forName("com.mysql.cj.jdbc.Driver");
                 Connection con = DriverManager.getConnection("localhost","root","");
		        String n = request.getParameter("txtName");
		        String p = request.getParameter("txtPwd");
		        PreparedStatement ps = con.prepareStatement("SELECT uname from login WHERE uname=? and password=?");
		        ps.setString(1, n);
		        ps.setString(2, p);
		        ResultSet rs=ps.executeQuery();
		        if(rs.next()) {
		        	RequestDispatcher rd= request.getRequestDispatcher("welcome.jsp");
		        	rd.forward(request,response);
		        }else {
		        	out.println("<font color=red size=18>Failed<br>");
		        	out.println("<a href=login.jsp>go back</a>");
		        }
		        
		 
		 }catch(ClassNotFoundException e) {
			 e.printStackTrace();
			 
		 }catch(SQLException e) {
			e.printStackTrace(); 
		 };
		
	}

}
