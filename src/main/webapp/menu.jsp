<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="fr.univlyon1.mifprojetgp7.utils.ParseURI" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<div class="container-fluid">
    <div class="d-flex flex-row bd-highlight">
        <div class="p-2 flex-grow-1 bd-highlight">
            GP7 Event
        </div>
        <c:choose>
            <c:when test="${ sessionScope.user != null }">
                <div class="p-2 bd-highlight"><a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/users/disconnect"> <button  class="btn btn-secondary"> Deconnexion </button></a></div>
                <div class="p-2 bd-highlight"><a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events"> <button  class="btn btn-secondary"> Liste des événements </button></a></div>
                <div class="p-2 bd-highlight"><a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/created"> <button  class="btn btn-secondary"> Liste de mes événements </button></a></div>
                <div class="p-2 bd-highlight"><a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/create"> <button  class="btn btn-secondary"> Créer un événement </button></a></div>
                <div class="p-2 bd-highlight"><a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/search"> <button  class="btn btn-secondary"> Chercher un événement </button></a></div>
                <div class="p-2 bd-highlight"><a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/events/participate"> <button  class="btn btn-secondary"> Mes participations </button></a></div>
            </c:when>
            <c:otherwise>
                <div class="p-2 bd-highlight"> <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/users/signup">  <button  class="btn btn-secondary"> Inscription </button></a></div>
                <div class="p-2 bd-highlight"> <a href="/<%= ParseURI.sourceURI(request.getRequestURI()) %>/users/login">  <button  class="btn btn-secondary">Connexion </button></a></div>
            </c:otherwise>
        </c:choose>
    </div>
</div>