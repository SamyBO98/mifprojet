<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="container-fluid">
    <div class="d-flex flex-row justify-content-center">
        <c:choose>
            <c:when test="${ requestScope.filter.equals(\"title\") }">
                <form method="post" action="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/title">
                    <div class="form-group">
                        <label for="filter"><h4>Recherche</h4></label>
                        <input type="text" class="form-control" id="filter" name="text-filter" required>
                    </div>             

                    <button class="btn btn-secondary" type="submit" name="submit" value="SearchFilter">Chercher les événements</button>

                </form>
            </c:when>

            <c:otherwise>
                <div class="d-flex flex-column justify-content-center pr-3">
                    <h3><a class="badge badge-secondary" href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/title">Rechercher par titre</a></h3>
                </div>
                <div class="d-flex flex-column justify-content-center pl-3">
                    <h3><a class="badge badge-secondary" href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/category">Rechercher par catégorie</a></h3>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
