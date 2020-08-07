<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Словарь</title>
    <link href="<c:url value="/resources/style.css"/>" rel="stylesheet" type="text/css" />
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <style>
        h4 { cursor:pointer;}
    </style>
</head>
<body>
<div id="main">
    <div id="content">
        <a href="<c:url value="/"/>"><h2>Словарь</h2></a>
        <a href="<c:url value="/add"/>">Добавить</a>
        <a href="<c:url value="/translate"/>">Перевести</a>
        <br>
        <c:forEach var="vocabulary" items="${vocabularies}">
            <h4><span>${vocabulary.name}</span></h4>
                <ul>
                    <c:if test="${vocabulary.positions.size() != 0}">
                        <c:forEach var="position" items="${vocabulary.positions}">
                            <li>${position.source} - ${position.translation} <a href="<c:url value="/delete/${position.source}"/>">Удалить</a></li>
                        </c:forEach>
                    </c:if>
                    <c:if test="${vocabulary.positions.size() == 0}">
                        Словарь пуст
                    </c:if>
                </ul>
        </c:forEach>
        <script>
            $(document).ready(function(){
                $("ul").hide();
                $("h4 span").click(function(){
                    $(this).parent().next().slideToggle();
                });
            });
        </script>
    </div>
</div>
</body>
</html>
