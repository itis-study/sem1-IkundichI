<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Update Subject</title>
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
      font-weight: bold;
    }

    select,
    input[type="text"] {
      width: 100%;
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }

    input[type="submit"] {
      background-color: #4caf50;
      color: white;
      border: none;
      border-radius: 4px;
      padding: 10px 20px;
      cursor: pointer;
    }
  </style>
</head>
<body>
<h1>Update Subject</h1>

<form method="post">
  <c:set var="subject" value="${subject}" />
  <input type="hidden" name="id" value="${subject.id}">
  <label for="name">Subject Name:</label>
  <input type="text" name="name" id="name" value="${subject.name}" required>
  <label for="teacherId">Select Teacher:</label>
  <select name="teacherId" id="teacherId">
    <option value="">Select Teacher</option>
    <c:set var="currentTeacherId" value="${subject.teacherId}" />
    <c:forEach items="${teachers}" var="teacher">
      <option value="${teacher.id}" <c:if test="${teacher.id eq currentTeacherId}">selected</c:if>>${teacher.name} ${teacher.surname}</option>
    </c:forEach>
  </select>
  <input type="submit" value="Update Subject">
</form>
</body>
</html>
