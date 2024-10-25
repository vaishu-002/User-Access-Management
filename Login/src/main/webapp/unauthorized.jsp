<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0); 
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Unauthorized Access</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
        h1 {
            color: #f44336; /* Red color */
        }
    </style>
</head>
<body>
    <h1>Unauthorized Access</h1>
    <p>You do not have permission to access this page.</p>
    <a href="index.jsp">Go to Signup</a>
</body>
</html>
