<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Create Student</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f5f5f5;
      margin: 0;
      padding: 0;
    }

    h1 {
      background-color: #333;
      color: white;
      padding: 10px;
      text-align: center;
    }

    form {
      max-width: 400px;
      margin: 0 auto;
      padding: 20px;
      background-color: white;
      border-radius: 5px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
    }

    label {
      display: block;
      margin-bottom: 10px;
    }

    input[type="text"],
    input[type="password"] {
      width: 100%;
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }

    input[type="submit"] {
      background-color: #4CAF50;
      color: white;
      border: none;
      padding: 10px 20px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 14px;
      border-radius: 5px;
      cursor: pointer;
    }

    input[type="submit"]:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>
<h1>Create Student</h1>
<form method="post" action="createStudent">
  <label for="name">Name:</label>
  <input type="text" id="name" name="name" required><br>

  <label for="surname">Surname:</label>
  <input type="text" id="surname" name="surname" required><br>

  <label for="className">Class Name:</label>
  <input type="text" id="className" name="className" required><br>

  <label for="mail">Mail:</label>
  <input type="text" id="mail" name="mail" required><br>

  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required><br>

  <input type="submit" value="Create Student">
</form>
</body>
</html>
