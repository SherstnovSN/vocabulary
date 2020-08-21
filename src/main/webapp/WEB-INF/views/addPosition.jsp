<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить</title>
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet" type="text/css" />
        <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
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
                <c:url value="/addPosition" var="addPosition"/>
                <form action="${addPosition}" method="POST">
                    <label for="sourceLanguage">Язык</label>
                    <select id="sourceLanguage" name="sourceLanguageId">
                        <c:forEach var="language" items="${languages}">
                            <option value="${language.id}">${language.name}</option>
                        </c:forEach>
                    </select>
                    <br><br>
                    <label for="source">Слово</label>
                    <input id="source" type="text" name="source" required autofocus>
                    <br><br>
                    <label for="translationLanguage">Язык</label>
                    <select id="translationLanguage" name="translationLanguageId">
                        <c:forEach var="language" items="${languages}">
                            <option value="${language.id}">${language.name}</option>
                        </c:forEach>
                    </select>
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
                    let x = 0;
                    function addInput() {
                        document.getElementById('input' + x).innerHTML = '<label for="translation">Перевод</label> ' +
                            '<input id="translation" type="text" name="translations">' +
                            '<br><br>' +
                            '<div id="input' + (x + 1) + '"></div>';
                        x++;
                    }
                </script>
            </div>
        </div>
    </body>
</html>
