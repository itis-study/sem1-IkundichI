<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Update Teacher</title>
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
      font-weight: bold;
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
<h1>Update Teacher</h1>

<c:set var="teacher" value="${requestScope.teacher}" />

<form method="post">
  <input type="hidden" name="id" value="${teacher.id}">
  <label for="name">Name:</label>
  <input type="text" name="name" id="name" value="${teacher.name}" required>
  <br>
  <label for="surname">Surname:</label>
  <input type="text" name="surname" id="surname" value="${teacher.surname}" required>
  <br>
  <label for="mail">Mail:</label>
  <input type="text" name="mail" id="mail" value="${teacher.mail}" required>
  <br>
  <label for="infoOfEducation">Info of Education:</label>
  <input type="text" name="infoOfEducation" id="infoOfEducation" value="${teacher.infoOfEducation}" required>
  <br>
  <label for="password">Password:</label>
  <input type="password" name="password" id="password" value="" required>
  <br>
  <input type="submit" value="Update">
</form>

</body>
</html>
