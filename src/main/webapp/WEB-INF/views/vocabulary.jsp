<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Словарь</title>
        <link href="<c:url value="/resources/style.css"/>" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div id="main">
            <div id="content">
                <a href="<c:url value="/"/>"><h2>Словарь</h2></a>
                <a href="<c:url value="/add"/>">Добавить</a>
                <a href="<c:url value="/vocabulary"/>">Отобразить</a>
                <a href="<c:url value="/translate"/>">Перевести</a>
                <a href="<c:url value="/delete"/>">Удалить</a>
                <br><br>
                <c:if test="${vocabulary.size() != 0}">
                    <c:forEach var="position" items="${vocabulary}">
                        ${position.key} ${position.value}
                        <br><br>
                    </c:forEach>
                </c:if>
                <c:if test="${vocabulary.size() == 0}">
                    Словарь пуст
                </c:if>
            </div>
        </div>
    </body>
</html>
