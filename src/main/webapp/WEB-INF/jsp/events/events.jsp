<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${ requestScope.events.size() > 0 }">
    <div class="container">
        <div class="d-flex justify-content-center mb-5">
            <h1> Liste des évènements </h1>
        </div>
        <div class="row">
        <c:forEach items="${ requestScope.events }" var="event" varStatus="status">
            <div class="col-lg-3 col-md-4 col-sm-12 mb-5">
                <div class="card mx-auto">
                    <div class="card-body">
                        <h3 class="card-title">${event.title}</h3>
                        <p class="card-text">Crée par l'utilisateur ${event.account.name} ${event.account.firstName}</p>
                        <p class="card-text">Catégorie: ${ event.category.categoryName }</p>
                        <a class="card-link m-2" href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/${ event.id }"> <button class="btn btn-secondary"> En savoir plus </button></a>
                    </div>
                </div>
            </div>
        </c:forEach>
        </div>
    </div>
</c:if>
<c:if test="${ requestScope.events.size() == 0 }">
    <h2>Aucun événement dans la base de données</h2>
</c:if>
