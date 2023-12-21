<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Teacher Subjects</title>
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
    input[type="submit"] {
      width: 100%;
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }

    table th, table td {
      border: 1px solid #ccc;
      padding: 8px;
      text-align: left;
    }

    table th {
      background-color: #f2f2f2;
    }

    .button-container {
      display: flex;
      align-items: center;
    }

    .button {
      text-decoration: none;
      padding: 5px 10px;
      border: 1px solid #ccc;
      border-radius: 4px;
      margin-left: 10px;
    }

    .delete {
      background-color: #ff4d4d;
      color: white;
    }

    .update {
      background-color: #4caf50;
      color: white;
    }
  </style>
</head>
<body>
<h1>Teacher Subjects</h1>

<form method="post">
  <label for="teacherId">Select Teacher:</label>
  <select name="teacherId" id="teacherId" required>
    <c:forEach items="${teachers}" var="teacher">
      <option value="${teacher.id}">${teacher.name} ${teacher.surname}</option>
    </c:forEach>
  </select>
  <input type="submit" value="Show Subjects">
</form>

<c:if test="${not empty teacherSubjects}">
  <h2>Subjects for the selected teacher:</h2>
  <table>
    <tr>
      <th>Subject Name</th>
      <th>Actions</th>
    </tr>
    <c:forEach items="${teacherSubjects}" var="subject">
      <tr>
        <td>${subject.name}</td>
        <td class="button-container">
          <a href="#" class="button delete" onclick="confirmDelete(${subject.id})">Delete</a>
          <a href="updateSubject?id=${subject.id}" class="button update">Update</a>
        </td>
      </tr>
    </c:forEach>
  </table>
</c:if>

<script>
  function confirmDelete(subjID) {
    if (confirm("Are you sure you want to delete this subject?")) {
      var deleteUrl = "deleteSubject?id=" + subjID;
      window.location.href = deleteUrl;
    }
  }
</script>

</body>
</html>
