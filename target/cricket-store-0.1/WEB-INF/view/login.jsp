<%-- 
    Document   : login
    Created on : 09-Mar-2023, 19:18:19
    Author     : Squash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="login.php" method="get">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" />

            <br />
            <label for="password">Password</label>
            <input type="password" name="password" id="password" />
            <br />
            <input type="submit" value="Login" />
        </form>
    </body>
</html>
