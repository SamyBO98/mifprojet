<%@ page import="fr.univlyon1.mifprojetgp7.model.Account" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 01/11/2020
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome </title>
</head>
<body>
<h1>Welcome to the main page <%= ((Account)session.getAttribute("user")).getFirstName() %>  <%= ((Account)session.getAttribute("user")).getLastName() %>!</h1>
<h2>For the moment, nothing to show you...</h2>
<h2>But don't worry, this project is in progress!</h2>

<h3>
    Your informations <br/>
    Your email: <%= ((Account)session.getAttribute("user")).getEmailUser() %><br/>
    Your first name: <%= ((Account)session.getAttribute("user")).getFirstName() %><br/>
    Your last name: <%= ((Account)session.getAttribute("user")).getLastName() %><br/>
</h3>

<a href="disconnect">Disconnect</a>
</body>
</html>
