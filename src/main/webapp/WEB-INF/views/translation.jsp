<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <a href="<c:url value="/"/>"><h2>Словарь</h2></a>
            <a href="<c:url value="/addPosition"/>">Добавить</a>
            <a href="<c:url value="/translate"/>">Перевести</a>
            <br><br>
            <c:if test="${positions.size() != 0}">
                <c:forEach var="position" items="${positions}">
                    <strong>${position.source}</strong> -
                    <c:forEach var="translation" items="${position.translations}">
                        ${translation.word}
                    </c:forEach>
                    (${position.vocabulary.name})
                    <br>
                </c:forEach>
            </c:if>
            <c:if test="${positions.size() == 0}">
                Слово отсутствует
            </c:if>
        </div>
    </div>
    </body>
</html>