<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Liste des catégories</h1>
<c:choose>
    <c:when test="${ requestScope.categories.size() == 0 }">
        <h2>Aucune catégorie stockée dans la base de données...</h2>
    </c:when>
    <c:otherwise>
        <c:forEach items="${ requestScope.categories }" var="category">
            <h2>Catégorie: ${ category.categoryName }</h2>
            <h3>
                <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/category/${ category.categoryName }/react">
                    Aimer / Ne pas aimer
                </a>
            </h3>
            <h4>
                <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/category/${ category.categoryName }">
                    Liste ces évènements de la catégorie
                </a>
            </h4>
        </c:forEach>
    </c:otherwise>
</c:choose>
