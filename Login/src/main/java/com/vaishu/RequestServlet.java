package com.vaishu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class RequestServlet
 */
@WebServlet("/RequestServlet")
public class RequestServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
	        String username = (String) session.getAttribute("username");
	        String softwareId = request.getParameter("software");
	        String accessType = request.getParameter("access_type");
	        String reason = request.getParameter("reason");
	        
	       

	        try {
	            Class.forName("org.postgresql.Driver");
	            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vaishu", "postgres", "Vaishu@222");

	            String userQuery = "SELECT id FROM users WHERE username=?";
	            PreparedStatement userStmt = conn.prepareStatement(userQuery);
	            userStmt.setString(1, username);
	            ResultSet userRs = userStmt.executeQuery();
	            userRs.next();
	            int userId = userRs.getInt("id");

	            String sql = "INSERT INTO requests (user_id, software_id, access_type, reason) VALUES (?, ?, ?, ?)";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, userId);
	            stmt.setInt(2, Integer.parseInt(softwareId));
	            stmt.setString(3, accessType);
	            stmt.setString(4, reason);
	            stmt.executeUpdate();

	            response.sendRedirect("requestAccess.jsp?success=Request Submitted");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
