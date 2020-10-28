<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.*" %>
<%@ page import="fr.univlyon1.mifprojetgp7.ConnectDB" %>
<%@ page import="fr.univlyon1.mifprojetgp7.dao.EventDAO" %>
<%@ page import="fr.univlyon1.mifprojetgp7.model.Event" %>

<html>
<body>
    <h2>Hello World!</h2>
    <form action="register" method="post">

        <label>
            Entrez le titre
            <input type="text" name="title"/>
        </label>

        <label>
            Entrez une description (optionnel)
            <input type="text" name="description"/>
        </label>

        <button type="submit" name="button">Lancez</button>

    </form>

    <c:if test="${ sessionScope.event != null }">
        <h1>Dernier evenement crée</h1>
        <h2>Id: ${ sessionScope.event.id }</h2>
        <h2>Titre: ${ sessionScope.event.title }</h2>
        <h2>Description: ${ sessionScope.event.description }</h2>
    </c:if>
</body>
</html>
