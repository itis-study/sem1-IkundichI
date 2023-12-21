<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Student Grades</title>
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

    select {
      margin-bottom: 10px;
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    table {
      border-collapse: collapse;
      width: 100%;
    }

    th, td {
      border: 1px solid #ccc;
      padding: 8px;
      text-align: center;
    }

    th {
      background-color: #333;
      color: white;
    }

    .button {
      display: inline-block;
      padding: 8px 12px;
      background-color: #333;
      color: white;
      text-decoration: none;
      border: none;
      cursor: pointer;
      border-radius: 4px;
    }

    .delete {
      background-color: red;
    }

    .update {
      background-color: blue;
    }
  </style>
</head>
<body>
<h1>Student Grades</h1>

<form method="post">
  <label for="student">Select Student:</label>
  <select name="student" id="student" required>
    <option value="">Select Student</option>
    <c:forEach items="${studentList}" var="student">
      <option value="${student.id}">${student.name}</option>
    </c:forEach>
  </select>
  <label for="subject">Select Subject:</label>
  <select name="subject" id="subject" required>
    <option value="">Select Subject</option>
    <c:forEach items="${subjectList}" var="subject">
      <option value="${subject.id}">${subject.name}</option>
    </c:forEach>
  </select>
  <input type="submit" value="Show Grades">
</form>

<c:if test="${not empty studentGrades}">
  <h2>Grades for ${selectedStudent.name} in ${selectedSubject.name}</h2>
  <table>
    <tr>
      <th>Date</th>
      <th>Grade</th>
      <th>Action</th>
    </tr>
    <c:forEach items="${studentGrades}" var="grade">
      <tr>
        <td>
          <fmt:formatDate value="${grade.dateOfGrade}" pattern="dd MMMM yyyy" />
        </td>
        <td>${grade.grade}</td>
        <td>
          <a href="#" class="button delete" onclick="confirmDelete(${grade.id})">Delete</a>
          <a href="updateGrade?id=${grade.id}" class="button update">Update</a>
        </td>
      </tr>
    </c:forEach>

  </table>
</c:if>

<script>
  function confirmDelete(gradeId) {
    if (confirm("Are you sure you want to delete this grade?")) {

      window.location.href = "deleteGrade?id=" + gradeId;
    }
  }
</script>

</body>
</html>
