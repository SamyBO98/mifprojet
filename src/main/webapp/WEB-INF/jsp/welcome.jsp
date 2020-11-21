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
    <h1>Welcome to the main page <%= ((Account)session.getAttribute("user")).getName() %>  <%= ((Account)session.getAttribute("user")).getFirstName() %>!</h1>
    <h2>For the moment, nothing to show you...</h2>
    <h2>But don't worry, this project is in progress!</h2>

    <h3>
        Your informations <br/>
        Your email: <%= ((Account)session.getAttribute("user")).getEmailUser() %><br/>
        Your first name: <%= ((Account)session.getAttribute("user")).getName() %><br/>
        Your last name: <%= ((Account)session.getAttribute("user")).getFirstName() %><br/>
    </h3>

    <c:if test="${ requestScope.page != null }">
        <jsp:include page="${ requestScope.page }"/>
    </c:if>

    <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events">All events</a>

    <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/created">Show my events</a>
    <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/participate">Show all events I participate</a>

    <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/create">Create event</a>
    <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search">Search for an event</a>

    <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/users/disconnect">Disconnect</a>

</body>
</html>
