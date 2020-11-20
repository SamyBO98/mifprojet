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
    <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search/category/${ event.category.categoryName }">
        ${ event.category.categoryName }
    </a>
</h3>

<h4>
    <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/${ event.id }/participate">
        Participer / Annuler
    </a>
</h4>
