<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: artemvaleev
  Date: 09.11.2023
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<h2>Домашняя работа не сохранена</h2>
<c:url var="createHomeworkUrl" value="createHomework"/>
<a href="${createHomeworkUrl}">Создать запись о д/з</a><body>

</body>
</html>
