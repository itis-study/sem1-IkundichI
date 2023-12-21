<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Subject</title>
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

        input[type="text"],
        select,
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
<h1>Create Subject</h1>

<form method="post">
    <label for="subjectName">Subject Name:</label>
    <input type="text" name="subjectName" id="subjectName" required>
    <br>
    <label for="teacherId">Teacher:</label>
    <select name="teacherId" id="teacherId" required>
        <c:forEach items="${teachers}" var="teacher">
            <option value="${teacher.id}">${teacher.name} ${teacher.surname}</option>
        </c:forEach>
    </select>
    <br>
    <input type="submit" value="Create">
</form>

</body>
</html>
