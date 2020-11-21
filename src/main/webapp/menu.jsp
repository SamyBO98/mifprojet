<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<div class="container-fluid">
    <div class="d-flex flex-row bd-highlight">
        <div class="p-2 flex-grow-1 bd-highlight">
            GP7 Event
        </div>
        <c:choose>
            <c:when test="${ sessionScope.user != null }">
                <div class="p-2 bd-highlight"> <button> Deconnexion </button></div>
            </c:when>
            <c:otherwise>
                <div class="p-2 bd-highlight"> <a href="./users/signup">Inscription </a></div>
                <div class="p-2 bd-highlight"> <a href="./users/login">Connexion</a></div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
