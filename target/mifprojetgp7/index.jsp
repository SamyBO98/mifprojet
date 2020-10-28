<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
    <h2>Hello World!</h2>
    <form action="register" method="post">

        <label>
            Entrez le titre
            <input type="text" name="title"/>
        </label>

        <label>
            Entrez une description (optionnel)
            <input type="text" name="description"/>
        </label>

        <button type="submit" name="button">Lancez</button>

    </form>

    <c:if test="${ sessionScope.events != null }">
        <table>
            <tr>
                <th>Evènement numéro</th>
                <th>Titre de l'évènement</th>
                <th>Description de l'évènement</th>
            </tr>

            <c:forEach items="${ sessionScope.events }" var="event">
                <tr>
                    <td> ${ event.id } </td>
                    <td> ${ event.title } </td>
                    <td> ${ event.description } </td>
                </tr>
            </c:forEach>

        </table>
    </c:if>

</body>
</html>
