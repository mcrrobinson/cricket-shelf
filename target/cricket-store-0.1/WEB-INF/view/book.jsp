<%-- 
    Document   : book
    Created on : 09-Mar-2023, 19:18:05
    Author     : Squash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <a href="basket">Checkout</a>
            <a href="orders">Orders</a>
            <a class="logoutButton" href="login">Logout</a>
        </div>
        <div class="container">
            <div class="content-md">
                <div class="card flex">
                    <img src="https://m.media-amazon.com/images/I/51CNarr23wL._SY346_.jpg" alt="" />
                    <div class="book-info">
                        <div class="info">
                            <h1>${book.title}</h1>
                            <p>Authors:</p>
                            <ul>
                                <c:forEach var="author" items="${authors}">
                                    <li>${author.name}</li>
                                </c:forEach>
                            </ul>
                            <p>Genres:</p>
                            <ul>
                                <c:forEach var="genre" items="${genres}">
                                    <li>${genre.name}</li>
                                </c:forEach>
                            </ul>
                            <p>Year: ${book.publishYear}</p>
                        </div>
                        <div class="btn-holder">
                            <input type="number" id="quantity" name="quantity" min="1" max="5">
                            <button id="addToCartButton" class="btn">Add to cart</button>
                        </div>
                    </div>
                </div>
            </div>

            <h1>Related books</h1>
            <div class="related-books">
                <img src="https://m.media-amazon.com/images/I/51CNarr23wL._SY346_.jpg" alt="" />
                <img src="https://m.media-amazon.com/images/I/51CNarr23wL._SY346_.jpg" alt="" />
                <img src="https://m.media-amazon.com/images/I/51CNarr23wL._SY346_.jpg" alt="" />
                <img src="https://m.media-amazon.com/images/I/51CNarr23wL._SY346_.jpg" alt="" />
                <img src="https://m.media-amazon.com/images/I/51CNarr23wL._SY346_.jpg" alt="" />
                <img src="https://m.media-amazon.com/images/I/51CNarr23wL._SY346_.jpg" alt="" />
            </div>
        </div>
    </body>
    <script>
        fetch('/cricket-store/api/recently-viewed', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              bookId: ${book.bookId}
            })
        });
            
        var addToCartButton = document.getElementById("addToCartButton");
        var quantityInput = document.getElementById("quantity");
        addToCartButton.addEventListener('click', function(){
            fetch('/cricket-store/api/cart/add', {
                method: 'POST',
                redirect: 'follow',
                headers: {
                  'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                  bookId: ${book.bookId},
                  quantity: quantityInput.value
                })
            }).then(response => {
                if (response.redirected) {
                    window.location.href = response.url;
                }
            });
        });
        </script>
</html>
