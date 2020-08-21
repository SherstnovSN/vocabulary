<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Редактировать</title>
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
                Словарь<br>
                <strong>${position.language.name}</strong>
                <br><br>
                Слово<br>
                <strong>${position.source}</strong>
                <br><br>
                Перевод
                <br>
                <c:forEach var="translation" items="${position.translations}">
                    <div class="translation${translation.id}">
                        <strong>${translation.word}</strong>
                        <a onclick="doAjax(${position.id}, ${translation.id})" title="Удалить">
                            <img src="<c:url value="/resources/img/delete.png"/>" width="13" height="13" alt="delete">
                        </a>
                    </div>
                </c:forEach>
                <a href="<c:url value="/addTranslation/${position.id}"/>" title="Добавить"><strong>+</strong></a>
                <script type="text/javascript">
                    function doAjax(positionId, translationId) {
                        $.ajax({
                            type: 'GET',
                            url: '/vocabulary/deleteTranslation',
                            data: ({
                                positionId: positionId,
                                translationId: translationId
                            }),
                            success:
                                $(".translation" + translationId).text('Удалено')
                        });
                    }
                </script>
            </div>
        </div>
    </body>
</html>
