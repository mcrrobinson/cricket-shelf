<%-- 
    Document   : index
    Created on : 09-Mar-2023, 15:47:17
    Author     : Squash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>

<!-- Anything that doesn't require rendering goes here, anything that does 
goes in the WEB-INF/view folder. WEB-INF/view/checkout.jsp wont be accessible 
to the browser -->
<html lang="en">
    <head>
    </head>
    <body>
        <div id="main">
            <div class="topnav">
                <a class="homeButton" href="index.jsp">Home</a>
                <!-- Search bar -->
                <form class="search" action="search" method="get">
                    <input type="text" name="search" id="search" placeholder="Search for a book..." />
                    <input type="submit" value="Search" />
                </form>
                <a href="basket">Basket</a>
                <a href="orders">Orders</a>
                <a class="logoutButton" href="/cricket-shelf/api/logout">Logout</a>
            </div>
            <div class="container">
                <h1>Home Page</h1>

                <div id="content" class="content-md"></div>
            </div>
        </div>
    </body>
    <script>
        var content = document.getElementById("content");
        
    fetch('/cricket-shelf/api/recently-viewed').then(res => {
                if(res.redirected){
                    window.location.href = res.url;
                } else {
                    return res.json();
                }
            }).then(book => {
        if(book == null) return;
        
        var card = document.createElement("div");
        card.classList = "card padding-sm align-center margin20";
        
        var title = document.createElement("h1");
        title.innerText = "Recently Viewed";
        
        var link = document.createElement("a");
        link.href = "book?id=" + book.bookId;
        
        var image = document.createElement("img");
        image.src = 'img/' + book.thumbnail + '.jpg';
        image.alt = book.title;
        image.setAttribute("aria-label", book.title);
        image.setAttribute("aria-labelledby", book.title);
        
        link.appendChild(image);
        
        card.appendChild(title);
        card.appendChild(link);
        content.appendChild(card);
    });
    fetch('/cricket-shelf/api/recently-added')
            .then(res => {
                if(res.redirected){
                    window.location.href = res.url;
                } else {
                    return res.json();
                }
            }).then(book => {
        if(book === null) return;
        
        var card = document.createElement("div");
        card.classList = "card padding-sm align-center margin20";
        
        var title = document.createElement("h1");
        title.innerText = "Recently Added";
        
        var link = document.createElement("a");
        link.href = "book?id=" + book.bookId;
        
        var image = document.createElement("img");
        image.src = 'img/' + book.thumbnail + '.jpg';
        image.alt = book.title;
        image.setAttribute("aria-label", book.title);
        image.setAttribute("aria-labelledby", book.title);
        
        link.appendChild(image);
        
        card.appendChild(title);
        card.appendChild(link);
        content.appendChild(card);
    });
    
    </script>
</html>
