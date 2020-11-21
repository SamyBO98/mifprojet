<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<c:choose>
    <c:when test="${ requestScope.filter.equals(\"title\") }">
        <form method="post" action="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/title">

            <label>
                <h2>Text search</h2>
                <input type="text" name="text-filter" required>
            </label>

            <button type="submit" name="submit" value="SearchFilter">Search Events</button>

        </form>
    </c:when>

    <c:otherwise>
        <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/title"></a>
        <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/category"></a>
    </c:otherwise>
</c:choose>
