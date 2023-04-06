<%-- 
    Document   : signup
    Created on : 09-Mar-2023, 19:18:23
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
        <form action="signup.php" method="get">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" />
            <label for="password">Password</label>
            <input type="password" name="password" id="password" />
            <input type="submit" value="Sign Up" />
        </form>
    </body>
</html>
