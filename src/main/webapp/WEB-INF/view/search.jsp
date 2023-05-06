<%-- 
    Document   : search
    Created on : 09-Mar-2023, 19:18:11
    Author     : Squash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="topnav">
            <a class="homeButton" href="index.jsp">Home</a>
            <!-- Search bar -->
            <form class="search" action="search" method="get">
                <input type="text" name="search" id="search" placeholder="Search for a book..." />
                <input type="submit" value="Search" />
            </form>
            <a href="basket">Basket</a>
            <a href="orders">Orders</a>
            <a class="logoutButton" href="login">Logout</a>
        </div>
        <div class="container">
            <div style="flex-wrap:wrap;" class="content-md">
                <c:forEach var="book" items="${books}">
                    <div style="min-width:220px;max-width:220px;" style="margin-bottom:20px;" class="card">
                        <a href="book?id=${book.bookId}">
                            <p>${book.title}</p>
                            <img style="width:220px;height:360px;" src="/cricket-shelf/img/${book.thumbnail}.jpg" alt="" />
                            <%--<img src="${book.thumbnail}" alt="" />--%>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
