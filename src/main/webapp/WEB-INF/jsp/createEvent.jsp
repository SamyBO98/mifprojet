<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<form method="post" action="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/create">

    Que voulez-vous créer zebi?
    <label>
        <h2>Title</h2>
        <input type="text" name="title" required>
    </label>

    <label>
        <h2>Content</h2>
        <input type="text" name="contenu">
    </label>

    <label>
        <h2>Category</h2>
        <select name="category">
            <option value="">-- Please Select a Category --</option>
            <c:forEach items="${ requestScope.categories }" var="category">
                <option value="${ category.categoryName }">${ category.categoryName }</option>
            </c:forEach>
        </select>
    </label>

    <button type="submit" name="submit" value="CreateEvent">Create Event</button>

</form>
