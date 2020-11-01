<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <form method="post" action="/mifprojetgp7_war/register">
        <label>
            <h2>Email adress</h2>
            <input type="email" name="login-email" required>
        </label>

        <label>
            <h2>Password</h2>
            <input type="password" name="login-password" required>
        </label>

        <label>
            <h2>First Name</h2>
            <input type="text" name="login-firstname" required>
        </label>

        <label>
            <h2>Last Name</h2>
            <input type="text" name="login-lastname" required>
        </label>

        <button type="submit" name="logon-submit" value="Create My Account">Create My Account</button>
    </form>
</body>
</html>
