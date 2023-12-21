<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Homework Information</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
    }

    h1 {
      color: #333;
      text-align: center;
      margin-bottom: 20px;
    }

    form {
      text-align: center;
      margin-bottom: 20px;
    }

    label {
      font-weight: bold;
    }

    select {
      padding: 10px;
      margin: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
      width: 100%;
    }

    input[type="submit"] {
      background-color: #007BFF;
      color: #fff;
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }

    table {
      width: 80%;
      margin: 0 auto;
      border-collapse: collapse;
      background-color: #fff;
    }

    table, th, td {
      border: 1px solid #ddd;
    }

    th, td {
      padding: 15px;
      text-align: left;
    }

    th {
      background-color: #007BFF;
      color: #fff;
      font-weight: bold;
    }

    tr:nth-child(even) {
      background-color: #f2f2f2;
    }

    tr:hover {
      background-color: #ddd;
    }

    .button {
      background-color: #007BFF;
      color: #fff;
      padding: 5px 10px;
      border: none;
      border-radius: 3px;
      text-decoration: none;
      cursor: pointer;
    }

    .button.delete {
      background-color: #ff3333;
    }

    .button.delete:hover {
      background-color: #ff0000;
    }
  </style>
</head>
<body>
<h1>Homework Information</h1>

<form method="post">
  <label for="className">Select Class:</label>
  <select name="className" id="className" required>
    <option value="">Select Class</option>
    <c:forEach items="${classNames}" var="className">
      <option value="${className}">${className}</option>
    </c:forEach>
  </select>

  <label for="subject">Select Subject:</label>
  <select name="subject" id="subject" required>
    <option value="">Select Subject</option>
    <c:forEach items="${subjectList}" var="subject">
      <option value="${subject.id}">${subject.name}</option>
    </c:forEach>
  </select>

  <input type="submit" value="Show Homework">
</form>
<c:if test="${not empty homeworkInfoList}">
  <table>
    <thead>
    <tr>
      <th>Date</th>
      <th>Homework</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${homeworkInfoList}" var="homework">
      <tr>
        <td>${homework.dateOfHomework}</td>
        <td>${homework.description}</td>
        <td>
          <a href="#" class="button delete" onclick="confirmDelete(${homework.id})">Delete</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</c:if>

<script>
  function confirmDelete(hwId) {
    if (confirm("Are you sure you want to delete this homework?")) {
      var deleteUrl = "deleteHomework?id=" + hwId;
      window.location.href = deleteUrl;
    }
  }
</script>

</body>
</html>
