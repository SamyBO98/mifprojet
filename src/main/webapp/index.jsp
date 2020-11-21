<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>
        mifprojetgp7
    </title>
    <jsp:include page="menu.jsp" /> 
</head>

<body>
    <c:choose>
        <c:when test="${ requestScope.page != null }">
            <jsp:include page="${ requestScope.page }"/>
        </c:when>
        <c:otherwise>
            <a href="./users/login">Connect to this shit</a>
            <br/>
            <a href="./users/signup">Register me</a>
        </c:otherwise>
    </c:choose>
</body>
</html>
