<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Create Grade</title>
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
      max-width: 400px;
      margin: 0 auto;
    }

    label {
      font-weight: bold;
      display: block;
      margin-bottom: 5px;
    }

    select, input[type="date"] {
      margin-bottom: 10px;
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    #grades {
      margin-bottom: 10px;
    }

    #grades label {
      display: inline-block;
      background-color: #4CAF50;
      color: white;
      padding: 6px 12px;
      border-radius: 4px;
      margin-right: 5px;
      cursor: pointer;
    }

    .button.create {
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
      transition: background-color 0.3s ease;
    }

    .button.create:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>
<h1>Create Grade</h1>

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

  <label for="date">Select Date:</label>
  <input type="date" name="date" id="date" required>

  <label>Select Grade:</label>
  <div id="grades">
    <c:forEach begin="1" end="5" var="grade">
      <label>
        <input type="radio" name="grade" value="${grade}" required> ${grade}
      </label>
    </c:forEach>
  </div>

  <input type="submit" value="Create Grade" class="button create">
</form>
</body>
</html>
