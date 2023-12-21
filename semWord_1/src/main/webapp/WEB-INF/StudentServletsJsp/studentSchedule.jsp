<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Schedule</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }
        .container {
            display: flex;
            justify-content: space-between;
            margin: 20px;
        }
        .table-container {
            width: 48%;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f8f8f8;
        }
        h1, h2 {
            color: #444;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        li {
            background-color: #fff;
            margin: 5px 0;
            padding: 10px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }
        .date-list {
            padding-left: 0;
        }
        .date-list li {
            display: flex;
            justify-content: space-between;
            padding: 5px 10px;
            background-color: #fff;
            margin-bottom: 5px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .date-list li span {
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>Your Weekly Schedule</h1>

<div class="container">
    <div class="table-container">
        <c:forEach items="${infoSchedulesMap1}" var="entry">
            <h3>${entry.key}</h3>
            <table>
                <tr>
                    <th>Time</th>
                    <th>Subject</th>
                    <th>Homework</th>
                    <th>Grade</th>
                </tr>
                <c:forEach items="${entry.value}" var="info">
                    <tr>
                        <td>${info.time}</td>
                        <td>${info.subject}</td>
                        <td>${info.homeWork}</td>
                        <td>${info.grade}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:forEach>
    </div>

    <div class="table-container">
        <c:forEach items="${infoSchedulesMap2}" var="entry">
            <h3>${entry.key}</h3>
            <table>
                <tr>
                    <th>Time</th>
                    <th>Subject</th>
                    <th>Homework</th>
                    <th>Grade</th>
                </tr>
                <c:forEach items="${entry.value}" var="info">
                    <tr>
                        <td>${info.time}</td>
                        <td>${info.subject}</td>
                        <td>${info.homeWork}</td>
                        <td>${info.grade}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:forEach>
    </div>

</div>



</body>
</html>
