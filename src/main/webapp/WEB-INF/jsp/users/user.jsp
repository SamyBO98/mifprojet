<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Utilisateur ${ sessionScope.user.name } ${ sessionScope.user.firstName }</h1>
<h2>
    Votre adresse mail: ${ sessionScope.user.emailUser }.
</h2>
<h3>Nombre d'évènements crés: ${ requestScope.countCreated }</h3>
<h3>Nombre d'évènements participés: ${ requestScope.countContribs }</h3>

<c:choose>
    <c:when test="${ requestScope.interests.size() > 0 }">
        <h3>Liste des catégories aimés<br/></h3>
        <c:forEach items="${ requestScope.interests }" var="interest">
            <h4>${ interest.category.categoryName }</h4>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <h3>Vous n'avez aimé aucune catégorie...</h3>
    </c:otherwise>
</c:choose>
