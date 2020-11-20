<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
</head>
<title>
    mifprojetgp7
</title>
<body>
    <jsp:include page="menu.jsp" />
    <c:if test="${ requestScope.page != null }">
        <jsp:include page="${ requestScope.page }"/>
    </c:if>
</body>
</html>
