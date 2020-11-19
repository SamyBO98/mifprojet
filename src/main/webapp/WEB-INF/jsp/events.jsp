<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Liste des evenements</h1>
<c:if test="${ requestScope.events.size() > 0 }">
    <c:forEach items="${ requestScope.events }" var="event">
        <h2>${event.title}</h2>
        <h3>${event.content}</h3>
        <h3>Crée par l'utilisateur ${event.account.name} ${event.account.firstName}</h3>
        <h4><a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/${ event.id }">Lien vers la page de l'évènement</a></h4>
    </c:forEach>
</c:if>
<c:if test="${ requestScope.events.size() == 0 }">
    <h2>Aucun évènement dans la base de données</h2>
</c:if>
