<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Liste des evenements</h1>
<c:choose>
    <c:when test="${ requestScope.events.size() == 0 }">
        <h2>Aucun évènement stockée dans la base de données...</h2>
    </c:when>
    <c:otherwise>
        <c:forEach items="${ requestScope.events }" var="event">
            <h2>${event.title}</h2>
            <h3>${event.content}</h3>
            <h3>Crée par l'utilisateur ${event.account.name} ${event.account.firstName}</h3>
            <h3>De la catégorie: ${ event.category.categoryName }</h3>
            <h4><a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/${ event.id }">Lien vers la page de l'évènement</a></h4>
            <c:if test="${ event.account.emailUser.equals(sessionScope.user.emailUser) }">
                <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/${ event.id }/delete">Supprimer l'évènement</a>
            </c:if>
        </c:forEach>
    </c:otherwise>
</c:choose>
