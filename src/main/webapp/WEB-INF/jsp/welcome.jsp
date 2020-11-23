<!doctype html>
<%@ page import="fr.univlyon1.mifprojetgp7.model.Account" %>
<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Connecté</title>
</head>
<body>
    <jsp:include page="${request.requestURI}/menu.jsp" />

    <c:if test="${ requestScope.page != null }">
        <jsp:include page="${ requestScope.page }"/>
    </c:if>
</body>
<jsp:include page="../../footer.jsp" />
</html>
