<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Average Grades</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }
        .container {
            width: 80%;
            margin: auto;
            overflow: hidden;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #e0e0e0;
            padding: 12px 15px;
            text-align: left;
        }
        th {
            background-color: #3498db;
            color: #fff;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .grade-list {
            padding: 0;
            list-style: none;
        }
        .grade-list li {
            display: inline;
            margin-right: 5px;
        }
        .grade-list li:not(:last-child)::after {
            content: ",";
        }
        .overall-average {
            font-weight: bold;
            color: #3498db;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Grades Overview</h2>
    <table>
        <tr>
            <th>Subject</th>
            <th>All Grades</th>
            <th>Average Grade</th>
        </tr>
        <c:forEach var="subject" items="${averageGradesBySubject.keySet()}">
            <tr>
                <td>${subject.name}</td>
                <td>
                    <ul class="grade-list">
                        <c:forEach var="grade" items="${gradesBySubject[subject]}">
                            <li>${grade.grade}</li>
                        </c:forEach>
                    </ul>
                </td>
                <td>${averageGradesBySubject[subject]}</td>

            </tr>
        </c:forEach>
        <tr>
            <td colspan="2" class="overall-average">Overall Average</td>
            <td class="overall-average">${overallAverage}</td>
        </tr>
    </table>
</div>

</body>
</html>
