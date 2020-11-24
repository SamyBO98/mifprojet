<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
<head>
    <title>Connecté</title>
    <meta charset="utf-8"/>
</head>
<body>
    <jsp:include page="${request.requestURI}/menu.jsp" />

    <c:if test="${ requestScope.page != null }">
        <jsp:include page="${ requestScope.page }"/>
    </c:if>
</body>
<jsp:include page="../../footer.jsp" />
</html>
