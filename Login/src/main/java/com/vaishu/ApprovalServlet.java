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
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ApprovalServlet
 */
@WebServlet("/ApprovalServlet")
public class ApprovalServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestId = request.getParameter("request_id");
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
    
        try {
            // Load PostgreSQL driver
            Class.forName("org.postgresql.Driver");
            // Establish connection to the database
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vaishu", "postgres", "Vaishu@222");

            // Determine the status based on the action
            String status = action.equals("Approve") ? "Approved" : "Rejected";
            String sql = "UPDATE requests SET status=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, Integer.parseInt(requestId));
            stmt.executeUpdate();

            // Fetch the updated list of pending requests
            String requestsSql = "SELECT r.id, u.username, r.access_type, s.name AS software_name " +
                    "FROM requests r " +
                    "JOIN users u ON r.user_id = u.id " + 
                    "JOIN software s ON r.software_id = s.id " +
                    "WHERE r.status='Pending'";
            PreparedStatement requestsStmt = conn.prepareStatement(requestsSql);
            ResultSet requestsRs = requestsStmt.executeQuery();

            List<Request> requestList = new ArrayList<>();
            while (requestsRs.next()) {
            	String username = requestsRs.getString("username");
                // Create a new Request object using the constructor
                Request req = new Request(
                    requestsRs.getInt("id")+"",
                    requestsRs.getString("username"),
                    requestsRs.getString("access_type"),
                    requestsRs.getString("software_name")
                );
                requestList.add(req);
            }

            // Set the updated request list as a request attribute
            request.setAttribute("requestList", requestList);
            request.setAttribute("successMessage", "Request " + status); // Add success message if needed
            
            // Forward the request to the JSP
            request.getRequestDispatcher("pendingRequests.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions gracefully, possibly redirect to an error page
        }
    }
}
