<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid">
    <div class="d-flex flex-column justify-content-center">
        <div class="d-flex flex-row justify-content-center">
            <h1>
                Evenement
                <a class="badge badge-secondary" href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/title/${ requestScope.event.title }">
                    ${ requestScope.event.title }
                </a>
            </h1>
        </div>
        <div class="d-flex flex-row justify-content-center">
            <h3>
                Catégorie:
                <a class="badge badge-secondary" href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/category/${ requestScope.event.category.categoryName }">
                    ${ requestScope.event.category.categoryName }
                </a>
            </h3>
        </div>
        <div class="d-flex flex-row justify-content-center">
            <h3>
                Nombre de participants: ${ requestScope.joiners }
            </h3>
        </div>
        <div class="d-flex flex-row justify-content-center"><p class="text-break p-4">${ requestScope.event.content }</p></div>
        <p class="d-flex flex-row"> </div>
        <div class="d-flex flex-row justify-content-center">
            <p>
                Crée par l'utilisateur ${requestScope.event.account.name} ${requestScope.event.account.firstName}
            </p>
        </div>
        <div class="d-flex flex-row justify-content-center">
            <c:choose>
                <c:when test="${ event.account.emailUser.equals(sessionScope.user.emailUser) }">
                    <h3> <a class="badge badge-secondary" href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/${ event.id }/delete">Supprimer l'évènement</a></h3>
                </c:when>
                <c:otherwise>
                    <h3><a class="badge badge-secondary" href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/${ event.id }/participate">
                        <c:choose>
                            <c:when test="${ !requestScope.like }">
                                Participer
                            </c:when>
                            <c:otherwise>
                                Annuler
                            </c:otherwise>
                        </c:choose>
                    </a></h3>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
