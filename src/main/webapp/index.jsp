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

</body>
</html>
