<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<h1>
    Evenement
    <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/title/${ requestScope.event.title }">
        ${ requestScope.event.title }
    </a>
</h1>

<h2>${ requestScope.event.content }</h2>
<h3>
    Crée par l'utilisateur ${requestScope.event.account.name} ${requestScope.event.account.firstName}
</h3>

<h3>
    De la catégorie:
    <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/category/${ requestScope.event.category.categoryName }">
        ${ requestScope.event.category.categoryName }
    </a>
</h3>

<h3>
    Nombre de participants: ${ requestScope.joiners }
</h3>

<c:choose>
    <c:when test="${ event.account.emailUser.equals(sessionScope.user.emailUser) }">
        <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/${ event.id }/delete">Supprimer l'évènement</a>
    </c:when>
    <c:otherwise>
        <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/${ event.id }/participate">
            <c:choose>
                <c:when test="${ !requestScope.like }">
                    Participer
                </c:when>
                <c:otherwise>
                    Annuler
                </c:otherwise>
            </c:choose>
        </a>
    </c:otherwise>
</c:choose>
