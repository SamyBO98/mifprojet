<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <div class="container-fluid">
    <c:choose>
        <c:when test="${ requestScope.categories.size() == 0 }">
            <h2>Aucune catégorie stockée dans la base de données...</h2>
        </c:when>
        <c:otherwise>
            <div class="d-flex flex-column">
            <h1 class="d-flex mx-auto"> Liste des catégories </h1>
                <c:forEach items="${ requestScope.categories }" var="category" varStatus="status">
                    <c:if test="${ ((status.count - 1 )  % 4 ) == 0 }">
                            <div class="d-flex flex-row justify-content-center">
                    </c:if>
                    <div class="card mx-auto">
                        <div class="card-body">
                            <h3 class="card-title">${category.categoryName}</h3>
                            <a class="card-link" href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/category/${ category.categoryName }"> <button  class="btn btn-secondary"> En savoir plus </button></a>
                            <a class="card-link" href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/category/${ category.categoryName }/react"> <button  class="btn btn-secondary"> Aimer / Ne pas aimer</button></a>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${ ((status.count - 1 )  % 4 ) == 3 }">
                            </div>
                        </c:when>
                        <c:when test="${ (status.count) == requestScope.events.size()}">
                            </div>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>