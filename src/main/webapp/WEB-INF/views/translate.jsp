<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Перевести</title>
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
        <div id="form">
            <c:url value="/translation" var="translate"/>
            <form action="${translate}" method="POST">
                <label for="sourceLanguage">Перевести с</label>
                <select id="sourceLanguage" name="sourceLanguageId">
                    <c:forEach var="language" items="${languages}">
                        <option value="${language.id}">${language.name}</option>
                    </c:forEach>
                </select>
                <label for="translationLanguage">на</label>
                <select id="translationLanguage" name="translationLanguageId">
                    <c:forEach var="language" items="${languages}">
                        <option value="${language.id}">${language.name}</option>
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