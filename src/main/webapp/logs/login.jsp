<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Log In</title>
</head>
<body>
<form method="post" action="../users/login">
    <label>
        <h2>Email adress</h2>
        <input type="email" name="email" required>
    </label>

    <label>
        <h2>Password</h2>
        <input type="password" name="password" required>
    </label>

    <button type="submit" name="submit" value="Connect">Connect</button>
</form>
</body>
</html>
