<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Software</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #ffffff;
            border: 1px solid #ddd;
            padding: 20px 40px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            max-width: 500px;
            width: 100%;
        }
        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            font-size: 14px;
            color: #555;
            margin-bottom: 5px;
            display: block;
        }
        input[type="text"], textarea, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            border: none;
            color: white;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .checkbox-group {
            margin: 15px 0;
        }
        .checkbox-group label {
            display: inline-block;
            margin-right: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Create Software</h2>
        <form action="SoftwareServlet" method="post">
            <label for="name">Software Name</label>
            <input type="text" id="name" name="name" required>

            <label for="description">Description</label>
            <textarea id="description" name="description" rows="4" required></textarea>

            <label for="access_levels">Access Levels</label>
            <select id="access_levels" name="access_levels">
                <option value="Read">Read</option>
                <option value="Write">Write</option>
                <option value="Admin">Admin</option>
            </select>

            <div class="checkbox-group">
                <label><input type="checkbox" name="software_options" value="Option 1"> Option 1</label>
                <label><input type="checkbox" name="software_options" value="Option 2"> Option 2</label>
                <label><input type="checkbox" name="software_options" value="Option 3"> Option 3</label>
                <label><input type="checkbox" name="software_options" value="Option 4"> Option 4</label>
                <label><input type="checkbox" name="software_options" value="Option 5"> Option 5</label>
            </div>

            <input type="submit" value="Create Software">
        </form>
    </div>
</body>
</html>
