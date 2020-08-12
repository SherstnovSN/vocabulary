<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить</title>
        <link href="<c:url value="/resources/style.css"/>" rel="stylesheet" type="text/css" />
        <script src="http://code.jquery.com/jquery-latest.js"></script>
    </head>
    <body>
        <div id="main">
            <div id="content">
                <a href="<c:url value="/"/>"><h2>Словарь</h2></a>
                <c:url value="/addPosition" var="addPosition"/>
                <form action="${addPosition}" method="POST">
                    <label for="vocabulary">Словарь</label>
                    <select id="vocabulary" name="vocabulary">
                        <c:forEach var="vocabulary" items="${vocabularies}">
                            <option value="${vocabulary.id}">${vocabulary.name}</option>
                        </c:forEach>
                    </select>
                    <br><br>
                    <label for="source">Слово</label>
                    <input id="source" type="text" name="source" required autofocus>
                    <br><br>
                    <label for="translation">Перевод</label>
                    <input id="translation" type="text" name="translations" required>
                    <br><br>
                    <div id="input0"></div>
                    <a onclick="addInput()">Добавить перевод</a>
                    <br><br>
                    <input type="submit" value="Добавить">
                </form>
                <script>
                    var x = 0;
                    function addInput() {
                        var str = '<label for="translation">Перевод</label> ' +
                            '<input id="translation" type="text" name="translations">' +
                            '<br><br>' +
                            '<div id="input' + (x + 1) + '"></div>';
                        document.getElementById('input' + x).innerHTML = str;
                        x++;
                    }
                </script>
            </div>
        </div>
    </body>
</html>