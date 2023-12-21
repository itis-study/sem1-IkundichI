<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Homework</title>
</head>
<style>

    form {
        max-width: 400px;
        margin: 0 auto;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        background-color: #f9f9f9;
    }

    form h1 {
        text-align: center;
        color: #333;
    }

    form div {
        margin-bottom: 10px;
    }

    form label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }

    form input[type="text"],
    form select,
    form input[type="date"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 3px;
        font-size: 16px;
    }

    form input[type="submit"] {
        background-color: #007BFF;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 3px;
        font-size: 18px;
        cursor: pointer;
    }

    form input[type="submit"]:hover {
        background-color: #0056b3;
    }

</style>
<body>

<form method="post">
    <div>
        <label for="subject">Subject:</label>
        <select name="subject" id="subject" required>
            <option value="">Select Subject</option>
            <c:forEach items="${subjectList}" var="subject">
                <option value="${subject.id}">${subject.name}</option>
            </c:forEach>
        </select>
    </div>

    <div>
        <label for="className">Class Name:</label>
        <select name="className" id="className" required>
            <option value="">Select Class</option>
            <c:forEach items="${classNames}" var="studentClassName">
                <option value="${studentClassName}">${studentClassName}</option>
            </c:forEach>
        </select>
    </div>

    <div>
        <label for="date">Date:</label>
        <input type="date" name="date" id="date" required>
    </div>

    <div>
        <label for="description">Homework Description:</label>
        <input type="text" id="description" name="description" required>
    </div>

    <div>
        <input type="submit" name="submitBtn" value="Create Homework" class="button create">
    </div>
</form>

</body>
</html>
