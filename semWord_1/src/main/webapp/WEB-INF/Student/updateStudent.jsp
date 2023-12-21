<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Update Student</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background-color: #f5f5f5;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .container {
      width: 100%;
      max-width: 500px;
    }

    h1 {
      background-color: #4a69bd;
      color: white;
      padding: 20px;
      border-radius: 5px;
      text-align: center;
      margin-bottom: 20px;
    }

    form {
      background-color: white;
      border-radius: 8px;
      padding: 25px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    .form-group {
      margin-bottom: 15px;
    }

    label {
      display: block;
      margin-bottom: 5px;
      font-weight: 600;
      color: #333;
    }

    input[type="text"],
    input[type="password"] {
      width: calc(100% - 20px);
      padding: 10px;
      margin-bottom: 10px;
      border: 1px solid #ced4da;
      border-radius: 4px;
      box-sizing: border-box;
    }

    input[type="submit"] {
      background-color: #78e08f;
      color: white;
      border: none;
      padding: 10px 20px;
      text-align: center;
      text-decoration: none;
      display: block;
      width: 100%;
      font-size: 16px;
      border-radius: 4px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    input[type="submit"]:hover {
      background-color: #60a671;
    }
  </style>
</head>
<body>

<div class="container">
  <h1>Update Student</h1>

  <c:set var="student" value="${requestScope.student}" />

  <form method="post">
    <input type="hidden" name="id" value="<c:out value="${student.id}" />">

    <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" name="name" id="name" value="<c:out value="${student.name}" />" required>
    </div>

    <div class="form-group">
      <label for="surname">Surname:</label>
      <input type="text" name="surname" id="surname" value="<c:out value="${student.surname}" />" required>
    </div>

    <div class="form-group">
      <label for="className">Class:</label>
      <input type="text" name="className" id="className" value="<c:out value="${student.className}" />" required>
    </div>

    <div class="form-group">
      <label for="mail">Mail:</label>
      <input type="text" name="mail" id="mail" value="<c:out value="${student.mail}" />" required>
    </div>

    <div class="form-group">
      <label for="password">Password:</label>
      <input type="password" name="password" id="password" value="" required>
    </div>

    <input type="submit" value="Update">
  </form>
</div>

</body>
</html>
