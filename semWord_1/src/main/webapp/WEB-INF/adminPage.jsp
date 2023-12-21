<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Page</title>
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

        .nav-list {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
        }

        .section-header {
            color: blue;
            margin-top: 20px;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        ul li {
            margin: 10px 0;
        }

        .nav-button {
            display: block;
            width: 100%;
            text-align: center;
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border: none;
            border-radius: 5px;
            margin-top: 10px;
        }

        .nav-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1>Welcome to Admin Page</h1>
<div class="nav-list">
    <h2 class="section-header">Teachers:</h2>
    <ul>
        <li><c:url var="createTeacherUrl" value="/admin/createTeacher"/><a href="${createTeacherUrl}" class="nav-button">Создать учителя</a></li>
        <li><c:url var="getTeacherUrl" value="/admin/getTeacher"/><a href="${getTeacherUrl}" class="nav-button">Список учителей</a></li>
    </ul>

    <h2 class="section-header">Students:</h2>
    <ul>
        <li><c:url var="createStudentUrl" value="/admin/createStudent"/><a href="${createStudentUrl}" class="nav-button">Создать ученика</a></li>
        <li><c:url var="getStudentUrl" value="/admin/getStudent"/><a href="${getStudentUrl}" class="nav-button">Список учеников</a></li>
    </ul>

    <h2 class="section-header">Subjects:</h2>
    <ul>
        <li><c:url var="createSubjectUrl" value="/admin/createSubject"/><a href="${createSubjectUrl}" class="nav-button">Добавить предмет учителю</a></li>
        <li><c:url var="teacherSubjectsUrl" value="/admin/teacherSubjects"/><a href="${teacherSubjectsUrl}" class="nav-button">Посмотреть предметы</a></li>
    </ul>

    <h2 class="section-header">Schedule:</h2>
    <ul>
        <li><c:url var="createScheduleUrl" value="/admin/createSchedule"/><a href="${createScheduleUrl}" class="nav-button">Добавить предмет в расписание</a></li>
        <li><c:url var="getScheduleUrl" value="/admin/getSchedule"/><a href="${getScheduleUrl}" class="nav-button">Посмотреть расписание</a></li>
    </ul>

    <h2 class="section-header">Grades:</h2>
    <ul>
        <li><c:url var="createGradeUrl" value="/teacher/createGrade"/><a href="${createGradeUrl}" class="nav-button">Выставить оценку ученику по предмету</a></li>
        <li><c:url var="getStudentGradesUrl" value="/teacher/getStudentGrades"/><a href="${getStudentGradesUrl}" class="nav-button">Посмотреть оценки ученика</a></li>
    </ul>

    <h2 class="section-header">Homework:</h2>
    <ul>
        <li><c:url var="createHomeworkUrl" value="/teacher/createHomework"/><a href="${createHomeworkUrl}" class="nav-button">Задать домашнее задание</a></li>
        <li><c:url var="getHomeworkUrl" value="/teacher/getHomework"/><a href="${getHomeworkUrl}" class="nav-button">Посмотреть домашнее задание по предмету</a></li>
    </ul>
</div>
</body>
</html>
