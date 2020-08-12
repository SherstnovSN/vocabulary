<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Перевести</title>
    <link href="<c:url value="/resources/style.css"/>" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="main">
    <div id="content">
        <a href="<c:url value="/"/>"><h2>Словарь</h2></a>
        <div id="form">
            <c:url value="/translation" var="translate"/>
            <form action="${translate}" method="POST">
                <label for="vocabulary">Словарь</label>
                <select id="vocabulary" name="vocabulary">
                    <option value="0">Все словари</option>
                        <c:forEach var="vocabulary" items="${vocabularies}">
                            <option value="${vocabulary.id}">${vocabulary.name}</option>
                        </c:forEach>
                </select>
                <br><br>
                <label for="source">Слово</label>
                <input id="source" type="text" name="source" required autofocus>
                <br><br>
                <input type="submit" value="Перевести">
            </form>
        </div>
    </div>
</div>
</body>
</html>