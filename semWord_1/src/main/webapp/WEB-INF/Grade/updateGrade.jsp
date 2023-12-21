<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Update Grade</title>
  <style>

    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
    }

    h1 {
      background-color: #333;
      color: white;
      padding: 10px;
      margin: 0;
    }

    form {
      background-color: #fff;
      padding: 20px;
      border: 1px solid #ccc;
      margin-top: 20px;
      max-width: 800px;
      margin: 0 auto;
    }

    label {
      font-weight: bold;
      display: block;
      margin-bottom: 5px;
    }

    input[type="number"], input[type="date"] {
      margin-bottom: 10px;
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    input[type="submit"] {
      background-color: #333;
      color: white;
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }

    input[type="submit"]:hover {
      background-color: #555;
    }
  </style>
</head>
<body>
<h1>Update Grade</h1>

<form method="post">
  <label for="newGrade">New Grade (1-5):</label>
  <input type="number" name="newGrade" id="newGrade" required min="1" max="5">
  <label for="newDate">New Date:</label>
  <input type="date" name="newDate" id="newDate" required>
  <input type="hidden" name="id" value="${grade.id}">
  <input type="submit" value="Update Grade">
</form>

</body>
</html>
