<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <title>
        mifprojetgp7
    </title>
    <jsp:include page="menu.jsp" /> 
    <meta charset="UTF-8">
</head>

<body>
    <c:if test="${ requestScope.page != null }">
        <jsp:include page="${ requestScope.page }"/>
    </c:if>
</body>

<jsp:include page="footer.jsp" />
</html>
