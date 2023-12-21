<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>School "MBOYSOSH"</title>
    <style>

        .navbar {
            background-color: #333;
            overflow: hidden;
        }

        .navbar a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }

        .school-info {
            background-color: #f2f2f2;
            padding: 20px;
            text-align: center;
        }

        h2, h3, p {
            font-family: Arial, sans-serif;
        }

        ul {
            list-style-type: disc;
        }

        .school-image {
            max-width: 100%;
            height: auto;
        }
    </style>
</head>
<body>
<div class="navbar">
    <c:url var="homeUrl" value="/"/>
    <a href="${homeUrl}">*</a>

    <c:url var="myCabinetUrl" value="/myCabinet"/>
    <a href="${myCabinetUrl}">My Cabinet</a>

    <c:url var="loginUrl" value="/login"/>
    <a href="${loginUrl}">Login</a>

    <c:if test="${role == 'student'}">
        <c:url var="studentScheduleUrl" value="/student/showSchedule"/>
        <a href="${studentScheduleUrl}">Schedule</a>
    </c:if>

    <c:if test="${role == 'teacher'}">
        <c:url var="teacherGradeUrl" value="/teacher/gradePage"/>
        <a href="${teacherGradeUrl}">Grade</a>
    </c:if>

    <c:if test="${role == 'student'}">
        <c:url var="studentGradeUrl" value="/student/averageGrade"/>
        <a href="${studentGradeUrl}">Grade</a>
    </c:if>

    <c:if test="${role == 'teacher'}">
        <c:url var="teacherHomeworkUrl" value="/teacher/homeworkPage"/>
        <a href="${teacherHomeworkUrl}">Homework</a>
    </c:if>

    <c:if test="${role == 'admin'}">
        <c:url var="adminPanelUrl" value="/admin"/>
        <a href="${adminPanelUrl}">Admin Panel</a>
    </c:if>
</div>


<h2>Welcome to "MBOYSOSH"</h2>

<div class="school-info">
    <h3>About Our School</h3>
    <p>Welcome to "MBOYSOSH". We are an educational center where students develop their abilities, build their futures, and create long-lasting friendships. Our mission is to provide quality education and help students achieve success in their studies and in life.</p>
    <p>Here are some of our core values:</p>
    <ul>
        <li>Respect</li>
        <li>Tolerance</li>
        <li>Innovation</li>
        <li>Collaboration</li>
    </ul>
    <p>We take pride in our professional teachers and a variety of educational programs. Our curriculum is designed to focus on academic excellence and personal development.</p>
    <p>Our students also participate in extracurricular activities, sports events, and clubs.</p>
    <p>We value the partnership between parents and teachers and the contribution of our parents to education.</p>
    <p>The safety and comfort of our students are our priorities.</p>
    <p>We are actively involved in social and charitable activities, supporting the local community.</p>
    <p>At "MBOYSOSH," we aim to stay ahead by following modern trends in education and utilizing advanced technologies.</p>
    <img class="school-image" src="https://avatars.mds.yandex.net/get-altay/2812564/2a00000174a1186d22575c7008a07c58c0d0/XXL_height" alt="Our students at school">
</div>
</body>
</html>
