<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Ensure you have JSTL support -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pending Requests</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .request {
            border: 1px solid #ccc;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            background-color: #fafafa;
        }
        .request p {
            margin: 5px 0;
        }
        .actions {
            display: flex;
            justify-content: space-between;
        }
        input[type="submit"] {
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .approve {
            background-color: #4CAF50;
            color: white;
        }
        .reject {
            background-color: #f44336;
            color: white;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Pending Requests</h1>
        <c:if test="${not empty requestList}">
            <c:forEach var="request" items="${requestList}">
                <div class="request">
                    <p><strong>User:</strong> ${request.username}</p>
                    <p><strong>Access Type:</strong> ${request.access_type}</p>
                    <p><strong>Software:</strong> ${request.software_name}</p>
                    <form action="ApprovalServlet" method="post" class="actions">
                        <input type="hidden" name="request_id" value="${request.id}">
                        <input type="submit" name="action" value="Approve" class="approve">
                        <input type="submit" name="action" value="Reject" class="reject">
                    </form>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${empty requestList}">
            <p>No pending requests at the moment.</p>
        </c:if>
    </div>
</body>
</html>
