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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vaishu", "postgres", "Vaishu@222");

            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                String role = rs.getString("role");
                session.setAttribute("role", role);

                switch (role) {
                    case "Employee":
                        
                        String softwareSql = "SELECT * FROM software"; 
                        PreparedStatement softwareStmt = conn.prepareStatement(softwareSql);
                        ResultSet softwareRs = softwareStmt.executeQuery();

                        List<Software> softwareList = new ArrayList<>();
                        while (softwareRs.next()) {
                            String softwareId = softwareRs.getString("id"); 
                            String softwareName = softwareRs.getString("name"); 
                            softwareList.add(new Software(softwareId, softwareName));
                        }

                        request.setAttribute("softwareList", softwareList); // Set the software list in request scope
                        System.out.println("Reached here");
                        request.getRequestDispatcher("requestAccess.jsp").forward(request, response); // Forward to JSP
                        break;
                    case "Manager":
                    	  String requestsSql = "SELECT r.id, u.username, r.access_type, s.name as software_name " +
                                  "FROM requests r " +
                                  "JOIN users u ON r.user_id = u.id " + // Assuming a relationship exists
                                  "JOIN software s ON r.software_id = s.id " + // Assuming a relationship exists
                                  "WHERE r.status='Pending'";
             PreparedStatement requestsStmt = conn.prepareStatement(requestsSql);
             ResultSet requestsRs = requestsStmt.executeQuery();

             List<Request> requestList = new ArrayList<>();
             while (requestsRs.next()) {
                 String id = requestsRs.getString("id");
                 String reqUsername = requestsRs.getString("username");
                 String accessType = requestsRs.getString("access_type");
                 String softwareName = requestsRs.getString("software_name");
                 requestList.add(new Request(id, reqUsername, accessType, softwareName));
             }

             request.setAttribute("requestList", requestList); // Set the request list in request scope
             request.getRequestDispatcher("pendingRequests.jsp").forward(request, response); // Forward to JSP
             break;
                    case "Admin":
                        // Redirect to create software page
                        response.sendRedirect("createSoftware.jsp");
                        break;
                }
            } else {
                response.sendRedirect("login.jsp?error=Invalid Credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=Server Error");
        }
    }
}
