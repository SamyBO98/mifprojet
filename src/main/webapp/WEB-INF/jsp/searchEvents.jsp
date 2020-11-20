<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<form method="post" action="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search">

    <label>
        <h2>Text search</h2>
        <input type="text" name="text-filter" required>
    </label>

    <label>
        <h2>Search Filter</h2>
        <select name="search-filter">
            <option value="">-- Please Select an option --</option>
            <option value="category">Category</option>
            <option value="title">Title</option>
        </select>
    </label>

    <button type="submit" name="submit" value="SearchFilter">Search Events</button>

</form>
