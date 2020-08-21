<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Словарь</title>
    <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="main">
    <div id="content">
        <br>
        <a href="<c:url value="/"/>"><h2>Словарь</h2></a>
        <a href="<c:url value="/admin"/>" title="Управление словарем">
            <img src="<c:url value="/resources/img/admin.png"/>" width="17" height="17" alt="admin">
        </a>
        <br><br>
        Позиция существует. <a href="<c:url value="/editPosition/${positionId}"/>">Перейти к редактированию.</a>
    </div>
</div>
</body>
</html>
