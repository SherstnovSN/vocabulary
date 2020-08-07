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
        ${position.source} - ${position.translation}
    </div>
</div>
</body>
</html>