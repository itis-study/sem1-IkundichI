<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Grade Page</title>
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

    .nav-list {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
    }

    .section-header {
      color: blue;
      margin-top: 20px;
    }

    ul {
      list-style-type: none;
      padding: 0;
    }

    ul li {
      margin: 10px 0;
    }

    .nav-button {
      display: block;
      width: 100%;
      text-align: center;
      background-color: #4CAF50;
      color: white;
      padding: 10px 20px;
      text-decoration: none;
      border: none;
      border-radius: 5px;
      margin-top: 10px;
    }

    .nav-button:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>
<h1>Welcome to Homework Page</h1>
<div class="nav-list">

  <h2 class="section-header">Homework:</h2>
  <ul>
    <li>
      <c:url var="createHomeworkUrl" value="/teacher/createHomework"/>
      <a href="${createHomeworkUrl}" class="nav-button">Задать домашнее задание по предмету</a>
    </li>
    <li>
      <c:url var="getHomeworkUrl" value="/teacher/getHomework"/>
      <a href="${getHomeworkUrl}" class="nav-button">Посмотреть домашние задания по предмету</a>
    </li>
  </ul>

</div>
</body>
</html>
