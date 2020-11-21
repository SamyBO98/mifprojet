<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container-fluid">
    <div class="col-sm-4 mx-auto">
        <h1> Créer un événement <h1>
            <form method="post" action="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/create">
                <div class="form-group">
                    <label for="title"><h4>Titre</h4></label>
                    <input type="text" class="form-control" id="title" name="title" required>
                </div>
                <div class="form-group">
                    <label for="content"><h4>Contenu</h4></label>
                    <textarea class="form-control" id="content" name="contenu" rows="3"></textarea>
                </div>
                <div class="form-group">
                    <label for="selectCategory"><h4>Choisissez une catégorie</h4></label>
                    <select class="form-control" id="selectCategory" name="category">
                        <c:forEach items="${ requestScope.categories }" var="category">
                            <option value="${ category.categoryName }">${ category.categoryName }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="d-flex flex-row justify-content-center">
                    <button class="btn btn-secondary" type="submit" name="submit" value="CreateEvent">Créer</button>
                </div>
            </form>
    </div>
</div>
