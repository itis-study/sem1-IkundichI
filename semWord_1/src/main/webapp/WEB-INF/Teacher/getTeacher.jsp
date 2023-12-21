<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Teacher List</title>
  <style>
    body {
      font-family: Arial, sans-serif;
    }

    h1 {
      background-color: #333;
      color: white;
      padding: 10px;
    }

    table {
      border-collapse: collapse;
      width: 100%;
    }

    th, td {
      border: 1px solid #ddd;
      text-align: left;
      padding: 8px;
    }

    tr:nth-child(even) {
      background-color: #f2f2f2;
    }

    a.button {
      background-color: #4CAF50;
      border: none;
      color: white;
      padding: 8px 16px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 14px;
      margin: 4px 2px;
      cursor: pointer;
      border-radius: 4px;
    }

    a.button.delete {
      background-color: #f44336;
    }

    a.button.update {
      background-color: #2196F3;
    }
  </style>
</head>
<body>
<h1>Teacher List</h1>

<%-- Вывод списка учителей --%>
<table>
  <thead>
  <tr>
    <th>Name</th>
    <th>Surname</th>
    <th>Mail</th>
    <th>Bio</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${teacherList}" var="teacher">
    <c:set var="deleteUrl" value="/java_semestWork_1/admin/deleteTeacher?id=${teacher.id}" />
    <c:set var="updateUrl" value="/java_semestWork_1/admin/updateTeacher?id=${teacher.id}" />
    <tr>
      <td>${teacher.name}</td>
      <td>${teacher.surname}</td>
      <td>${teacher.mail}</td>
      <td>${teacher.infoOfEducation}</td>
      <td>
        <a href="#" class="button delete" onclick="confirmDelete(this)" data-id="${teacher.id}">Delete</a>
        <a href="${updateUrl}" class="button update">Update</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<script>
  function confirmDelete(deleteLink) {
    var teacherId = deleteLink.getAttribute("data-id");
    if (confirm("Are you sure you want to delete this teacher?")) {
      var deleteUrl = "/java_semestWork_1/admin/deleteTeacher?id=" + teacherId;
      window.location.href = deleteUrl;
    }
  }
</script>

</body>
</html>
