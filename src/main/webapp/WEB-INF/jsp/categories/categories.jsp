<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<div class="container">
    <c:choose>
        <c:when test="${ requestScope.categories.size() == 0 }">
            <h2>Aucune catégorie stockée dans la base de données...</h2>
        </c:when>
        <c:otherwise>
            <div class="d-flex justify-content-center mb-5">
                <h1> Liste des catégories </h1>
            </div>
            <div class="row">
                <c:forEach items="${ requestScope.categories }" var="category" varStatus="status">
                    <div class="col-lg-3 col-md-4 col-sm-12 mb-5">
                        <div class="card mx-auto">
                            <div class="card-body">
                                <h3 class="card-title">${category.categoryName}</h3>
                                <a class="card-link m-2" href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/category/${ category.categoryName }"> <button  class="btn btn-secondary"> En savoir plus </button></a>
                                <a class="card-link m-2" href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/category/${ category.categoryName }/react"> <button  class="btn btn-secondary"> Aimer / Ne pas aimer</button></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>
