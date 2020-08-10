<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Редактировать</title>
    <link href="<c:url value="/resources/style.css"/>" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="main">
    <div id="content">
        <a href="<c:url value="/"/>"><h2>Словарь</h2></a>
        <c:url value="/edit" var="editPosition"/>
        <form action="${editPosition}" method="POST">
            <table>
                <tr>
                    <td>Словарь</td>
                    <td>${position.vocabulary.name}</td>
                </tr>
                <tr>
                    <td><label for="source">Слово</label></td>
                    <td><input id="source" type="hidden" name="source" value="${position.source}">${position.source}</td>
                </tr>
                <tr>
                    <td><label for="translation">Перевод</label></td>
                    <td><input id="translation" type="text" name="translation" required autofocus></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Редактировать"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
