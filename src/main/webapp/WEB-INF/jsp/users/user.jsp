<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="container-fluid">
    <div class="card text-center">
        <div class="card-header">
            <h1>Utilisateur ${ sessionScope.user.name } ${ sessionScope.user.firstName }</h1>
        </div>
        <div class="card-body">
            <p class="card-text">Votre adresse mail: ${ sessionScope.user.emailUser }</p>
            <p class="card-text">Nombre d'évènements crés: ${ requestScope.countCreated }</p>
            <p class="card-text">Nombre d'évènements participés: ${ requestScope.countContribs }</p>
        </div>
    </div>
    <div class="d-flex flex-md-column justify-content-center">
    <c:choose>
        <c:when test="${ requestScope.interests.size() > 0 }">
            <div class="d-flex flex-md-row justify-content-center">
                <h3>Liste des catégories aimés :<br/></h3>
            </div>
            <c:forEach items="${ requestScope.interests }" var="interest">
                <div class="d-flex flex-md-row justify-content-center">
                    <h4>${ interest.category.categoryName }</h4>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="d-flex flex-md-row justify-content-center">
                <h3>Vous n'avez aimé aucune catégorie...</h3>
            </div>
        </c:otherwise>
    </c:choose>
</div>
