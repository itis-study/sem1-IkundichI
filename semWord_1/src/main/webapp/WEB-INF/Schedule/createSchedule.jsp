<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Create Schedule</title>
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
<h1>Create Schedule</h1>

<form method="post">
  <label for="subjectId">Select Subject:</label>
  <select name="subjectId" id="subjectId" required>
    <option value="">Select Subject</option>
    <c:forEach items="${requestScope.subjects}" var="subject">
      <option value="${subject.id}">${subject.name}</option>
    </c:forEach>
  </select>
  <label for="className">Select Class:</label>
  <select name="className" id="className" required>
    <option value="">Select Class</option>
    <c:forEach items="${requestScope.studentsClassName}" var="studentsClassName">
      <option value="${studentsClassName}">${studentsClassName}</option>
    </c:forEach>
  </select>
  <label for="dayOfWeek">Select Day of Week:</label>
  <select name="dayOfWeek" id="dayOfWeek" required>
    <option value="">Select Day of Week</option>
    <option value="Monday">Monday</option>
    <option value="Tuesday">Tuesday</option>
    <option value="Wednesday">Wednesday</option>
    <option value="Thursday">Thursday</option>
    <option value="Friday">Friday</option>
    <option value="Saturday">Saturday</option>
  </select>
  <label for="time">Select Time:</label>
  <select name="time" id="time" required>
    <option value="">Select Time</option>
    <option value="08:30-10:00">08:30-10:00</option>
    <option value="10:10-11:40">10:10-11:40</option>
    <option value="12:10-13:40">12:10-13:40</option>
    <option value="13:50-15:20">13:50-15:20</option>
    <option value="15:50-17:20">15:50-17:20</option>
    <option value="17:30-19:00">17:30-19:00</option>
    <option value="19:10-20:40">19:10-20:40</option>
  </select>
  <input type="submit" value="Create Schedule">
</form>
</body>
</html>
