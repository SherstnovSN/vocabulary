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
        <a href="<c:url value="/add"/>">Добавить</a>
        <a href="<c:url value="/vocabulary"/>">Отобразить</a>
        <a href="<c:url value="/translate"/>">Перевести</a>
        <a href="<c:url value="/delete"/>">Удалить</a>
        <br><br>
        <div id="form">
            <c:url value="/translation" var="translate"/>
            <form action="${translate}" method="POST">
                <table>
                    <tr>
                        <td><label for="source">Слово</label></td>
                        <td><input id="source" type="text" name="source" required autofocus></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Перевести"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>