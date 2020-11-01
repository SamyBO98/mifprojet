<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Log On</title>
</head>
<body>
    <form method="post" action="/mifprojetgp7_war/auth">
        <label>
            <h2>Email adress</h2>
            <input type="email" name="logon-email" required>
        </label>

        <label>
            <h2>Password</h2>
            <input type="password" name="logon-password" required>
        </label>

        <button type="submit" name="logon-submit" value="Connect">Connect</button>
    </form>
</body>
</html>
