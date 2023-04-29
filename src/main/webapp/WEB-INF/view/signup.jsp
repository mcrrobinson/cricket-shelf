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
        <div>
            <label for="emailAddress">Email</label>
            <input type="text" name="emailAddress" id="emailAddress" />
            <br />
            
            <label for="firstName">First Name</label>
            <input type="text" name="firstName" id="firstName" />
            <br />
            
            <label for="lastName">Last Name</label>
            <input type="text" name="lastName" id="lastName" />
            
            <br />
            <label for="password">Password</label>
            <input type="password" name="password" id="password" />
            
            <br />
            <label for="password">Password</label>
            <input type="password" name="password" id="password" />
            
            <br />
            <input type="submit" value="Sign Up" onclick="onSignupRequest()"/>
        </div>
    </body>
    <script>
        var emailAddress = document.getElementById("emailAddress");
        var firstName = document.getElementById("firstName");
        var lastName = document.getElementById("lastName");
        var password = document.getElementById("password");
        function onSignupRequest(){
            fetch('/cricket-shelf/api/signup', {
                method: 'POST',
                redirect: 'follow',
                headers: {
                  'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                  emailAddress: emailAddress.value,
                  firstName: firstName.value,
                  lastName: lastName.value,
                  password: password.value
                })
            }).then(response => {
                if (response.redirected) {
                    window.location.href = response.url;
                } else {
                    response.text().then((text => {
                        console.log(text);
                    }));
                }
            });
        }
    
    </script>
</html>
