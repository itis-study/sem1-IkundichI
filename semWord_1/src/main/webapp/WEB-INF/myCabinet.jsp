<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>User Cabinet</title>
  <style>

    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
    }

    h1, h2 {
      color: #333;
    }

    .welcome-box {
      background-color: #fff;
      border: 1px solid #ddd;
      padding: 20px;
      margin: 20px;
      border-radius: 5px;
    }

    .welcome-box p {
      margin: 5px 0;
    }

    .home-button {
      position: absolute;
      top: 10px;
      right: 10px;
      font-size: 20px;
      text-decoration: none;
      color: #333;
    }

    .home-icon {
      font-size: 24px;
      margin-right: 5px;
    }
  </style>
</head>
<body>
<a href="/java_semestWork_1" class="home-button">
  <span class="home-icon">🏠</span> Home
</a>
<h1>User Cabinet</h1>

<c:if test="${role == 'teacher' || role == 'admin'}">
  <div class="welcome-box">
    <h2>Welcome, ${teacher1.name}!</h2>
    <p>Name: ${teacher1.name}</p>
    <p>Surname: ${teacher1.surname}</p>
    <p>Mail: ${teacher1.mail}</p>
    <p>Info of Education: ${teacher1.infoOfEducation}</p>
  </div>
</c:if>

<c:if test="${role == 'student'}">
  <div class="welcome-box">
    <h2>Welcome, ${student1.name}!</h2>
    <p>Name: ${student1.name}</p>
    <p>Surname: ${student1.surname}</p>
    <p>Mail: ${student1.mail}</p>
    <p>Class Name: ${student1.className}</p>
  </div>
</c:if>
</body>
</html>
