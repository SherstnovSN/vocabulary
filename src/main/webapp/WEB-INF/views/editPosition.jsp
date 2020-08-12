<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Редактировать</title>
        <link href="<c:url value="/resources/style.css"/>" rel="stylesheet" type="text/css" />
        <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
    </head>
    <body>
        <div id="main">
            <div id="content">
                <a href="<c:url value="/"/>"><h2>Словарь</h2></a>
                Словарь<br>
                <strong>${position.vocabulary.name}</strong>
                <br><br>
                Слово<br>
                <strong>${position.source}</strong>
                <br><br>
                Перевод
                <c:forEach var="translation" items="${position.translations}">
                    <div class="translation${translation.id}">
                        <strong>${translation.word}</strong>
                        <a onclick="doAjax(${translation.id})" title="Удалить"><strong>x</strong></a>
                    </div>
                </c:forEach>
                <a href="<c:url value="/addTranslation/${position.source}"/>" title="Добавить"><strong>+</strong></a>
                <script type="text/javascript">
                    function doAjax(translationId) {
                        $.ajax({
                            type: 'GET',
                            url: '/vocabulary/deleteTranslation',
                            dataType: 'json',
                            data: ({
                                id: translationId
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
