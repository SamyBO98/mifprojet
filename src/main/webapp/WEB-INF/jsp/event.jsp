<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<h1>Evenement ${ requestScope.event.title }</h1>
<h2>${ requestScope.event.content }</h2>
<h3>Crée par l'utilisateur ${requestScope.event.account.name} ${requestScope.event.account.firstName}</h3>
<a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/${ event.id }/participate">Participer / Annuler</a></h4>
