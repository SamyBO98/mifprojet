<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${ requestScope.events.size() > 0 }">
    <div class="container-fluid">
        <div class="d-flex flex-column">
        <h1 class="d-flex mx-auto"> Liste des événements </h1>
        <c:forEach items="${ requestScope.events }" var="event" varStatus="status">
            <c:if test="${ ((status.count - 1 )  % 4 ) == 0 }">
                    <div class="d-flex flex-row justify-content-center">
            </c:if>
            <div class="card mx-auto">
                <div class="card-body">
                    <h3 class="card-title">${event.title}</h3>
                    <p class="card-text">Crée par l'utilisateur ${event.account.name} ${event.account.firstName}</p>
                    <p class="card-text">Catégorie: ${ event.category.categoryName }</p>
                    <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/${ event.id }"> <button  class="btn btn-secondary"> En savoir plus </button></a>
                </div>
            </div>
            <c:choose>
                <c:when test="${ ((status.count - 1 )  % 4 ) == 3 }">
                    </div>
                </c:when>
                <c:when test="${ (status.count) == fn:length(requestScope.events)}">
                    </div>
                </c:when>
            </c:choose>
        </c:forEach>
        </div>
    </div>
</c:if>
<c:if test="${ requestScope.events.size() == 0 }">
    <h2>Aucun événement dans la base de données</h2>
</c:if>
