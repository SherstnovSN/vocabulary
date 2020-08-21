<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Словарь</title>
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet" type="text/css" />
        <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
        <style>
            h4 { cursor:pointer;}
        </style>
    </head>
    <body>
        <div id="main">
            <div id="content">
                <br>
                <a href="<c:url value="/"/>"><h2>Словарь</h2></a>
                <br><br>
                <a href="<c:url value="/addPosition"/>">Добавить</a>
                <br>
                <c:forEach var="language" items="${languages}">
                    <h4><span>${language.name}</span></h4>
                        <ul>
                            <c:if test="${language.positions.size() != 0}">
                                <c:forEach var="position" items="${language.positions}">
                                        <li>
                                            <div class="position${position.id}">
                                                <strong>${position.source}</strong> -
                                                <c:forEach var="translation" items="${position.translations}">
                                                    ${translation.word} (${translation.language.name})
                                                </c:forEach>
                                                <a href="<c:url value="/editPosition/${position.id}"/>" title="Редактировать">
                                                    <img src="<c:url value="/resources/img/edit.png"/>" width="13" height="13" alt="edit">
                                                </a>
                                                <a onclick="doAjax('${position.id}')" title="Удалить">
                                                    <img src="<c:url value="/resources/img/delete.png"/>" width="13" height="13" alt="delete">
                                                </a>
                                            </div>
                                        </li>
                                </c:forEach>
                            </c:if>
                            <c:if test="${language.positions.size() == 0}">
                                Словарь пуст
                            </c:if>
                        </ul>
                </c:forEach>
                <script type="text/javascript">
                    $(document).ready(function(){
                        $("ul").hide();
                        $("h4 span").click(function(){
                            $(this).parent().next().slideToggle();
                        });
                    });
                </script>
                <script type="text/javascript">
                    function doAjax(positionId) {
                        $.ajax({
                            type: 'GET',
                            url: 'deletePosition',
                            data: ({
                                positionId: positionId
                            }),
                            success:
                                $(".position" + positionId).text('Удалено')
                        });
                    }
                </script>
            </div>
        </div>
    </body>
</html>
