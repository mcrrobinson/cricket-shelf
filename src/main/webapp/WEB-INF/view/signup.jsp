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
        <style>
        @import url(https://fonts.googleapis.com/css?family=Roboto:300);

        .login-page {
            width: 360px;
            padding: 8% 0 0;
            margin: auto;
        }

        .form {
            position: relative;
            z-index: 1;
            background: #FFFFFF;
            max-width: 360px;
            margin: 0 auto 100px;
            padding: 45px;
            text-align: center;
            box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
        }

        .form input {
            font-family: "Roboto", sans-serif;
            outline: 0;
            background: #f2f2f2;
            width: 100%;
            border: 0;
            margin: 0 0 15px;
            padding: 15px;
            box-sizing: border-box;
            font-size: 14px;
        }

        .form button {
            font-family: "Roboto", sans-serif;
            text-transform: uppercase;
            outline: 0;
            background: #4CAF50;
            width: 100%;
            border: 0;
            padding: 15px;
            color: #FFFFFF;
            font-size: 14px;
            -webkit-transition: all 0.3 ease;
            transition: all 0.3 ease;
            cursor: pointer;
        }

        .form button:hover,
        .form button:active,
        .form button:focus {
            background: #43A047;
        }

        .form .message {
            margin: 15px 0 0;
            color: #b3b3b3;
            font-size: 12px;
        }

        .form .message a {
            color: #4CAF50;
            text-decoration: none;
        }

        .container {
            position: relative;
            z-index: 1;
            max-width: 300px;
            margin: 0 auto;
        }

        .container:before,
        .container:after {
            content: "";
            display: block;
            clear: both;
        }

        .container .info {
            margin: 50px auto;
            text-align: center;
        }

        .container .info h1 {
            margin: 0 0 15px;
            padding: 0;
            font-size: 36px;
            font-weight: 300;
            color: #1a1a1a;
        }

        .container .info span {
            color: #4d4d4d;
            font-size: 12px;
        }

        .container .info span a {
            color: #000000;
            text-decoration: none;
        }

        .container .info span .fa {
            color: #EF3B3A;
        }

        body {
            font-family: "Roboto", sans-serif;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        .cookie-container {
            padding: 10px;
            display: flex;
            justify-content: center;
            align-content: center;
            background-color: rgba(0, 74, 151, 0.9);
        }
        .cookies {
            position: fixed;
            bottom: 0;
            width: 100%;
            z-index: 999;
        }

        .cookie-container > div {
            color: #fff;
            font-size: 1.1rem;
        }

        .cookie-container > .close {
            color: #fff;
            font-size: 1.5rem;
            font-weight: bold;
            align-self: center;
            padding: 0 1rem;
            cursor: pointer;
            background-color: #004a97;
            transition: background-color 0.3s ease;
            margin-right: 0.5rem;
        }

        .cookie-container > .close:hover {
            background-color: #fff;
            color: #004a97;
        }

    </style>
    </head>
    <body>
        <div class="login-page">
            <div class="form">
                <h1>Sign Up</h1>
                <div class="register-form">
                    <input type="text" id="emailAddress" placeholder="email address" />
                    <input type="password" id="firstName" placeholder="first name" />
                    <input type="text" id="lastName" placeholder="last name" />
                    <input type="text" id="password" placeholder="password" />
                    <input type="text" id="confirmPassword" placeholder="confirm password" />
                    <button onclick="onSignupRequest()">create</button>
                    <p class="message">Already registered? <a href="/cricket-shelf/login">Sign In</a></p>
                </div>
            </div>
        </div>
        <div class="cookies" style="display:none;">
            <div class="cookie-container">
            <div id="close" class="close">X</div>
            <div class="col-sm-12">This site uses cookies in order to run properly and create a better user experience. If you continue it will be assumed that you agree to the use of cookies.</div>
            </div>
        </div>
    </body>
    <script>
        var emailAddress = document.getElementById("emailAddress");
        var firstName = document.getElementById("firstName");
        var lastName = document.getElementById("lastName");
        var password = document.getElementById("password");

        let accepted = sessionStorage.getItem("acceptedCookies");
        if (!accepted) {
            document.querySelector(".cookies").style.display = "block";
        }

        let close = document.getElementById("close");

        close.addEventListener("click", function(){
            event.target.parentElement.parentElement.style.display = "none";
        });
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
