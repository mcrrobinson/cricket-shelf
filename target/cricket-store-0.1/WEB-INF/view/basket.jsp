<%-- 
    Document   : checkout
    Created on : 09-Mar-2023, 19:17:58
    Author     : Squash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
        <script src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"/>
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
            <h1>Your Basket</h1>
            <table id="example" class="display" style="width:100%">
                <thead>
                    <tr>
                        <th>Thumbnail</th>
                        <th>Title</th>
                        <th>Price</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <form action="/cricket-shelf/checkout">
                <input id="checkoutButton" type="submit" value="Checkout" disabled/>
            </form>
        </div>
    </body>
    <script>
        var table = $('#example').DataTable({
            ajax: {
                    url: '/cricket-shelf/api/cart/list',
                    dataSrc: function(data) {
                        console.log(data);
                        if(data.length !== 0){
                            document.getElementById("checkoutButton").disabled = false;
                        }
                        return data;
                    }
                },
            processing: true,
            columnDefs: [
                {type:"html",targets:0, render: function (data, type, full, meta) {
                        return '<img style="height:150px;" src="img/'+ data + '.jpg"/>';}},
                {type:"html",targets:1},
                {type:"html",targets:2},
                {type:"html",targets:3}
            ],
            columns: [
                { data: 'books.thumbnail', title: '', orderable: false },
                { data: 'books.title', title: 'Title' },
                { data: 'books.salesPrice', title: 'Price' },
                { data: 'quantity', title: 'Qty'}
            ]
        });
    </script>
</html>
