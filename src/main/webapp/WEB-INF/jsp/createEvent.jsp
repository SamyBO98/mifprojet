<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<form method="post" action="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/create">

    Que voulez-vous créer zebi?
    <label>
        <h2>Title</h2>
        <input type="text" name="title" required>
    </label>

    <label>
        <h2>Contenu</h2>
        <input type="text" name="contenu">
    </label>

    <button type="submit" name="submit" value="CreateEvent">Create Event</button>

</form>
