<%@ page import="ru.itis.models.Schedule" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Schedule for Class and Day</title>
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
    }

    label {
      font-weight: bold;
      display: block;
      margin-bottom: 5px;
    }

    select, input[type="submit"] {
      margin-bottom: 10px;
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    table {
      border-collapse: collapse;
      width: 100%;
      margin-top: 20px;
    }

    th, td {
      border: 1px solid #ddd;
      text-align: left;
      padding: 8px;
    }

    tr:nth-child(even) {
      background-color: #f2f2f2;
    }

    .button {
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

    .button.delete {
      background-color: #f44336;
    }

    .button.update {
      background-color: #2196F3;
    }

    .button:hover {
      background-color: #45a049;
    }

    .button.delete:hover {
      background-color: #f32d20;
    }

    .button.update:hover {
      background-color: #1976D2;
    }
  </style>
</head>
<body>
<h1>Schedule for Class and Day</h1>

<form method="get">
  <label for="className">Select Class:</label>
  <select name="className" id="className" required>
    <option value="">Select Class</option>
    <% for (String className : (List<String>) request.getAttribute("studentsClassName")) { %>
    <option value="<%= className %>"><%= className %></option>
    <% } %>
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
  <input type="submit" value="Show Schedule" class="button update">
</form>

<% String selectedClass = request.getParameter("className");
  String selectedDay = request.getParameter("dayOfWeek");
  List<Schedule> scheduleForClassAndDay = (List<Schedule>) request.getAttribute("scheduleForClassAndDay");
  if (scheduleForClassAndDay != null && !scheduleForClassAndDay.isEmpty()) { %>
<h2>Schedule for <%= selectedClass %> on <%= selectedDay %>:</h2>
<table>
  <tr>
    <th>Time</th>
    <th>Subject Name</th>
    <th>Action</th>
  </tr>
  <% for (Schedule schedule : scheduleForClassAndDay) { %>
  <tr>
    <td><%= schedule.getTime() %></td>
    <td><%= request.getAttribute("subjectName_" + schedule.getSubjectId()) %></td>
    <td>
      <button class="button delete" onclick="confirmDelete('<%= schedule.getId() %>')">Delete</button>
      <button class="button update" onclick="updateSchedule('<%= schedule.getId() %>')">Update</button>
    </td>
  </tr>
  <% } %>
</table>
<% }
%>
<script>
  function confirmDelete(scheduleId) {
    if (confirm("Are you sure you want to delete this schedule?")) {
      var deleteUrl = "deleteSchedule?id=" + scheduleId;
      window.location.href = deleteUrl;
    }
  }

  function updateSchedule(scheduleId) {
    var updateUrl = "updateSchedule?id=" + scheduleId;
    window.location.href = updateUrl;
  }
</script>
</body>
</html>
