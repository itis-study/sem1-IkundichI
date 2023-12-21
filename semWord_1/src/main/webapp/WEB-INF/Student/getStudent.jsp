<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student List</title>
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
<h1>Student List</h1>

<c:if test="${not empty studentList}">
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Class</th>
            <th>Mail</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${studentList}" var="student">
            <tr>
                <td><c:out value="${student.getName()}" /></td>
                <td><c:out value="${student.getSurname()}" /></td>
                <td><c:out value="${student.getClassName()}" /></td>
                <td><c:out value="${student.getMail()}" /></td>
                <td>
                    <a href="#" class="button delete" onclick="confirmDelete('<c:out value="${student.getId()}" />')">Delete</a>
                    <a href="updateStudent?id=<c:out value="${student.getId()}" />" class="button update">Update</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<script>
    function confirmDelete(studentId) {
        if (confirm("Are you sure you want to delete this student?")) {
            var deleteUrl = "deleteStudent?id=" + studentId;
            window.location.href = deleteUrl;
        }
    }
</script>

</body>
</html>
