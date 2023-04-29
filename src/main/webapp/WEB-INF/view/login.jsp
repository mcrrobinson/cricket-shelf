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
        <div>
            <label for="emailAddress">Username</label>
            <input type="text" name="emailAddress" id="emailAddress" />

            <br />
            <label for="password">Password</label>
            <input type="password" name="password" id="password" />
            <br />
            <input type="submit" value="Login" onclick="sendLoginRequest()" />
            <a href="/cricket-shelf/signup">Sign up</a>
        </div>
    </body>
    
    <script>
        var emailAddress = document.getElementById("emailAddress");
        var password = document.getElementById("password");
        
        function sendLoginRequest(){
            fetch('/cricket-shelf/api/login', {
                method: 'POST',
                redirect: 'follow',
                headers: {
                  'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                  emailAddress: emailAddress.value,
                  password: password.value
                })
            }).then(response => {
                if (response.redirected) {
                    window.location.href = response.url;
                } else {
                    response.text().then((text => {
                        alert(text);
                    }));
                }
            });
        }
    </script>
</html>
