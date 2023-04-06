<%-- 
    Document   : orders
    Created on : 09-Mar-2023, 19:17:58
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
        <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
        <script src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
        <script src="//cdn.datatables.net/plug-ins/1.10.12/sorting/datetime-moment.js"></script>
        <script src="https://cdn.datatables.net/datetime/1.3.1/js/dataTables.dateTime.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"/>
        <style>
    div.dt-datetime {
      position: absolute;
      background-color: white;
      z-index: 2050;
      border: 1px solid #ccc;
      box-shadow: 0 5px 15px -5px rgba(0, 0, 0, 0.5);
      padding: 0 20px 6px 20px;
      width: 275px;
    }
    div.dt-datetime.inline {
      position: relative;
      box-shadow: none;
    }
    div.dt-datetime div.dt-datetime-title {
      text-align: center;
      padding: 5px 0px 3px;
    }
    div.dt-datetime div.dt-datetime-buttons {
      text-align: center;
    }
    div.dt-datetime div.dt-datetime-buttons a {
      display: inline-block;
      padding: 0 0.5em 0.5em 0.5em;
      margin: 0;
      font-size: 0.9em;
    }
    div.dt-datetime div.dt-datetime-buttons a:hover {
      text-decoration: underline;
    }
    div.dt-datetime table {
      border-spacing: 0;
      margin: 12px 0;
      width: 100%;
    }
    div.dt-datetime table.dt-datetime-table-nospace {
      margin-top: -12px;
    }
    div.dt-datetime table th {
      font-size: 0.8em;
      color: #777;
      font-weight: normal;
      width: 14.285714286%;
      padding: 0 0 4px 0;
      text-align: center;
    }
    div.dt-datetime table td {
      font-size: 0.9em;
      color: #444;
      padding: 0;
    }
    div.dt-datetime table td.selectable {
      text-align: center;
      background: #f5f5f5;
    }
    div.dt-datetime table td.selectable.disabled {
      color: #aaa;
      background: white;
    }
    div.dt-datetime table td.selectable.disabled button:hover {
      color: #aaa;
      background: white;
    }
    div.dt-datetime table td.selectable.now {
      background-color: #ddd;
    }
    div.dt-datetime table td.selectable.now button {
      font-weight: bold;
    }
    div.dt-datetime table td.selectable.selected button {
      background: #4e6ca3;
      color: white;
      border-radius: 2px;
    }
    div.dt-datetime table td.selectable button:hover {
      background: #ff8000;
      color: white;
      border-radius: 2px;
    }
    div.dt-datetime table td.dt-datetime-week {
      font-size: 0.7em;
    }
    div.dt-datetime table button {
      width: 100%;
      box-sizing: border-box;
      border: none;
      background: transparent;
      font-size: inherit;
      color: inherit;
      text-align: center;
      padding: 4px 0;
      cursor: pointer;
      margin: 0;
    }
    div.dt-datetime table button span {
      display: inline-block;
      min-width: 14px;
      text-align: right;
    }
    div.dt-datetime table.weekNumber th {
      width: 12.5%;
    }
    div.dt-datetime div.dt-datetime-calendar table {
      margin-top: 0;
    }
    div.dt-datetime div.dt-datetime-label {
      position: relative;
      display: inline-block;
      height: 30px;
      padding: 5px 6px;
      border: 1px solid transparent;
      box-sizing: border-box;
      cursor: pointer;
    }
    div.dt-datetime div.dt-datetime-label:hover {
      border: 1px solid #ddd;
      border-radius: 2px;
      background-color: #f5f5f5;
    }
    div.dt-datetime div.dt-datetime-label select {
      position: absolute;
      top: 6px;
      left: 0;
      cursor: pointer;
      opacity: 0;
    }
    div.dt-datetime.horizontal {
      width: 550px;
    }
    div.dt-datetime.horizontal div.dt-datetime-date,
    div.dt-datetime.horizontal div.dt-datetime-time {
      width: 48%;
    }
    div.dt-datetime.horizontal div.dt-datetime-time {
      margin-left: 4%;
    }
    div.dt-datetime div.dt-datetime-date {
      position: relative;
      float: left;
      width: 100%;
    }
    div.dt-datetime div.dt-datetime-time {
      position: relative;
      float: left;
      width: 100%;
      text-align: center;
    }
    div.dt-datetime div.dt-datetime-time>span {
      vertical-align: middle;
    }
    div.dt-datetime div.dt-datetime-time th {
      text-align: left;
    }
    div.dt-datetime div.dt-datetime-time div.dt-datetime-timeblock {
      display: inline-block;
      vertical-align: middle;
    }
    div.dt-datetime div.dt-datetime-iconLeft,
    div.dt-datetime div.dt-datetime-iconRight,
    div.dt-datetime div.dt-datetime-iconUp,
    div.dt-datetime div.dt-datetime-iconDown {
      width: 30px;
      height: 30px;
      background-position: center;
      background-repeat: no-repeat;
      opacity: 0.3;
      overflow: hidden;
      box-sizing: border-box;
    }
    div.dt-datetime div.dt-datetime-iconLeft:hover,
    div.dt-datetime div.dt-datetime-iconRight:hover,
    div.dt-datetime div.dt-datetime-iconUp:hover,
    div.dt-datetime div.dt-datetime-iconDown:hover {
      border: 1px solid #ccc;
      border-radius: 2px;
      background-color: #f0f0f0;
      opacity: 0.6;
    }
    div.dt-datetime div.dt-datetime-iconLeft button,
    div.dt-datetime div.dt-datetime-iconRight button,
    div.dt-datetime div.dt-datetime-iconUp button,
    div.dt-datetime div.dt-datetime-iconDown button {
      border: none;
      background: transparent;
      text-indent: 30px;
      height: 100%;
      width: 100%;
      cursor: pointer;
    }
    div.dt-datetime div.dt-datetime-iconLeft {
      position: absolute;
      top: 5px;
      left: 5px;
      background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAeCAYAAAAsEj5rAAAAUklEQVR42u3VMQoAIBADQf8Pgj+OD9hG2CtONJB2ymQkKe0HbwAP0xucDiQWARITIDEBEnMgMQ8S8+AqBIl6kKgHiXqQqAeJepBo/z38J/U0uAHlaBkBl9I4GwAAAABJRU5ErkJggg==');
    }
    div.dt-datetime div.dt-datetime-iconRight {
      position: absolute;
      top: 5px;
      right: 5px;
      background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAeCAYAAAAsEj5rAAAAU0lEQVR42u3VOwoAMAgE0dwfAnNjU26bYkBCFGwfiL9VVWoO+BJ4Gf3gtsEKKoFBNTCoCAYVwaAiGNQGMUHMkjGbgjk2mIONuXo0nC8XnCf1JXgArVIZAQh5TKYAAAAASUVORK5CYII=');
    }
    div.dt-datetime div.dt-datetime-iconUp {
      height: 20px;
      background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAALCAMAAABf9c24AAAAFVBMVEX///99fX1+fn57e3t6enoAAAAAAAC73bqPAAAABnRSTlMAYmJkZt92bnysAAAAL0lEQVR4AWOgJmBhxCvLyopHnpmVjY2VCadeoCxIHrcsWJ4RlyxCHlMWCTBRJxwAjrIBDMWSiM0AAAAASUVORK5CYII=');
    }
    div.dt-datetime div.dt-datetime-iconDown {
      height: 20px;
      background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAALCAMAAABf9c24AAAAFVBMVEX///99fX1+fn57e3t6enoAAAAAAAC73bqPAAAABnRSTlMAYmJkZt92bnysAAAAMElEQVR4AWOgDmBiRQIsmPKMrGxQgJDFlEfIYpoPk8Utz8qM232MYFfhkQfKUg8AANefAQxecJ58AAAAAElFTkSuQmCC');
    }
    div.dt-datetime-error {
      clear: both;
      padding: 0 1em;
      max-width: 240px;
      font-size: 11px;
      line-height: 1.25em;
      text-align: center;
      color: #b11f1f;
    }
    p {
      word-wrap: break-word;
    }
    .modal .modal-dialog-centered {
      width: 90%;
      max-width: none;
    }
    .modal .modal-content {
      height: 100%;
      border: 0;
      border-radius: 0;
    }
    .modal .modal-body {
      overflow-y: auto;
    }
  </style>
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
            <h1>Your Orders</h1>
            <table border="0" cellspacing="5" cellpadding="5">
              <tbody>
                <tr>
                  <td>Ordered date from:</td>
                  <td><input type="text" id="min" name="min" /></td>
                </tr>
                <tr>
                  <td>Ordered date to:</td>
                  <td><input type="text" id="max" name="max" /></td>
                </tr>
              </tbody>
            </table>
            <table id="example" class="display" style="width:100%">
                <thead>
                    <tr>
                        <th></th>
                        <th>Order ID</th>
                        <th>Total</th>
                        <th>Status</th>
                        <th>Ordered</th>
                        <th>Out for Delivery</th>
                        <th>Delivered</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </body>
    <script>
        var minDate, maxDate;
        
        var table = $('#example').DataTable({
            ajax: {
                    url: '/cricket-store/api/orders/list',
                    dataSrc: ''
                },
            processing: true,
            columnDefs: [
                {type:"html",targets:0},
                {type:"html",targets:1},
                {type:"html",targets:2},
                {type:"html",targets:3},
                {type:"unix",targets:4,
                    render: function (data, type, full, meta) {
                        return moment.utc(data, "x").toISOString()
                    }
                },
                {type:"html",targets:5},
                {type:"html",targets:6}
            ],
            columns: [
                {
                    className: 'dt-control',
                    orderable: false,
                    data: null,
                    defaultContent: '',
                },
                { data: 'orderId', title: 'ID' },
                { data: 'total', title: 'Total' },
                { data: 'status', title: 'Status' },
                { data: 'ordered', title: 'Ordered', type: "date"},
                { data: 'outForDelivery', title: 'Out for delivery' },
                { data: 'delivered', title: 'Delivered' }
            ]
        });
        
        // Custom filtering function which will search data in column four between two values
        $.fn.dataTable.ext.search.push(function (settings, data, dataIndex) {
          var min = minDate.val();
          var max = maxDate.val();
          var date = new Date(data[4]);
          if (
            (min === null && max === null) ||
            (min === null && date <= max) ||
            (min <= date && max === null) ||
            (min <= date && date <= max)
          ) {
            return true;
          }
          return false;
        });
        // Create date inputs
        minDate = new DateTime($('#min'), {
          format: 'MMMM Do YYYY',
        });
        maxDate = new DateTime($('#max'), {
          format: 'MMMM Do YYYY',
        });
        
        // Refilter the table
        $('#min, #max').on('change', function () {
          table.draw();
        });
        
        function format(d) {
            return fetch("/cricket-store/api/orders/product?id=" + d.orderId).then((res) => res.json());
        }
        
        // Add event listener for opening and closing details
        $('#example tbody').on('click', 'td.dt-control', function () {
            var tr = $(this).closest('tr');
            var row = table.row(tr);

            if (row.child.isShown()) {
                // This row is already open - close it
                row.child.hide();
                tr.removeClass('shown');
            } else {
                // Open this row
                format(row.data()).then((res) => {
                    let table = document.createElement('table');
                    let table_head = document.createElement('thead');
                    let table_head_row = document.createElement('tr');

                    let table_head_image = document.createElement('th');
                    table_head_image.innerText = 'Image';
                    table_head_row.appendChild(table_head_image);

                    let table_head_title = document.createElement('th');
                    table_head_title.innerText = 'Title';
                    table_head_row.appendChild(table_head_title);

                    let table_head_cell = document.createElement('th');
                    table_head_cell.innerText = 'Quantity';
                    table_head_row.appendChild(table_head_cell);

                    table_head_cell = document.createElement('th');
                    table_head_cell.innerText = 'Price';
                    table_head_row.appendChild(table_head_cell);

                    table_head.appendChild(table_head_row);
                    table.appendChild(table_head);

                    res.forEach((item) => {
                        let row = document.createElement('tr');

                        let cell_image = document.createElement('td');
                        let image = document.createElement('img');
                        image.style = 'object-fit: contain';
                        image.width = 50;
                        image.height = 50;
                        image.src = "img/" + item.books.thumbnail + ".jpg";
                        
                        cell_image.appendChild(image);
                        row.appendChild(cell_image);
                        
                        console.log(item);
                        let cell_title = document.createElement('td');
                        let cell_title_link = document.createElement('a');
                        cell_title_link.href = '/cricket-store/book?id=' + item.books.bookId;
                        cell_title_link.innerText = item.books.title;
                        cell_title.appendChild(cell_title_link);
                        row.appendChild(cell_title);

                        let cell = document.createElement('td');
                        cell.innerText = item.quantity;
                        row.appendChild(cell);

                        cell = document.createElement('td');
                        cell.innerText = item.itemCostOnPurchase.toFixed(2);
                        row.appendChild(cell);

                        table.appendChild(row);
                    });
                    row.child(table).show();
                });
                tr.addClass('shown');
            }
        });
    </script>
</html>
